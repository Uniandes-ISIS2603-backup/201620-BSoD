/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.restauranteselsabor.test.logic;

import co.edu.uniandes.bsod.restauranteselsabor.api.IDomicilioLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.DomicilioLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.FacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.SucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.DomicilioPersistence;
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
public class DomicilioLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private IDomicilioLogic domicilioLogic;
    
    @Inject
    private DomicilioPersistence domicilioPersistence;
    
    @Inject
    private IFacturaLogic facturaLogic;
    
    @Inject
    private FacturaPersistence facturaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DomicilioEntity> domicilioData = new ArrayList<DomicilioEntity>();

    private List<FacturaEntity> facturaData = new ArrayList<FacturaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DomicilioEntity.class.getPackage())
                .addPackage(DomicilioLogic.class.getPackage())
                .addPackage(IDomicilioLogic.class.getPackage())
                .addPackage(DomicilioPersistence.class.getPackage())
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(IFacturaLogic.class.getPackage())
                .addPackage(SucursalEntity.class.getPackage())
                .addPackage(SucursalPersistence.class.getPackage())
                .addPackage(SucursalLogic.class.getPackage())
                .addPackage(ISucursalLogic.class.getPackage())
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
        em.createQuery("delete from DomicilioEntity").executeUpdate();
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        
        
        for (int i = 0; i < 3; i++) {
            FacturaEntity factura = factory.manufacturePojo(FacturaEntity.class);
            em.persist(factura);
            facturaData.add(factura);
        }
        for (int i = 0; i < 3; i++) {
            DomicilioEntity entity = factory.manufacturePojo(DomicilioEntity.class);
            entity.setFactura(facturaData.get(0));
            

            em.persist(entity);
            domicilioData.add(entity);
        }
    }
    /**
     * Prueba para crear un Domicilio
     *
     * 
     */
    @Test
    public void createDomicilioTest() throws RestauranteLogicException {
        DomicilioEntity newEntity = factory.manufacturePojo(DomicilioEntity.class);
        DomicilioEntity result = domicilioLogic.createDomicilio(newEntity);
        Assert.assertNotNull(result);
        DomicilioEntity entity = em.find(DomicilioEntity.class, result.getId());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
   
    /**
     * Prueba para consultar un Domicilio
     *
     * 
     */
    @Test
    public void getDomicilioTest() {
        DomicilioEntity entity = domicilioData.get(0);
        DomicilioEntity resultEntity = domicilioLogic.getDomicilio(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getName(), resultEntity.getName());
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para eliminar un Domicilio
     *
     * 
     */
    @Test
    public void deleteDomicilioTest() throws RestauranteLogicException {
        DomicilioEntity entity = domicilioData.get(1);
        domicilioLogic.deleteDomicilio(entity.getId());
        DomicilioEntity deleted = em.find(DomicilioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Domicilio
     *
     * 
     */
    @Test
    public void updateDomicilioTest() throws RestauranteLogicException {
        DomicilioEntity entity = domicilioData.get(0);
        DomicilioEntity pojoEntity = factory.manufacturePojo(DomicilioEntity.class);

        pojoEntity.setId(entity.getId());

        domicilioLogic.updateDomicilio(pojoEntity);

        DomicilioEntity resp = em.find(DomicilioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getName(), resp.getName());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
}
