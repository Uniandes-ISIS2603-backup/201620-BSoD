/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IPlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.PlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.SucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
public class PlatoLogicTest {
    SucursalEntity fatherEntity;
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    private IPlatoLogic platoLogic;
    
    @PersistenceContext 
    private EntityManager em;
    
    @Inject 
    private UserTransaction utx;
    
    private List<PlatoEntity> platoData = new ArrayList<PlatoEntity>();
    
    private List<SucursalEntity> sucursalData = new ArrayList<SucursalEntity>();
    
    @Deployment 
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoLogic.class.getPackage())
                .addPackage(IPlatoLogic.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalLogic.class.getPackage())
                .addPackage(ISucursalLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml"); 
    }
    
    @Before  
    public void setUp(){
        try{
            utx.begin();
            clearData();
            insertData();
            utx.commit();
            
        }catch( Exception e){
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    
    private void clearData (){
        em.createQuery("delete from PlatoEntity").executeUpdate();
        em.createQuery("dellete from sucursalEntity").executeUpdate();
        
    }
  
    
    private void insertData(){
        
       for (int i = 0; i < 3; i++) {
            SucursalEntity sucursal = factory.manufacturePojo(SucursalEntity.class);
            System.out.println(sucursal+"++++++++"); 
           em.persist(sucursal);
            sucursalData.add(sucursal);
        }  
       
        fatherEntity=sucursalData.get(0);
        
          for (int i = 0; i < 3; i++) {
            PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class);
            entity.setSucursal(sucursalData.get(0));
            
           

            em.persist(entity);
            platoData.add(entity);
        }
    }
    
     
    /**
     * Prueba para crear un Department
     *
     *
     */
    @Test
    public void createPlatoTest1() throws RestauranteLogicException{
        PlatoEntity newEntity = factory.manufacturePojo(PlatoEntity.class);
        PlatoEntity result = platoLogic.crearPlato(newEntity);
        Assert.assertNotNull(result);
        PlatoEntity entity = em.find(PlatoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
 /**
     * Prueba para crear un plato con un precio superior a 100000
     */
    @Test(expected = RestauranteLogicException.class)
    public void createPlatoTest2() throws Exception {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setSucursal(fatherEntity);
        plato.setPrecio(200000);
        PlatoEntity result = platoLogic.crearPlato(plato);
    }
    /**
     * Prueba para consultar la lista de platos
     *
     *
     */
    @Test
    public void getPlatoTest() {
        Assert.fail(""+sucursalData.get(0)+";"+sucursalData.get(1)+";"+sucursalData.get(2));
        List<PlatoEntity> list = platoLogic.darPlatos(fatherEntity.getId());
        Assert.assertEquals(platoData.size(), list.size());
        for (PlatoEntity entity : list) {
            boolean found = false;
            for (PlatoEntity storedEntity : platoData) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Department
     *
     *
     */
    @Test
    public void getDepartmentTest() {
        PlatoEntity entity = platoData.get(0);
        PlatoEntity resultEntity = platoLogic.darPlato(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un plato
     *
     *
     */
    @Test
    public void deletePlatoTest() {
        PlatoEntity entity =platoData.get(1);
        platoLogic.eliminarPlato(entity.getId());
        PlatoEntity deleted = em.find(PlatoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un plato
     *
     *
     */
    @Test
    public void updatePlatoTest() {
        PlatoEntity entity = platoData.get(0);
        PlatoEntity pojoEntity = factory.manufacturePojo(PlatoEntity.class);

        pojoEntity.setId(entity.getId());

        platoLogic.actualizarPlato(pojoEntity);

        PlatoEntity resp = em.find(PlatoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }

  
    
}
