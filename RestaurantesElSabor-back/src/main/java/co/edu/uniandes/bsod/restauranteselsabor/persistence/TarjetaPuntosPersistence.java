/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jdguz
 */
@Stateless
public class TarjetaPuntosPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(TarjetaPuntosPersistence.class.getName());

    @PersistenceContext(unitName = "TarjetaPuntosPU")
    protected EntityManager em;
    
    //  CREATE
    
    public TarjetaPuntosEntity create(TarjetaPuntosEntity tarjetaPuntosEntity)
    {
        LOGGER.info("Creando una TARJETAPUNTOS nueva");
        em.persist(tarjetaPuntosEntity);
        LOGGER.info("TARJETAPUNTOS creada");
        return tarjetaPuntosEntity;
    }
    
    //  READ
    
    public TarjetaPuntosEntity find(Long id) 
    {
        LOGGER.log(Level.INFO, "Consultando TARJETAPUNTOS con id={0}", id);
        return em.find(TarjetaPuntosEntity.class, id);
    }
    
    public List<TarjetaPuntosEntity> findAll() 
    {
        LOGGER.info("Consultando todas las TARJETAPUNTOS");
        Query q = em.createQuery("select tarjetaPuntos from TarjetaPuntosEntity tarjetasPuntos");
        return q.getResultList();
    }
    
    //  UPDATE
    
    public TarjetaPuntosEntity update(TarjetaPuntosEntity tarjetaPuntosEntity) 
    {
        LOGGER.log(Level.INFO, "Actualizando TARJETAPUNTOS con id={0}", tarjetaPuntosEntity.getId());
        return em.merge(tarjetaPuntosEntity);
    }
    
    //  DELETE

    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando TARJETAPUNTOS con id={0}", id);
        TarjetaPuntosEntity entity = em.find(TarjetaPuntosEntity.class, id);
        em.remove(entity);
    }
    
    
}
