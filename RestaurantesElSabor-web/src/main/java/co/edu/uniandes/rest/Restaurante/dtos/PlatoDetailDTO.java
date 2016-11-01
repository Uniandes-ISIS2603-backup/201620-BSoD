/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author zl.castaneda10
 */
@XmlRootElement
public class PlatoDetailDTO extends PlatoDTO{
    @PodamExclude
    private SucursalDTO sucursal;
    
    public PlatoDetailDTO(){
        super();
    }
    
    public PlatoDetailDTO(PlatoEntity entity) {
     super(entity);
      
     if (entity.getSucursal() != null){
         //this.sucursal = new SucursalDTO(entity.getSucursal());
     }

    }
    
    @Override
    public PlatoEntity toEntity(){
        PlatoEntity entity = super.toEntity();
            if(this.getSucursal() != null){
                entity.setSucursal(this.getSucursal().toEntity());
            }
        return entity;
    } 
    
    
    public SucursalDTO getSucursal(){
        return sucursal;
    }
    
    public void setSucursal (SucursalDTO sucursal){
        this.sucursal = sucursal;
    }    
}
