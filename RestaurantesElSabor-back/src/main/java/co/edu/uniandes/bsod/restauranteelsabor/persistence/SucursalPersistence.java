/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteelsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author af.pinzon10
 */
@Stateless
public class SucursalPersistence {
    private static final Logger LOGGER = Logger.getLogger(SucursalPersistence.class.getName());

    @PersistenceContext(unitName = "Bsod")
    protected EntityManager em;

    public SucursalEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando sucursal con id={0}", id);
        return em.find(SucursalEntity.class, id);
    }
    
    public SucursalEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando sucursal con name = {0}", name);
        TypedQuery<SucursalEntity> q
                = em.createQuery("select u from SucursalEntity u where u.name = :name", SucursalEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }

    public List<SucursalEntity> findAll() {
        LOGGER.info("Consultando todas las sucursales");
        Query q = em.createQuery("select u from SucursalEntity u");
        return q.getResultList();
    }

    public SucursalEntity create(SucursalEntity entity) {
        LOGGER.info("Creando una sucursal nueva");
        em.persist(entity);
        LOGGER.info("Sucursal creada");
        return entity;
    }

    public SucursalEntity update(SucursalEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando sucursal con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando sucursal con id={0}", id);
        SucursalEntity entity = em.find(SucursalEntity.class, id);
        em.remove(entity);
    }
}
