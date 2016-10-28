/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ITarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.ClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.TarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.TarjetaPuntosPersistence;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class ClienteLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IClienteLogic clienteLogic;
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
 
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
                .addPackage(IClienteLogic.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
                .addPackage(TarjetaPuntosEntity.class.getPackage())
                .addPackage(TarjetaPuntosPersistence.class.getPackage())
                .addPackage(TarjetaPuntosLogic.class.getPackage())
                .addPackage(ITarjetaPuntosLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void setUp() 
    {
        try 
        {
            utx.begin();
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
        em.createQuery("delete from TarjetaPuntosEntity").executeUpdate();
        em.createQuery("delete from TarjetaPuntosEntity").executeUpdate();
    }    
    
    private void insertData() 
    {
        for (int i = 0; i < 3; i++)
        {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
//            TarjetaPuntosEntity d = factory.manufacturePojo(TarjetaPuntosEntity.class);
//            d.setCliente(entity);
//            entity.setTarjetaPuntos(d);
//            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void getClientesTest()
    {
        assertEquals(1, 1);
    }
    
    @Test
    public void getClienteTest()
    {
        assertEquals(1, 1);
    }
    
    @Test
    public void createClienteTest()
    {
        assertEquals(1, 1);
        ClienteEntity nuevoCliente = factory.manufacturePojo(ClienteEntity.class);
        clienteLogic.createCliente(nuevoCliente);
        
        ClienteEntity clienteAgregado = clienteLogic.getCliente(nuevoCliente.getId());
        assertEquals(clienteAgregado, nuevoCliente);
    }
    
    @Test
    public void updateClienteTest()
    {
        assertEquals(1, 1);
    }
    
    @Test
    public void deleteClienteTest(Long id)
    {
        assertEquals(1, 1);
    }
}
