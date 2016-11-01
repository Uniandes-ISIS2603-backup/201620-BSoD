/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;


import co.edu.uniandes.bsod.restauranteselsabor.api.IReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.ReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Date;
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
public class ReservaLogicTest {
    ClienteEntity fatherEntity;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IReservaLogic reservaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ReservaEntity> reservaData = new ArrayList<ReservaEntity>();

    private List<ClienteEntity> clienteData = new ArrayList<ClienteEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(IReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
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
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            entity.setCliente(fatherEntity);
            em.persist(entity);
            reservaData.add(entity);
        }
    }
    //*****************************
    //Metodos de prueba
    //*****************************
    
    /**
     * Prueba para consultar la lista de reservas de un cliente
     */
    @Test
    public void getReservasTest() throws RestauranteLogicException {
        List<ReservaEntity> list = reservaLogic.getReservas(fatherEntity.getId());
        Assert.assertEquals(reservaData.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity ent : reservaData) {
                if (entity.getId().equals(ent.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
     /**
     * Prueba para consultar una reserva que existe
     */
    @Test
    public void getReservaTest1() throws RestauranteLogicException {
        ReservaEntity entity = reservaData.get(0);
        ReservaEntity resultEntity = reservaLogic.getReserva(fatherEntity.getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }
     /**
     * Prueba para consultar un medio de pago que no existe
     */
    @Test
    public void getReservaTest2() throws RestauranteLogicException {
        ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity resultEntity = reservaLogic.getReserva(fatherEntity.getId(), entity.getId());
        Assert.assertNull(resultEntity);
    }
    /**
     * Prueba para crear una reserva nueva
     */
    @Test
    public void createReservaTest1() throws RestauranteLogicException{
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaLogic.createReserva(fatherEntity.getId(), newEntity);
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    /**
     * Prueba para crear una reserva que ya existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void createReservaTest2() throws Exception {
        ReservaEntity res = factory.manufacturePojo(ReservaEntity.class);
        res.setCliente(fatherEntity);
        res.setId(reservaData.get(0).getId());
        ReservaEntity result = reservaLogic.createReserva(fatherEntity.getId(), res);
    }
     /**
     * Prueba para actualizar una reserva
     */
    @Test
    public void updateReservaTest1() throws RestauranteLogicException {
        ReservaEntity entity = reservaData.get(0);
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setId(entity.getId());
        reservaLogic.updateReserva(fatherEntity.getId(), newEntity);
        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
    /**
     * Prueba para actualizar una reserva que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void updateReservaest2() throws Exception {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        reservaLogic.updateReserva(fatherEntity.getId(), newEntity);
    }
     /**
     * Prueba para eliminar una reserva que existe
     */
    @Test
    public void deleteReservaTest1() throws RestauranteLogicException {
        ReservaEntity entity = reservaData.get(1);
        reservaLogic.deleteReserva(fatherEntity.getId(),entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba para eliminar uuna reserva que no existe
     */
    @Test(expected = RestauranteLogicException.class)
    public void deleteReservaTest2() throws RestauranteLogicException {
        ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
        reservaLogic.deleteReserva(fatherEntity.getId(),entity.getId());
    }
    /**
     * Prueba para buscar las reservas en una fecha dada
     */
    @Test
    public void getReservasEnFechaTest1() {
       Date fecha = new Date(2016, 10, 25);
       Date fecha2 = new Date(1990, 6, 11);
       for (int i=0; i<reservaData.size(); i++) {
            if (i==0 || i==2) {
                reservaData.get(i).setFecha(fecha);
            }
            else{
                reservaData.get(i).setFecha(fecha2);
            }
        }
        List<ReservaEntity> lista = reservaLogic.getReservasEnFecha(fecha);
        //Assert.assertEquals(lista.size(), 2);
        System.out.println("size"+lista.size());
        for (ReservaEntity ent : lista) {
            boolean found = false;
            System.out.println("******"+ ent.getId()+ " fecha: "+ ent.getFecha());
            if(ent.getFecha().getYear()== fecha.getYear() && ent.getFecha().getMonth()== fecha.getMonth() && ent.getFecha().getDate()== fecha.getDate())
            {
                found = true;
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para buscar las reservas en una fecha dada
     */
    @Test
    public void getReservasEnFechaTest2() {
       Date fecha = new Date(2016, 10, 25);
       Date fecha2 = new Date(1990, 6, 11);
       for (int i=0; i<reservaData.size(); i++) {
            if (i==0 || i==2) {
                reservaData.get(i).setFecha(fecha);
            }
            else{
                reservaData.get(i).setFecha(fecha2);
            }
        }
        List<ReservaEntity> lista = reservaLogic.getReservasEnFecha(fecha);
        Assert.assertEquals(lista.size(), 0);
    }
}
