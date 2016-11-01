/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;

/**
 *
 * @author cc.novoa11
 */
public interface IDomicilioLogic {
    public DomicilioEntity getDomicilio(Long idFactura);
    
    public DomicilioEntity createDomicilio(DomicilioEntity domicilio) throws RestauranteLogicException;
    
    public DomicilioEntity updateDomicilio(DomicilioEntity domicilio) throws RestauranteLogicException;
    
    public void deleteDomicilio(Long idFactura) throws RestauranteLogicException;
    
}
