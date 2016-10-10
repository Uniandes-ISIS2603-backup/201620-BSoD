/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author af.pinzon10
 */
@Entity
public class SucursalEntity extends BaseEntity implements Serializable{
    
    private String ciudad;
    
    private String direccion;
    
    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MesaEntity> mesas = new ArrayList<MesaEntity>();
    
    private List<String> calificaciones;
    
    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();
    
//    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();
    
    @OneToMany(mappedBy = "Sucursal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlatoEntity> platos = new ArrayList<PlatoEntity>();

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

 //   public List<FacturaEntity> getFacturas() {
 //       return facturas;
 //   }

//    public void setFacturas(List<FacturaEntity> facturas) {
//        this.facturas = facturas;
//    }

    public List<PlatoEntity> getPlatos() {
        return platos;
    }

    public void setPlatos(List<PlatoEntity> platos) {
        this.platos = platos;
    }
    
    
}
