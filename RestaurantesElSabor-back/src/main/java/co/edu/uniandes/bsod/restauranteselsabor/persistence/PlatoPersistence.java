/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.persistence;

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

    public List<PlatoEntity> findAllInPlato(Long platoId) {
        LOGGER.log(Level.INFO, "Consultando todos los departments de la company id={0}", platoId);
        TypedQuery q = em.createQuery("select d from PlatoEntity d  where d.plato.id = :platoId", PlatoEntity.class);
        q = q.setParameter("companyId", platoId);
        return q.getResultList();
    }

    public PlatoEntity create(PlatoEntity entity) {
        LOGGER.info("Creando un department nuevo");
        em.persist(entity);
        LOGGER.info("Department creado");
        return entity;
    }

    public PlatoEntity update(PlatoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando department con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el deptarment
     * crrespondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando department con id={0}", id);
        PlatoEntity entity = em.find(PlatoEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
}
