/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author cc.novoa11
 */

@XmlRootElement
public class DomicilioDetailDTO extends DomicilioDTO{
    @PodamExclude
    private FacturaDTO factura;
    
    public DomicilioDetailDTO(){
        super();
    }
    
    public DomicilioDetailDTO(DomicilioEntity entity){
        super(entity);
    }
    
    @Override
    public DomicilioEntity toEntity(){
        DomicilioEntity entity = super.toEntity();
            if(this.getFactura() != null){
                entity.setFactura(this.getFactura().toEntity());
            }
        return entity;
    } 
    
    
    public FacturaDTO getFactura(){
        return factura;
    }
    
    public void setFactura (FacturaDTO factura){
        this.factura = factura;
    }   
    
 
    
}
