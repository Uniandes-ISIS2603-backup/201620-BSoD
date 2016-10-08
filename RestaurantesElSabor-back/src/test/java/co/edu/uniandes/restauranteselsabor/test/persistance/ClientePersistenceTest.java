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
    private List<ClienteEntity> listTest = new ArrayList<ClienteEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
            em.persist(newEntity);
            listTest.add(newEntity);
        }
    }

    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntityTest = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.create(newEntityTest);

        Assert.assertNotNull(result);

        ClienteEntity entityPersistence = em.find(ClienteEntity.class, result.getId());

        //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityTest.getDocumentoIdentidad(), entityPersistence.getDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getTipoDocumentoIdentidad(), entityPersistence.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getNombre(), entityPersistence.getNombre());
        Assert.assertEquals(newEntityTest.getApellidos(), entityPersistence.getApellidos());
        Assert.assertEquals(newEntityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(newEntityTest.getTelefono(), entityPersistence.getTelefono());
    }

    @Test
    public void findAllTest() 
    {
        List<ClienteEntity> listPersistence = clientePersistence.findAll();
        Assert.assertEquals(listTest.size(), listPersistence.size());
        
        for (ClienteEntity entityPersistence : listPersistence) 
        {
            boolean found = false;
            for (ClienteEntity entityTest : listTest) 
            {
                if (entityPersistence.getId().equals(entityTest.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void findTest() 
    {
        ClienteEntity entityPersistence = listTest.get(0);
        ClienteEntity newEntityTest = clientePersistence.find(entityPersistence.getId());
        
        Assert.assertNotNull(newEntityTest);
       //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityTest.getDocumentoIdentidad(), entityPersistence.getDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getTipoDocumentoIdentidad(), entityPersistence.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getNombre(), entityPersistence.getNombre());
        Assert.assertEquals(newEntityTest.getApellidos(), entityPersistence.getApellidos());
        Assert.assertEquals(newEntityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(newEntityTest.getTelefono(), entityPersistence.getTelefono());
    }

    @Test
    public void deleteTest() 
    {
        ClienteEntity entity = listTest.get(0);
        clientePersistence.delete(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateTest() 
    {
        ClienteEntity entity = listTest.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntityTest = factory.manufacturePojo(ClienteEntity.class);

        newEntityTest.setId(entity.getId());
        
        clientePersistence.update(newEntityTest);

        ClienteEntity entityPersistence = em.find(ClienteEntity.class, entity.getId());

        //  Assert.assertEquals(newClienteEntity.getName(), clienteEntity.getName());
        Assert.assertEquals(newEntityTest.getDocumentoIdentidad(), entityPersistence.getDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getTipoDocumentoIdentidad(), entityPersistence.getTipoDocumentoIdentidad());
        Assert.assertEquals(newEntityTest.getNombre(), entityPersistence.getNombre());
        Assert.assertEquals(newEntityTest.getApellidos(), entityPersistence.getApellidos());
        Assert.assertEquals(newEntityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(newEntityTest.getTelefono(), entityPersistence.getTelefono());
    }
}
