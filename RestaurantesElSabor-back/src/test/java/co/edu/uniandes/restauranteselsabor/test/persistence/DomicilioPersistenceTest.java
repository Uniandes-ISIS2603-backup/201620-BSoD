/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.DomicilioPersistence;
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
public class DomicilioPersistenceTest {
@Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private DomicilioPersistence domicilioPersistence;
    
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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
    }

    /**
     * 
     */
    private List<DomicilioEntity> listTest = new ArrayList<DomicilioEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
            em.persist(newEntity);
            listTest.add(newEntity);
        }
    }

    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity newEntityTest = factory.manufacturePojo(DomicilioEntity.class);
        
        DomicilioEntity createdEntity = domicilioPersistence.create(newEntityTest);
        Assert.assertNotNull(createdEntity);

        DomicilioEntity entityPersistence = em.find(DomicilioEntity.class, createdEntity.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(newEntityTest.getFactura(), entityPersistence.getFactura());
        Assert.assertEquals(newEntityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(newEntityTest.getPrecio(), entityPersistence.getPrecio());
        
        Assert.assertEquals(newEntityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void findAllTest() 
    {
        List<DomicilioEntity> listPersistence = domicilioPersistence.findAll();
        Assert.assertEquals(listTest.size(), listPersistence.size());
        
        for (DomicilioEntity entityPersistence : listPersistence) 
        {
            boolean found = false;
            for (DomicilioEntity entityTest : listTest) 
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
        DomicilioEntity entityTest = listTest.get(0);
        DomicilioEntity entityPersistence = domicilioPersistence.find(entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(entityTest.getFactura(), entityPersistence.getFactura());
        Assert.assertEquals(entityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(entityTest.getPrecio(), entityPersistence.getPrecio());
        
        Assert.assertEquals(entityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void deleteTest() 
    {
        DomicilioEntity entityTest = listTest.get(0);
        domicilioPersistence.delete(entityTest.getId());
        DomicilioEntity deletedEntity = em.find(DomicilioEntity.class, entityTest.getId());
        Assert.assertNull(deletedEntity);
    }
    
    @Test
    public void updateTest() 
    {
        DomicilioEntity entityTest = listTest.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        DomicilioEntity updatedEntityTest = factory.manufacturePojo(DomicilioEntity.class);

        updatedEntityTest.setId(entityTest.getId());
        
        domicilioPersistence.update(updatedEntityTest);

        DomicilioEntity entityPersistence = em.find(DomicilioEntity.class, entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(updatedEntityTest.getFactura(), entityPersistence.getFactura());
        Assert.assertEquals(updatedEntityTest.getDireccion(), entityPersistence.getDireccion());
        Assert.assertEquals(updatedEntityTest.getPrecio(), entityPersistence.getPrecio());
        
        Assert.assertEquals(updatedEntityTest.getName(), entityPersistence.getName());
    }
}
