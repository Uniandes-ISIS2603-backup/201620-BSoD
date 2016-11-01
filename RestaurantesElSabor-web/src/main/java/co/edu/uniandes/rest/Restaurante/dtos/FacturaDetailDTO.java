/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author cc.novoa11
 */
@XmlRootElement
public class FacturaDetailDTO extends FacturaDTO {
    @PodamExclude
    private SucursalDTO sucursal;
    
    @PodamExclude
    private ClienteDTO cliente;
    
    @PodamExclude
    private DomicilioDTO domicilio;
    
    public FacturaDetailDTO(){
        super();
    }
    public FacturaDetailDTO(FacturaEntity entity){
        super(entity);
    }
    
    public FacturaEntity toEntity()
    {
        FacturaEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        if(this.getSucursal() != null){
            entity.setSucursal(this.getSucursal().toEntity());
        }
        if(this.getDomicilio() != null){
            entity.setDomicilio(this.getDomicilio().toEntity());
        }
        return entity;
    }
    
    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public SucursalDTO getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }
    
    public DomicilioDTO getDomicilio() {
        return domicilio;
    }
    
    public void setDomicilio (DomicilioDTO domicilio){
        this.domicilio = domicilio;
    }
  
}
