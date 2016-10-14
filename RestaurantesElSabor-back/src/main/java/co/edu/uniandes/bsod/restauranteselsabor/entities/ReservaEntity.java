/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.util.Date;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author aj.paredes10
 */
@Entity
public class ReservaEntity extends BaseEntity{
    
    @PodamExclude
    @ManyToOne
    SucursalEntity sucursal;
    
    @PodamExclude
    @ManyToOne
    ClienteEntity cliente;
    
//    @Temporal(TemporalType.DATE)
//    private Date fecha;
    
    private int numPersonas;
    private int piso;
    
    
    
    // en sucursal iria @OneToMany(mappedBy="reservas")

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

 //   public Date getFecha() {
 //       return fecha;
 //   }

//    public void setFecha(Date fecha) {
//        this.fecha = fecha;
//    }

    public int getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
