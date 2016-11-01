/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import java.util.List;

/**
 *
 * @author cc.novoa11
 */
public interface IFacturaLogic {
    public List<FacturaEntity> getFacturas(Long idSucursal)throws RestauranteLogicException;
    
    public FacturaEntity getFactura(Long idSucursal,Long id)throws RestauranteLogicException;
    
    public FacturaEntity createFactura(Long idSucursal,FacturaEntity nFactura)throws RestauranteLogicException;
    
    public FacturaEntity updateFactura(Long idSucursal,FacturaEntity factura)throws RestauranteLogicException;
    
    public void deleteFactura(Long idSucursal,Long id)throws RestauranteLogicException;
    
}
