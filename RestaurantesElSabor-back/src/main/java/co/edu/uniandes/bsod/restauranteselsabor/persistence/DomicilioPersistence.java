/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author cc.novoa11
 */
@Stateless
public class DomicilioPersistence {
    private static final Logger LOGGER = Logger.getLogger(DomicilioPersistence.class.getName());

    @PersistenceContext(unitName = "Bsod")
    protected EntityManager em;

    public DomicilioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando domicilio con id={0}", id);
        return em.find(DomicilioEntity.class, id);
    }

    public List<DomicilioEntity> findAll() {
        LOGGER.info("Consultando todos los domicilios");
        Query q = em.createQuery("select u from DomicilioEntity u");
        return q.getResultList();
    }

    public DomicilioEntity create(DomicilioEntity entity) {
        LOGGER.info("Creando un domicilio nuevo");
        em.persist(entity);
        LOGGER.info("domicilio creado");
        return entity;
    }

    public DomicilioEntity update(DomicilioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando domicilio con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el deptarment
     * crrespondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando domicilio con id={0}", id);
        DomicilioEntity entity = em.find(DomicilioEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
}
