/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IMesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.MesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.PlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.SucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.*;
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
public class SucursalLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ISucursalLogic sucursalLogic;
    /**
     *
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *
     */
    @Inject
    private UserTransaction utx;

    /**
     *
     */
    private List<SucursalEntity> data = new ArrayList<SucursalEntity>();

    /**
     *
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalLogic.class.getPackage())
                .addPackage(ISucursalLogic.class.getPackage())
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaLogic.class.getPackage())
                .addPackage(IMesaLogic.class.getPackage())
                //.addPackage(ReservaEntity.class.getPackage())
                //.addPackage(ReservaLogic.class.getPackage())
                //.addPackage(ILogic.class.getPackage())
                //.addPackage(FacturaEntity.class.getPackage())
                //.addPackage(FacturaLogic.class.getPackage())
                //.addPackage(IFacturaLogic.class.getPackage())
                //.addPackage(PlatoEntity.class.getPackage())
                //.addPackage(PlatoLogic.class.getPackage())
                //.addPackage(IPlatoLogic.class.getPackage())
                .addPackage(Persistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from MesaEntity").executeUpdate();
        em.createQuery("delete from SucursalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            SucursalEntity entity = factory.manufacturePojo(SucursalEntity.class);
            for (int j = 0; j < 3; j++) {
        
            }
            for (MesaEntity d : entity.getMesas()) {
                d.setSucursal(entity);
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Sucursal con un nombre que no existe
     */
    @Test
    public void createSucursalTest1() {
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);
        for (MesaEntity d : newEntity.getMesas()) {
            d.setSucursal(newEntity);
        }

        SucursalEntity result = sucursalLogic.createSucursal(newEntity);
        Assert.assertNotNull(result);

        SucursalEntity entity = em.find(SucursalEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertNotNull(entity.getMesas());
        Assert.assertNotNull(result.getMesas());
        Assert.assertEquals(result.getMesas().size(), entity.getMesas().size());

        for (MesaEntity d : result.getMesas()) {
            boolean found = false;
            for (MesaEntity oracle : entity.getMesas()) {
                if (d.getName().equals(oracle.getName())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);

        }

    }

    /**
     * Prueba para crear un Sucursal con un nombre que ya existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void createSucursalTest2() throws Exception {
        SucursalEntity newEntity = factory.manufacturePojo(SucursalEntity.class);
        newEntity.setName(data.get(0).getName());
        SucursalEntity result = sucursalLogic.createSucursal(newEntity);
    }

    /**
     * Prueba para consultar la lista de Sucursales
     *
     *
     */
    @Test
    public void getSucursalesTest() {
        List<SucursalEntity> list = sucursalLogic.getSucursales();
        Assert.assertEquals(data.size(), list.size());
        for (SucursalEntity entity : list) {
            boolean found = false;
            for (SucursalEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Sucursal
     *
     *
     */
    @Test
    public void getSucursalTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity resultEntity = sucursalLogic.getSucursal(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Sucursal
     *
     *
     */
    @Test
    public void deleteSucursalTest() {
        SucursalEntity entity = data.get(1);
        sucursalLogic.deleteSucursal(entity.getId());
        SucursalEntity deleted = em.find(SucursalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Sucursal
     *
     *
     */
    @Test
    public void updateSucursalTest() {
        SucursalEntity entity = data.get(0);
        SucursalEntity pojoEntity = factory.manufacturePojo(SucursalEntity.class);

        pojoEntity.setId(entity.getId());

        sucursalLogic.updateSucursal(pojoEntity);

        SucursalEntity resp = em.find(SucursalEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
