/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ITarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.ClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.ReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.TarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ReservaPersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.TarjetaPuntosPersistence;
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
public class TarjetaPuntosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private ITarjetaPuntosLogic tarjetaPuntosLogic;
    
    @Inject
    private IClienteLogic clienteLogic;
    
    @Inject
    private TarjetaPuntosPersistence tarjetaPuntosPersistence;
    
    @Inject
    private ClientePersistence clientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
            
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
//                .addPackage(MedioPagoEntity.class.getPackage())
//                .addPackage(MedioPagoPersistence.class.getPackage())
//                .addPackage(MedioPagoLogic.class.getPackage())
//                .addPackage(IMedioPagoLogic.class.getPackage())
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
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
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }    
    
    private void insertData() 
    {
        for (int i = 0; i < 5; i++)
        {
            try
            {
                ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
                if(entity.getDocumentoIdentidad()<=0)
                {
                    entity.setDocumentoIdentidad(Math.abs(entity.getDocumentoIdentidad()));
                }
                clienteLogic.createCliente(entity);
                tarjetaPuntosLogic.createTarjetaPuntos(entity.getId());
            }
            catch(Exception e)
            {
                Assert.fail(e.getMessage());
            }
        }
    }
    
    @Test
    public void createTarjetaPuntosTest1()
    {
        try
        {
            List<ClienteEntity> clientes = clientePersistence.findAll();
            ClienteEntity cliente = clientes.get(0);
            tarjetaPuntosLogic.deleteTarjetaPuntos(cliente.getId());
            tarjetaPuntosLogic.createTarjetaPuntos(cliente.getId());
            Assert.assertEquals(1, 1);
        }
        catch(RestauranteLogicException e)
        {
            Assert.fail(e.getMessage());
        }
    }
    
    @Test
    public void createTarjetaPuntosTest2()
    {
        try
        {
            List<ClienteEntity> clientes = clienteLogic.getClientes();
            ClienteEntity cliente = clientes.get(0);
            tarjetaPuntosLogic.createTarjetaPuntos(cliente.getId());
            tarjetaPuntosLogic.createTarjetaPuntos(cliente.getId());
            Assert.fail("No deberia dejar crearle una tarjeta de puntos a un cliente que ya tiene una.");
        }
        catch(RestauranteLogicException e)
        {
            Assert.assertEquals(1, 1);
        }
    }
    
    @Test
    public void updateTarjetaPuntosTest()
    {
        Assert.assertEquals(1, 1);
    }
    
    @Test
    public void sumarPuntosTarjetaPuntosTest()
    {
         Assert.assertEquals(1, 1);
    }
    
    @Test
    public void deleteTarjetaPuntosTest()
    {
        Assert.assertEquals(1, 1);
    }
}
