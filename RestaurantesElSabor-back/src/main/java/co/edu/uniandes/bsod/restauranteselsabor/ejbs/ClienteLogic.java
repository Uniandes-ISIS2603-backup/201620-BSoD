/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.TarjetaPuntosPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jdguz
 */
@Stateless
public class ClienteLogic implements IClienteLogic
{
    @Inject private ClientePersistence persistence;
    
    @Inject private TarjetaPuntosLogic tarjetaPuntosLogic;
    
    public List<ClienteEntity> getClientes()
    {
        return persistence.findAll();
    }
    
    public ClienteEntity getCliente(Long id)
    {
        return persistence.find(id);
    }
        
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws RestauranteLogicException
    {
        if(clienteEntity.getDocumentoIdentidad()<0)
        {
            throw new RestauranteLogicException("El documento de identidad no puede ser 0 negativo, se intento crear un cliente con documento de identidad de: "+clienteEntity.getDocumentoIdentidad());
        }
        return persistence.create(clienteEntity);
    }
    
    public ClienteEntity updateCliente(ClienteEntity clienteEntity) throws RestauranteLogicException
    {
        if(clienteEntity.getDocumentoIdentidad()<0)
        {
            throw new RestauranteLogicException("El documento de identidad no puede ser 0 negativo, se intento actualizar el documento de identidad de un cliente a: "+clienteEntity.getDocumentoIdentidad());
        }
        return persistence.update(clienteEntity);
    }
    
    public void deleteCliente(Long id) throws RestauranteLogicException
    {
        ClienteEntity cliente = persistence.find(id);
        if(cliente==null) throw new RestauranteLogicException("Se intento eliminar un cliente que no existe.");
        
        if(cliente.getTarjetaPuntos()!=null) tarjetaPuntosLogic.deleteTarjetaPuntos(id);
        persistence.delete(id);
    }
    
    
}
