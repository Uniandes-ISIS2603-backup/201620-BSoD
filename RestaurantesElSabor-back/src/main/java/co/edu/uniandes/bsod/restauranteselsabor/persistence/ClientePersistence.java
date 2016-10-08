/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.persistence;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
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
public class ClientePersistence 
{
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "ClientePU")
    protected EntityManager em;
    
    //  CREATE
    
    public ClienteEntity create(ClienteEntity clienteEntity)
    {
        LOGGER.info("Creando un CLIENTE nuevo");
        em.persist(clienteEntity);
        LOGGER.info("CLIENTE creado");
        return clienteEntity;
    }
    
    //  READ
    
    public ClienteEntity find(Long id) 
    {
        LOGGER.log(Level.INFO, "Consultando CLIENTE con id={0}", id);
        return em.find(ClienteEntity.class, id);
    }
    
    public List<ClienteEntity> findAll() 
    {
        LOGGER.info("Consultando todos los CLIENTE");
        Query q = em.createQuery("select clientes from ClienteEntity clientes");
        return q.getResultList();
    }
    
    //  UPDATE
    
    public ClienteEntity update(ClienteEntity clienteEntity) 
    {
        LOGGER.log(Level.INFO, "Actualizando CLIENTE con id={0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }
    
    //  DELETE

    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando CLIENTE con id={0}", id);
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
    
    
}
