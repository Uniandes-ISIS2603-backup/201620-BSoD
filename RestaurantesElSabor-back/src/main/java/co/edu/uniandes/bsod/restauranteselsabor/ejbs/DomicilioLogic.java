/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.DomicilioPersistence;
import javax.inject.Inject;

/**
 *
 * @author cc.novoa11
 */
public class DomicilioLogic {
    
    @Inject private DomicilioPersistence persistence;
    
    public DomicilioEntity getDomicilio(Long idFactura)
    {
        return persistence.find(idFactura);
    }
    
    public DomicilioEntity createDomicilio(DomicilioEntity domicilio) throws RestauranteLogicException
    {
        return persistence.create(domicilio);
    }
    
    public DomicilioEntity updateDomicilio(DomicilioEntity domicilio) throws RestauranteLogicException
    {
        return persistence.update(domicilio);
    }
        
    public void deleteDomicilio(Long idFactura) throws RestauranteLogicException
    {
        persistence.delete(idFactura);
    }
    
}
