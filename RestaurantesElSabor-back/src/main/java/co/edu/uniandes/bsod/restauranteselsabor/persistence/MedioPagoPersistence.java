package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
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
 * @author aj.paredes10
 */
@Stateless
public class MedioPagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MedioPagoPersistence.class.getName());
    @PersistenceContext( unitName="Bsod")
    protected EntityManager em;
    
    //buscar medio de pago por id
    public MedioPagoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con id={0}", id);
        return em.find(MedioPagoEntity.class, id);
    }
    
    //buscar reserva por nombre
    public MedioPagoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando medio de pago con name = {0}", name);
        TypedQuery<MedioPagoEntity> q
                = em.createQuery("select u from MedioPagoEntity u where u.name = :name", MedioPagoEntity.class);
        q = q.setParameter("name", name); 
        return q.getSingleResult();
    }
    //busca todas las reservas
    public List<MedioPagoEntity> findAll() {
        LOGGER.info("Consultando todos los medios de pago");
        Query q = em.createQuery("select u from MedioPagoEntity u");
        return q.getResultList();
    }
    
    //crear reserva
    public MedioPagoEntity create(MedioPagoEntity entity) {
        LOGGER.info("Creando un medio de pago nuevo");
        em.persist(entity);
        LOGGER.info("medio de pago creado");
        return entity;
    }
    
    //modificar reserva
    public MedioPagoEntity update(MedioPagoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando medio de pago con id={0}", entity.getId());
        return em.merge(entity);
    }
    
    //eliminar reserva
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando medio de pago con id={0}", id);
        MedioPagoEntity entity = em.find(MedioPagoEntity.class, id);
        em.remove(entity);
    }
}
