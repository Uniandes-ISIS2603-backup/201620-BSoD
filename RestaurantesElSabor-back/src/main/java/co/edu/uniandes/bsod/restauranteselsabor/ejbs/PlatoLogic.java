/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IPlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.PlatoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author zl.castaneda10
 */
@Stateless
public class PlatoLogic implements IPlatoLogic{
    
    @Inject 
    private PlatoPersistence persistence;

    @Override
    public List<PlatoEntity> darPlatos(Long idSucursal) {
        return persistence.findAll();
    }

    @Override
    public PlatoEntity darPlato(Long id) {
        return persistence.find(id);
    }

    //los precios de los platos no peuden superar los $100.000
    @Override
    public PlatoEntity crearPlato(PlatoEntity plato) throws RestauranteLogicException
    {
        
       if (plato.getPrecio() > 100000){
           throw new RestauranteLogicException("No se peuden crear paltos con valor superior a los $100.000");
       }else{
           return persistence.create(plato);
       } 
    }

    @Override
    public PlatoEntity actualizarPlato(PlatoEntity plato) {
        return persistence.update(plato);
    }

    @Override
    public void eliminarPlato(Long id) {
        persistence.delete(id);
    }
    
}
