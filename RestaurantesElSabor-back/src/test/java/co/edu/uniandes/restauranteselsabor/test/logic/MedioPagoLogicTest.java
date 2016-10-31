/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IMedioPagoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.MedioPagoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.MedioPagoPersistence;
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
 * @author aj.paredes10
 */
@RunWith(Arquillian.class)
public class MedioPagoLogicTest {
    
    ClienteEntity fatherEntity;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IMedioPagoLogic medioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MedioPagoEntity> medioData = new ArrayList<MedioPagoEntity>();

    private List<ClienteEntity> clienteData = new ArrayList<ClienteEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoLogic.class.getPackage())
                .addPackage(IMedioPagoLogic.class.getPackage())
                .addPackage(MedioPagoPersistence.class.getPackage())
                .addPackage(ClienteEntity.class.getPackage())
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
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        fatherEntity = factory.manufacturePojo(ClienteEntity.class);
        fatherEntity.setId(1L);
        em.persist(fatherEntity);
        for (int i = 0; i < 4; i++) {
            MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
            entity.setCliente(fatherEntity);
            em.persist(entity);
            medioData.add(entity);
        }
    }
    //*****************************
    //Metodos de prueba
    //*****************************
    
    /**
     * Prueba para consultar la lista de medios de pago de un cliente
     */
    @Test
    public void getMediosPagoTest() throws RestauranteLogicException {
        List<MedioPagoEntity> list = medioLogic.darMedios(fatherEntity.getId());
        Assert.assertEquals(medioData.size(), list.size());
        for (MedioPagoEntity entity : list) {
            boolean found = false;
            for (MedioPagoEntity ent : medioData) {
                if (entity.getId().equals(ent.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para consultar un medio de pago que existe
     */
    @Test
    public void getMedioPagoTest1() throws RestauranteLogicException {
        MedioPagoEntity entity = medioData.get(0);
        MedioPagoEntity resultEntity = medioLogic.darMedio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
     /**
     * Prueba para consultar un medio de pago que no existe
     */
    @Test
    public void getMedioPagoTest2() throws RestauranteLogicException {
        MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
        MedioPagoEntity resultEntity = medioLogic.darMedio(entity.getId());
        Assert.assertNull(resultEntity);
    }
    /**
     * Prueba para crear un medio de pago nuevo
     */
    @Test
    public void createMedioPagoTest1() throws RestauranteLogicException{
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        MedioPagoEntity result = medioLogic.crearMedio(newEntity);
        Assert.assertNotNull(result);
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear un medio de pago que ya existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void createMedioPagoTest2() throws Exception {
        MedioPagoEntity newMedio = factory.manufacturePojo(MedioPagoEntity.class);
        newMedio.setCliente(fatherEntity);
        newMedio.setId(medioData.get(0).getId());
        MedioPagoEntity result = medioLogic.crearMedio(newMedio);
    }
    /**
     * Prueba para actualizar un medio de pago
     */
    @Test
    public void updateMedioPagoTest1() throws RestauranteLogicException {
        MedioPagoEntity entity = medioData.get(0);
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        newEntity.setId(entity.getId());
        medioLogic.actualizarMedio(newEntity);
        MedioPagoEntity resp = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para actualizar un medio de pago que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void updateMedioPagoTest2() throws Exception {
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);
        medioLogic.actualizarMedio(newEntity);
    }
    /**
     * Prueba para eliminar un medio de pago que existe
     */
    @Test
    public void deleteMedioPagoTest1() throws RestauranteLogicException {
        MedioPagoEntity entity = medioData.get(1);
        medioLogic.eliminarMedio(entity.getId());
        MedioPagoEntity deleted = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba para eliminar un medio de pago que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void deleteMedioPagoTest2() throws RestauranteLogicException {
        MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
        medioLogic.eliminarMedio(entity.getId());
    }
}
