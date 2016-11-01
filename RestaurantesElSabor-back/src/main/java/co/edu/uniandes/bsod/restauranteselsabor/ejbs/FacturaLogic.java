/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;


import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.FacturaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author cc.novoa11
 */
@Stateless
public class FacturaLogic implements IFacturaLogic{
    
    @Inject
    private FacturaPersistence persistence;
    @Inject
    private ISucursalLogic clienteLogic;
          
    @Override
    public List<FacturaEntity> getFacturas(Long idSucursal) throws RestauranteLogicException {
        SucursalEntity suc = clienteLogic.getSucursal(idSucursal);
        return suc.getFacturas();
    }

    @Override
    public FacturaEntity getFactura(Long idSucursal, Long id) throws RestauranteLogicException {
        return persistence.find(idSucursal, id); 
    }

    @Override
    public FacturaEntity createFactura(Long idSucursal,FacturaEntity nFactura) throws RestauranteLogicException {
        SucursalEntity suc = clienteLogic.getSucursal(idSucursal);
        nFactura.setSucursal(suc);
        nFactura= persistence.create(nFactura); 
        return nFactura;
    }

    @Override
    public FacturaEntity updateFactura(Long idSucursal,FacturaEntity factura) throws RestauranteLogicException {
        SucursalEntity suc = clienteLogic.getSucursal(idSucursal);
        factura.setSucursal(suc);
        return persistence.update(factura); 
    }

    @Override
    public void deleteFactura(Long idSucursal, Long id) throws RestauranteLogicException {
        persistence.delete(id); 
    }
    
}
