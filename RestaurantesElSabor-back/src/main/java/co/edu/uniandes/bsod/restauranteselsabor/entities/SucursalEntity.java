/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author af.pinzon10
 */
@Entity
public class SucursalEntity extends BaseEntity implements Serializable{
    
    private String ciudad;
    
    private String direccion;
    
    private List<MesaEntity> mesas;
    
    private List<String> calificaciones;
    
    private List<ReservaEntity> reservas;
    
    private List<FacturaEntity> facturas;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<MesaEntity> getMesas() {
        return mesas;
    }

    public void setMesas(List<MesaEntity> mesas) {
        this.mesas = mesas;
    }

    public List<String> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<String> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }
    
    
}
