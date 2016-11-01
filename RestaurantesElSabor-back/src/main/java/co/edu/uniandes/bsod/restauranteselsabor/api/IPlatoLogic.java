/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import java.util.List;

/**
 *
 * @author zl.castaneda10
 */
public interface IPlatoLogic {
    //Plato por id de sucursal
    public List<PlatoEntity> darPlatos(Long idSucursal);
    public PlatoEntity darPlato(Long id);
    public PlatoEntity crearPlato(PlatoEntity plato) throws RestauranteLogicException;
    public PlatoEntity actualizarPlato(PlatoEntity plato);
    public void eliminarPlato(Long id);
    
    
    
    
    
    
}

