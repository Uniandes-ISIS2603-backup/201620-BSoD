/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import java.util.List;

/**
 *
 * @author jdguz
 */
public interface IClienteLogic 
{
    public List<ClienteEntity> getClientes();
    
    public ClienteEntity getCliente(Long id);
    
    public ClienteEntity createCliente(ClienteEntity clienteEntity);
    
    public ClienteEntity update(ClienteEntity clienteEntity);
    
    public void deleteCliente(Long id);
}
