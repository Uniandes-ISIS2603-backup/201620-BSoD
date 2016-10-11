/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author cc.novoa11
 */
@Entity
public class DomicilioEntity extends BaseEntity implements Serializable {

    @OneToOne
    private FacturaEntity factura;

    private double precio;
    private String direccion;

    //
    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public double getFecha() {
        return precio;
    }

    public void setFecha(double precio) {
        this.precio = precio;
    }

    public String getTotal() {
        return direccion;
    }

    public void setTotal(String direccion) {
        this.direccion = direccion;
    }

}
