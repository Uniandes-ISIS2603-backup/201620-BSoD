/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author jdguz
 */
public class ClienteLogic 
{
    @Inject private ClientePersistence persistence;
    
    public List<ClienteEntity> getClientes()
    {
        return persistence.findAll();
    }
    
    
}
