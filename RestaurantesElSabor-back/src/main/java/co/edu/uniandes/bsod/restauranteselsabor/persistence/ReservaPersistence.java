package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.*;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * cubrimiento: porcentaje de codigo que se ejecuta dado unas prubas, no mide calidad de codigo
 * @author aj.paredes10
 */
@Stateless
public class ReservaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());
    @PersistenceContext( unitName="Bsod")
    protected EntityManager em;
    
    //buscar reserva por id
    public ReservaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", id);
        return em.find(ReservaEntity.class, id);
    }
    
    //buscar reserva por fecha
    public List<ReservaEntity> findByDate(Date fecha) {
        LOGGER.log(Level.INFO, "Consultando reserva con fehca = {0}", fecha);
        Query q = em.createQuery("select u from ReservaEntity u where u.fecha = :fecha", ReservaEntity.class);
        q = q.setParameter("fecha", fecha); 
        return q.getResultList();
    }
    //busca todas las reservas
    public List<ReservaEntity> findAll() {
        LOGGER.info("Consultando todas las reservas");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }
    
    //crear reserva
    public ReservaEntity create(ReservaEntity entity) {
        LOGGER.info("Creando una reserva nueva");
        em.persist(entity);
        LOGGER.info("Reserva creada");
        return entity;
    }
    
    //modificar reserva
    public ReservaEntity update(ReservaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando reserva con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    //eliminar reserva
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando reserva con id={0}", id);
        ReservaEntity entity = em.find(ReservaEntity.class, id);
        em.remove(entity);
    }
}
