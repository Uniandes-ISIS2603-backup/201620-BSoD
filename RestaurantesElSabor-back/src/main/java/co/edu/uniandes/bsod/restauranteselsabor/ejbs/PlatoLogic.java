/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.PlatoPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author zl.castaneda10
 */
public class PlatoLogic {
    
    @Inject private PlatoPersistence persistence;
    
    public List<PlatoEntity> getPlatos(){
        return persistence.findAll();
    }
    
    public PlatoEntity getPlato(Long id){
        return persistence.find(id);
    }
    
    public PlatoEntity createPlato(PlatoEntity platoEntity){
        return persistence.create(platoEntity);
    }
    
    public PlatoEntity update (PlatoEntity platoEntity){
            return persistence.update(platoEntity);
    }
    
    public void deletePlato(Long id){
        persistence.delete(id);
    }
    
}
