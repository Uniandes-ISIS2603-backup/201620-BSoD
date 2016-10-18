/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.FacturaPersistence;
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
 * @author cc.novoa11
 */

@RunWith(Arquillian.class)
public class FacturaPersistenceTest {
@Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private FacturaPersistence facturaPersistence;
    
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * 
     */
    private List<FacturaEntity> listTest = new ArrayList<FacturaEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(newEntity);
            listTest.add(newEntity);
        }
    }

    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntityTest = factory.manufacturePojo(FacturaEntity.class);
        
        FacturaEntity createdEntity = facturaPersistence.create(newEntityTest);
        Assert.assertNotNull(createdEntity);

        FacturaEntity entityPersistence = em.find(FacturaEntity.class, createdEntity.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(newEntityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(newEntityTest.getDomicilio(), entityPersistence.getDomicilio());
        Assert.assertEquals(newEntityTest.getFecha(), entityPersistence.getFecha());
        Assert.assertEquals(newEntityTest.getSucursal(), entityPersistence.getSucursal());
        Assert.assertEquals(newEntityTest.getTotal(), entityPersistence.getTotal());
        
        Assert.assertEquals(newEntityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void findAllTest() 
    {
        List<FacturaEntity> listPersistence = facturaPersistence.findAll();
        Assert.assertEquals(listTest.size(), listPersistence.size());
        
        for (FacturaEntity entityPersistence : listPersistence) 
        {
            boolean found = false;
            for (FacturaEntity entityTest : listTest) 
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
        FacturaEntity entityTest = listTest.get(0);
        FacturaEntity entityPersistence = facturaPersistence.find(entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(entityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(entityTest.getDomicilio(), entityPersistence.getDomicilio());
        Assert.assertEquals(entityTest.getFecha(), entityPersistence.getFecha());
        Assert.assertEquals(entityTest.getSucursal(), entityPersistence.getSucursal());
        Assert.assertEquals(entityTest.getTotal(), entityPersistence.getTotal());
        
        Assert.assertEquals(entityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void deleteTest() 
    {
        FacturaEntity entityTest = listTest.get(0);
        facturaPersistence.delete(entityTest.getId());
        FacturaEntity deletedEntity = em.find(FacturaEntity.class, entityTest.getId());
        Assert.assertNull(deletedEntity);
    }
    
    @Test
    public void updateTest() 
    {
        FacturaEntity entityTest = listTest.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity updatedEntityTest = factory.manufacturePojo(FacturaEntity.class);

        updatedEntityTest.setId(entityTest.getId());
        
        facturaPersistence.update(updatedEntityTest);

        FacturaEntity entityPersistence = em.find(FacturaEntity.class, entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(updatedEntityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(updatedEntityTest.getDomicilio(), entityPersistence.getDomicilio());
        Assert.assertEquals(updatedEntityTest.getFecha(), entityPersistence.getFecha());
        Assert.assertEquals(updatedEntityTest.getSucursal(), entityPersistence.getSucursal());
        Assert.assertEquals(updatedEntityTest.getTotal(), entityPersistence.getTotal());
        
        Assert.assertEquals(updatedEntityTest.getName(), entityPersistence.getName());
    }
}