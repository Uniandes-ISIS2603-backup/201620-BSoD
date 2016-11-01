/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;


import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author aj.paredes10
 */
@XmlRootElement
public class ReservaDetailDTO extends ReservaDTO{
    @PodamExclude
    private ClienteDTO cliente;
    @PodamExclude
    private SucursalDTO sucursal;
    
    public ReservaDetailDTO(){
        super();
    }
    
    public ReservaDetailDTO(ReservaEntity entity) {
        super(entity);
        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        }
        if (entity.getSucursal() != null){
            this.sucursal = new SucursalDTO(entity.getSucursal());
        }
    }
     @Override
    public ReservaEntity toEntity() {
        ReservaEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        if(this.getSucursal() != null){
            entity.setSucursal(this.getSucursal().toEntity());
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
    
    
}
