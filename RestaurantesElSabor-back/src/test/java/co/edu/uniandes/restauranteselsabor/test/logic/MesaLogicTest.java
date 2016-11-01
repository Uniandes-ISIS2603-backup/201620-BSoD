/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IMesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.MesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
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
public class MesaLogicTest {
    

    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private IMesaLogic mesaLogic;

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
    private List<MesaEntity> data = new ArrayList<MesaEntity>();

    /**
     * 
     */
    private List<SucursalEntity> sucursalData = new ArrayList<>();

    /**
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MesaEntity.class.getPackage())
                .addPackage(MesaLogic.class.getPackage())
                .addPackage(IMesaLogic.class.getPackage())
                .addPackage(MesaPersistence.class.getPackage())
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
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     *
     * 
     */
    private void insertData() {
        
        
        for (int i = 0; i < 3; i++) {
            SucursalEntity sucursal = factory.manufacturePojo(SucursalEntity.class);
            em.persist(sucursal);
            sucursalData.add(sucursal);
        }
        for (int i = 0; i < 3; i++) {
            MesaEntity entity = factory.manufacturePojo(MesaEntity.class);
            entity.setSucursal(sucursalData.get(0));
            

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Prueba para crear un Mesa
     *
     * 
     */
    @Test
    public void createMesaTest() {
        MesaEntity newEntity = factory.manufacturePojo(MesaEntity.class);
        MesaEntity result = mesaLogic.createMesa(newEntity);
        Assert.assertNotNull(result);
        MesaEntity entity = em.find(MesaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de Mesas
     *
     * 
     */
    @Test
    public void getMesasTest() {
        List<MesaEntity> list = mesaLogic.getMesas();
        Assert.assertEquals(data.size(), list.size());
        for (MesaEntity entity : list) {
            boolean found = false;
            for (MesaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    
    /**
     * Prueba para consultar un Mesa
     *
     * 
     */
    @Test
    public void getMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity resultEntity = mesaLogic.getMesa(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Mesa
     *
     * 
     */
    @Test
    public void deleteMesaTest() {
        MesaEntity entity = data.get(1);
        mesaLogic.deleteMesa(entity.getId());
        MesaEntity deleted = em.find(MesaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Mesa
     *
     * 
     */
    @Test
    public void updateMesaTest() {
        MesaEntity entity = data.get(0);
        MesaEntity pojoEntity = factory.manufacturePojo(MesaEntity.class);

        pojoEntity.setId(entity.getId());

        mesaLogic.updateMesa(pojoEntity);

        MesaEntity resp = em.find(MesaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
