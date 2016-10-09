/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteelsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
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
public class MesaPersistence {
    private static final Logger LOGGER = Logger.getLogger(MesaPersistence.class.getName());

    @PersistenceContext(unitName = "Bsod")
    protected EntityManager em;

    public MesaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando la mesa con id={0}", id);
        return em.find(MesaEntity.class, id);
    }

    public List<MesaEntity> findAll() {
        LOGGER.info("Consultando todas las mesas");
        Query q = em.createQuery("select u from MesaEntity u");
        return q.getResultList();
    }

    public List<MesaEntity> findAllInCompany(Long sucursalId) {
        LOGGER.log(Level.INFO, "Consultando todas las mesas de la sucursal id={0}", sucursalId);
        TypedQuery q = em.createQuery("select d from MesaEntity d  where d.sucursal.id = :sucursalId", MesaEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        return q.getResultList();
    }

    public MesaEntity create(MesaEntity entity) {
        LOGGER.info("Creando una mesa nueva");
        em.persist(entity);
        LOGGER.info("Mesa creada");
        return entity;
    }

    public MesaEntity update(MesaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando mesa con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe la mesa
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando mesa con id={0}", id);
        MesaEntity entity = em.find(MesaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
