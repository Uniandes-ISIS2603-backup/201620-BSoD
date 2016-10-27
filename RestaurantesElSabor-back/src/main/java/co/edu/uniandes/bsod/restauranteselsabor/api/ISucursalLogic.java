/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import java.util.List;

/**
 *
 * @author af.pinzon10
 */
public interface ISucursalLogic {
    public List<SucursalEntity> getSucursales();

    public SucursalEntity getSucursal(Long id);

    public SucursalEntity getSucursalByName(String name);

    public SucursalEntity createSucursal(SucursalEntity entity);

    public SucursalEntity updateSucursal(SucursalEntity entity);

    public void deleteSucursal(Long id);
}
