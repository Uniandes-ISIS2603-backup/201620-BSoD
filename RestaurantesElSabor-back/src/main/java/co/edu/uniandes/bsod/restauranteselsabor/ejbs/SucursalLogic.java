/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.SucursalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.pinzon10
 */
@Stateless
public class SucursalLogic implements ISucursalLogic{

    @Inject
    private SucursalPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de Sucursal.
     *
     * @return Colección de objetos de SucursalEntity.
     *
     */
    @Override
    public List<SucursalEntity> getSucursales() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalEntity con los datos del Sucursal consultado.
     *
     */
    @Override
    public SucursalEntity getSucursal(Long id) {
         return persistence.find(id);
    }

    @Override
    public SucursalEntity getSucursalByName(String name) {
        return persistence.findByName(name);
    }

    /**
     * Se encarga de crear un Sucursal en la base de datos.
     *
     * @param entity Objeto de SucursalEntity con los datos nuevos
     * @return Objeto de SucursalEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public SucursalEntity createSucursal(SucursalEntity entity) {
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Sucursal.
     *
     * @param entity Instancia de SucursalEntity con los nuevos datos.
     * @return Instancia de SucursalEntity con los datos actualizados.
     *
     */
    @Override
    public SucursalEntity updateSucursal(SucursalEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Sucursal de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deleteSucursal(Long id) {
        persistence.delete(id);
    }

}
