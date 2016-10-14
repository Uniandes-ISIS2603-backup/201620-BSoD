/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.*;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.MesaPersistence;
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
public class MesaPersistenceTest {
    /**
     * @return el jar que se desplegará para la prueba
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Sucursal que contiene las mesas. La relación entre sucursal y
     * mesa es "composite"
     */
    SucursalEntity fatherEntity;

    /**
     * Lista de las mesas que serán utilizadas en las pruebas. La
     * relación entre sucursal y mesa es "composite"
     */
    private List<MesaEntity> data = new ArrayList<>();

    @Inject
    private MesaPersistence mesaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    /**
     * Configuración inicial de cada método de prueba.
     *
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
        em.createQuery("delete  from MesaEntity").executeUpdate();
        em.createQuery("delete  from SucursalEntity").executeUpdate();
    }

    /**
     * Para el correcto funcionamiento de las pruebas, inserta los datos
     * iniciales en la base de datos utilizando un manejador de persistencia.
     *
     * Crea una sucursal y luego le adiciona tres mesas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 3; i++) {
            MesaEntity entity = factory.manufacturePojo(MesaEntity.class);
            entity.setSucursal(fatherEntity);
            data.add(entity);
            em.persist(entity);
        }

    }

    /**
     * Prueba para crear una mesa.
     *
     *
     */
    @Test
    public void createMesaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);
        newEntity.setSucursal(fatherEntity);
        MesaEntity result = mesaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        MesaEntity entity = em.find(MesaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
    }

    /**
     * Prueba para consultar la lista de mesas.
     *
     *
     */
    @Test
    public void getMesasInSucursalesTest() {
        List<MesaEntity> list = mesaPersistence.findAllInSucursal(fatherEntity.getId());
        Assert.assertEquals(data.size(), list.size());
        for (MesaEntity ent : list) {
            boolean found = false;
            for (MesaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una mesa.
     *
     *
     */
    @Test
    public void getMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity newEntity = mesaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
    }

    /**
     * Prueba para eliminar una mesa.
     *
     *
     */
    @Test
    public void deleteMesaTest() {
        MesaEntity entity = data.get(0);
        mesaPersistence.delete(entity.getId());
        MesaEntity deleted = em.find(MesaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una mesa.
     *
     *
     */
    @Test
    public void updateMesaTest() {
        MesaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);

        newEntity.setId(entity.getId());

        mesaPersistence.update(newEntity);

        MesaEntity resp = em.find(MesaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
