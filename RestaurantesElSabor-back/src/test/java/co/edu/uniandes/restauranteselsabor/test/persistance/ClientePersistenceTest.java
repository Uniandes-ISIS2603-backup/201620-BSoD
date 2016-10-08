/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistance;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jdguz
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void setUp() 
    {
        try 
        {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            }
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() 
    {
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * 
     */
    private List<ClienteEntity> listaPrueba = new ArrayList<ClienteEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            ClienteEntity clienteEntity = factory.manufacturePojo(ClienteEntity.class);
            
            em.persist(clienteEntity);
            listaPrueba.add(clienteEntity);
        }
    }

    @Test
    public void createEmployeeTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntityPrueba = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.crearCliente(newEntityPrueba);

        Assert.assertNotNull(result);

        ClienteEntity entityPersistencia = em.find(ClienteEntity.class, result.getId());

        //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityPrueba.getDocumentoIdentidad(), entityPersistencia.getDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getTipoDocumentoIdentidad(), entityPersistencia.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getNombre(), entityPersistencia.getNombre());
        Assert.assertEquals(newEntityPrueba.getApellidos(), entityPersistencia.getApellidos());
        Assert.assertEquals(newEntityPrueba.getDireccion(), entityPersistencia.getDireccion());
        Assert.assertEquals(newEntityPrueba.getTelefono(), entityPersistencia.getTelefono());
    }

    @Test
    public void darClientesTest() 
    {
        List<ClienteEntity> listaPersistencia = clientePersistence.darClientes();
        Assert.assertEquals(listaPrueba.size(), listaPersistencia.size());
        
        for (ClienteEntity entidadPersistencia : listaPersistencia) 
        {
            boolean found = false;
            for (ClienteEntity entidadPrueba : listaPrueba) 
            {
                if (entidadPersistencia.getId().equals(entidadPrueba.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void darClienteTest() 
    {
        ClienteEntity entityPersistencia = listaPrueba.get(0);
        ClienteEntity newEntityPrueba = clientePersistence.darCliente(entityPersistencia.getId());
        
        Assert.assertNotNull(newEntityPrueba);
       //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityPrueba.getDocumentoIdentidad(), entityPersistencia.getDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getTipoDocumentoIdentidad(), entityPersistencia.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getNombre(), entityPersistencia.getNombre());
        Assert.assertEquals(newEntityPrueba.getApellidos(), entityPersistencia.getApellidos());
        Assert.assertEquals(newEntityPrueba.getDireccion(), entityPersistencia.getDireccion());
        Assert.assertEquals(newEntityPrueba.getTelefono(), entityPersistencia.getTelefono());
    }

    @Test
    public void eliminarClienteTest() 
    {
        ClienteEntity entity = listaPrueba.get(0);
        clientePersistence.eliminarCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void actualizarClienteTest() 
    {
        ClienteEntity entity = listaPrueba.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntityPrueba = factory.manufacturePojo(ClienteEntity.class);

        newEntityPrueba.setId(entity.getId());
        
        clientePersistence.actualizarCliente(newEntityPrueba);

        ClienteEntity entityPersistencia = em.find(ClienteEntity.class, entity.getId());

        //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityPrueba.getDocumentoIdentidad(), entityPersistencia.getDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getTipoDocumentoIdentidad(), entityPersistencia.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityPrueba.getNombre(), entityPersistencia.getNombre());
        Assert.assertEquals(newEntityPrueba.getApellidos(), entityPersistencia.getApellidos());
        Assert.assertEquals(newEntityPrueba.getDireccion(), entityPersistencia.getDireccion());
        Assert.assertEquals(newEntityPrueba.getTelefono(), entityPersistencia.getTelefono());
    }
}
