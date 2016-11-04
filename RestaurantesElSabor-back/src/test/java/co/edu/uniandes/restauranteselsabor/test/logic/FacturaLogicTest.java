/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.FacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.FacturaPersistence;
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
 * @author cc.novoa11
 */

@RunWith(Arquillian.class)
public class FacturaLogicTest {
     SucursalEntity fatherEntity;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IFacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> facturaData = new ArrayList<FacturaEntity>();

    private List<SucursalEntity> sucursalData = new ArrayList<SucursalEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(IFacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        fatherEntity = factory.manufacturePojo(SucursalEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 4; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            entity.setSucursal(fatherEntity);
            em.persist(entity);
            facturaData.add(entity);
        }
    }
    //*****************************
    //Metodos de prueba
    //*****************************
    
    /**
     * Prueba para consultar la lista de facturas de un cliente
     */
    @Test
    public void getFacturasTest() throws RestauranteLogicException {
        List<FacturaEntity> list = facturaLogic.getFacturas(fatherEntity.getId());
        Assert.assertEquals(facturaData.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity ent : facturaData) {
                if (entity.getId().equals(ent.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
     /**
     * Prueba para consultar una factura que existe
     */
    @Test
    public void getFacturaTest1() throws RestauranteLogicException {
        FacturaEntity entity = facturaData.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(fatherEntity.getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
     /**
     * Prueba para consultar factura que no existe
     */
    @Test
    public void getFacturaTest2() throws RestauranteLogicException {
        FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity resultEntity = facturaLogic.getFactura(fatherEntity.getId(), entity.getId());
        Assert.assertNull(resultEntity);
    }
    /**
     * Prueba para crear una factura nueva
     */
    @Test
    public void createFacturaTest1() throws RestauranteLogicException{
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = facturaLogic.createFactura(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear una factura que ya existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void createFacturaTest2() throws Exception {
        FacturaEntity res = factory.manufacturePojo(FacturaEntity.class);
        res.setSucursal(fatherEntity);
        res.setId(facturaData.get(0).getId());
        FacturaEntity result = facturaLogic.createFactura(fatherEntity.getId(), res);
    }
     /**
     * Prueba para actualizar una factura
     */
    @Test
    public void updateFacturaTest1() throws RestauranteLogicException {
        FacturaEntity entity = facturaData.get(0);
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setId(entity.getId());
        facturaLogic.updateFactura(fatherEntity.getId(), newEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para actualizar una factura que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void updateFacturaest2() throws Exception {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        facturaLogic.updateFactura(fatherEntity.getId(), newEntity);
    }
     /**
     * Prueba para eliminar una factura que existe
     */
    @Test
    public void deleteFacturaTest1() throws RestauranteLogicException {
        FacturaEntity entity = facturaData.get(1);
        facturaLogic.deleteFactura(fatherEntity.getId(),entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba para eliminar uuna factura que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void deleteFacturaTest2() throws RestauranteLogicException {
        FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
        facturaLogic.deleteFactura(fatherEntity.getId(),entity.getId());
    }
    
}
