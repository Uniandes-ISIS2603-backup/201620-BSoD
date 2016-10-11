/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistance;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.PlatoPersistence;
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
 * @author zl.castaneda10
 */
@RunWith(Arquillian.class)
public class PlatoPersistenceTest {
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    SucursalEntity fatherEntity;
    
    private List<PlatoEntity> data = new ArrayList<PlatoEntity>();
    
    @Inject
    private PlatoPersistence platoPersistence;
    
    @PersistenceContext
    private EntityManager em;

    
    @Inject
    UserTransaction utx;
    
    
     
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
        em.createQuery("delete  from PlatoEntity").executeUpdate();
        em.createQuery("delete  from SucursalEntity").executeUpdate();
    }

  /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una compañía y luego le adiciona tres departamentos.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            entity.setSucursal(fatherEntity);
            data.add(entity);
            em.persist(entity);
        }

    }    
    
    @Test
    public void createPlatoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);
        newEntity.setSucursal(fatherEntity);
        PlatoEntity result = platoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PlatoEntity entity = em.find(PlatoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

      /**
     * Prueba para consultar la lista de platos
     */
    @Test
    public void getPlatosInSucursalTest() {
        List<PlatoEntity> list = platoPersistence.findAllInEntity(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (PlatoEntity ent : list) {
            boolean found = false;
            for (PlatoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void deleteDepartmentTest() {
        PlatoEntity entity = data.get(0);
        platoPersistence.delete(entity.getId());
        PlatoEntity deleted = em.find(PlatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateDepartmentTest() {
        PlatoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);

        newEntity.setId(entity.getId());

        platoPersistence.update(newEntity);

        PlatoEntity resp = em.find(PlatoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }

}
