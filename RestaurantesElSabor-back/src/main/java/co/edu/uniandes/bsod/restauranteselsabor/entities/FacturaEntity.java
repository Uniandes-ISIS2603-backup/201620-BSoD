/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class FacturaEntity extends BaseEntity implements Serializable {

    @PodamExclude
    @ManyToOne
    private SucursalEntity sucursal;

    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;
  
    @PodamExclude
    @OneToOne
    private DomicilioEntity domicilio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    
    private int total;

    //
    public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    public DomicilioEntity getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioEntity domicilio) {
        this.domicilio = domicilio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
