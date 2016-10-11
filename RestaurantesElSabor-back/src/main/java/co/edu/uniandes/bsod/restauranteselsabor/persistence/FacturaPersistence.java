/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author cc.novoa11
 */
@Stateless
public class FacturaPersistence {
      private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    @PersistenceContext( unitName="Bsod")
    protected EntityManager em;
    
     public FacturaPersistence find(Long id) {
        LOGGER.log(Level.INFO, "Consultando factura con id={0}", id);
        return em.find(FacturaPersistence.class, id);
    }
     
     
    public List<FacturaEntity> findAll() {
        LOGGER.info("Consultando todas las facturas");
        Query q = em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }
    
    public List<FacturaEntity> findAllInEntity(Long sucursalId) {
        LOGGER.log(Level.INFO, "Consultando todas las facturas de la sucursal id={0}", sucursalId);
        TypedQuery q = em.createQuery("select d from FacturaEntity d  where d.sucursal.id = :sucursalId", FacturaEntity.class);
        q = q.setParameter("sucursalId", sucursalId);
        return q.getResultList();
    }


    public FacturaEntity create(FacturaEntity entity) {
        LOGGER.info("Creando factura nueva");
        em.persist(entity);
        LOGGER.info("Factura creado");
        return entity;
    }

    public FacturaEntity update(FacturaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando factura con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el deptarment
     * crrespondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando factura con id={0}", id);
        FacturaEntity entity = em.find(FacturaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
    
}
