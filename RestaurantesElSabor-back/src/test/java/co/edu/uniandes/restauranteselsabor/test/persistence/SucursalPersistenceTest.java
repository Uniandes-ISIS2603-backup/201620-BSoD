/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.SucursalPersistence;
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
 * @author af.pinzon10
 */
@RunWith(Arquillian.class)
public class SucursalPersistenceTest {
    /**
     *
     * @return el jar que va a desplegar para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Inject
    private SucursalPersistence sucursalPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SucursalEntity> data = new ArrayList<SucursalEntity>();

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            SucursalEntity entity = factory.manufacturePojo(SucursalEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una sucursal.
     */
    @Test
    public void createSucursalTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);

        SucursalEntity result = sucursalPersistence.create(newEntity);

        Assert.assertNotNull(result);
        SucursalEntity entity = em.find(SucursalEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de sucursales.
     *
     *
     */
    @Test
    public void getSucursalesTest() {
        List<SucursalEntity> list = sucursalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SucursalEntity ent : list) {
            boolean found = false;
            for (SucursalEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una sucursal.
     */
    @Test
    public void getSucursalTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity newEntity = sucursalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para consultar una sucursal.
     */
    @Test
    public void getSucursalByNameTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity newEntity = sucursalPersistence.findByName(entity.getName());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar una sucursal.
     */
    @Test
    public void deleteSucursalTest() {
        SucursalEntity entity = data.get(0);
        sucursalPersistence.delete(entity.getId());
        SucursalEntity deleted = em.find(SucursalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    
    

    /**
     * Prueba para actualizar una sucursal.
     */
    @Test
    public void updateSucursalTest() {
        SucursalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);

        newEntity.setId(entity.getId());

        sucursalPersistence.update(newEntity);

        SucursalEntity resp = em.find(SucursalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
