/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
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
public class MedioPagoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MedioPagoEntity.class.getPackage())
                .addPackage(MedioPagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    MedioPagoPersistence medioPersistence;
    
    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<MedioPagoEntity> data = new ArrayList<MedioPagoEntity>();
    
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
        em.createQuery("delete from MedioPagoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 4; i++) {
            MedioPagoEntity entity = factory.manufacturePojo(MedioPagoEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un medio de pago.
     */
    @Test
    public void createMedioDePagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);

        MedioPagoEntity result = medioPersistence.create(newEntity);

        Assert.assertNotNull(result);
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para consultar la lista de reservas.
     */
    @Test
    public void getMediosDePagoTest() {
        List<MedioPagoEntity> list = medioPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (MedioPagoEntity ent : list) {
            boolean found = false;
            for (MedioPagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una reserva por id.
     */
    @Test
    public void getMedioDePagoTest() {
        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity newEntity = medioPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    @Test
    public void getMedioDePagoPorNombreTest(){
        
        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity ent = medioPersistence.findByName(entity.getName());
        Assert.assertNotNull(ent);
        Assert.assertEquals(entity.getId(), ent.getId());
    }
    
    @Test
    public void deleteMedioDePagoTest(){
        MedioPagoEntity entity = data.get(0);
        MedioPagoEntity ent = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertNotNull(ent);
        medioPersistence.delete(entity.getId());
        ent = em.find(MedioPagoEntity.class, entity.getId());
        Assert.assertNull(ent);
    }
    
    /**
     * Prueba para actualizar un medio de pago.
     */
    @Test
    public void updateMedioDePagoTest() {
        MedioPagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MedioPagoEntity newEntity = factory.manufacturePojo(MedioPagoEntity.class);

        newEntity.setId(entity.getId());

        medioPersistence.update(newEntity);

        MedioPagoEntity resp = em.find(MedioPagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
    }
}
