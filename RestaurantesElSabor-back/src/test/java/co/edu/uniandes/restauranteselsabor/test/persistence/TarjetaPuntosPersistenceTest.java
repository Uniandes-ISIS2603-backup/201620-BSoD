/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
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
public class TarjetaPuntosPersistenceTest 
{
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaPuntosEntity.class.getPackage())
                .addPackage(TarjetaPuntosPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private TarjetaPuntosPersistence persistence;
    
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
        em.createQuery("delete from TarjetaPuntosEntity").executeUpdate();
    }

    /**
     * 
     */
    private List<TarjetaPuntosEntity> listTest = new ArrayList<TarjetaPuntosEntity>();

    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            TarjetaPuntosEntity newEntity = factory.manufacturePojo(TarjetaPuntosEntity.class);
            em.persist(newEntity);
            listTest.add(newEntity);
        }
    }

    @Test
    public void createTest() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPuntosEntity newEntityTest = factory.manufacturePojo(TarjetaPuntosEntity.class);
        
        TarjetaPuntosEntity createdEntity = persistence.create(newEntityTest);
        Assert.assertNotNull(createdEntity);

        TarjetaPuntosEntity entityPersistence = em.find(TarjetaPuntosEntity.class, createdEntity.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(newEntityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(newEntityTest.getFechaCaducidad(), entityPersistence.getFechaCaducidad());
        Assert.assertEquals(newEntityTest.getAcumulado(), entityPersistence.getAcumulado());
        
        Assert.assertEquals(newEntityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void findAllTest() 
    {
        List<TarjetaPuntosEntity> listPersistence = persistence.findAll();
        Assert.assertEquals(listTest.size(), listPersistence.size());
        
        for (TarjetaPuntosEntity entityPersistence : listPersistence) 
        {
            boolean found = false;
            for (TarjetaPuntosEntity entityTest : listTest) 
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
        TarjetaPuntosEntity entityTest = listTest.get(0);
        TarjetaPuntosEntity entityPersistence = persistence.find(entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(entityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(entityTest.getFechaCaducidad(), entityPersistence.getFechaCaducidad());
        Assert.assertEquals(entityTest.getAcumulado(), entityPersistence.getAcumulado());
        
        Assert.assertEquals(entityTest.getName(), entityPersistence.getName());
    }

    @Test
    public void deleteTest() 
    {
        TarjetaPuntosEntity entityTest = listTest.get(0);
        persistence.delete(entityTest.getId());
        TarjetaPuntosEntity deletedEntity = em.find(TarjetaPuntosEntity.class, entityTest.getId());
        Assert.assertNull(deletedEntity);
    }
    
    @Test
    public void updateTest() 
    {
        TarjetaPuntosEntity entityTest = listTest.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaPuntosEntity updatedEntityTest = factory.manufacturePojo(TarjetaPuntosEntity.class);

        updatedEntityTest.setId(entityTest.getId());
        
        persistence.update(updatedEntityTest);

        TarjetaPuntosEntity entityPersistence = em.find(TarjetaPuntosEntity.class, entityTest.getId());
        
        Assert.assertNotNull(entityPersistence);
        Assert.assertEquals(updatedEntityTest.getCliente(), entityPersistence.getCliente());
        Assert.assertEquals(updatedEntityTest.getFechaCaducidad(), entityPersistence.getFechaCaducidad());
        Assert.assertEquals(updatedEntityTest.getAcumulado(), entityPersistence.getAcumulado());
        
        Assert.assertEquals(updatedEntityTest.getName(), entityPersistence.getName());
    }
}
