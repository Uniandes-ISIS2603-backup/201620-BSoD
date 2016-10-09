/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteelsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
/**
 *
 * @author zl.castaneda10
 */

@Stateless
public class PlatoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(PlatoPersistence.class.getName());
    @PersistenceContext( unitName="Bsod")
    protected EntityManager em;
    
     public PlatoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando plato con id={0}", id);
        return em.find(PlatoEntity.class, id);
    }
     
     
    public List<PlatoEntity> findAll() {
        LOGGER.info("Consultando todos los platos");
        Query q = em.createQuery("select u from PlatoEntity u");
        return q.getResultList();
    }
    
    public List<PlatoEntity> findAllInEntity(Long sucursalId) {
        LOGGER.log(Level.INFO, "Consultando todos los platos de la sucursal id={0}", sucursalId);
        TypedQuery q = em.createQuery("select d from PlatoEntity d  where d.sucursal.id = :sucursalId", PlatoEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        return q.getResultList();
    }




    public PlatoEntity create(PlatoEntity entity) {
        LOGGER.info("Creando un plato nuevo");
        em.persist(entity);
        LOGGER.info("Plato creado");
        return entity;
    }

    public PlatoEntity update(PlatoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando plato con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el deptarment
     * crrespondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando plato con id={0}", id);
        PlatoEntity entity = em.find(PlatoEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
}
