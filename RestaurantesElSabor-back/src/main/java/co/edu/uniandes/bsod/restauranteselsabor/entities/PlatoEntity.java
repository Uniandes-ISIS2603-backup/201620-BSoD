/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author zl.castaneda10
 */
@Entity
public class PlatoEntity extends BaseEntity implements Serializable{
    
    private int precio;
    private String descripcion;
    
    @OneToMany( mappedBy = "sucursal")
    private SucursalEntity sucursal;
    
    public int getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public SucursalEntity getSucursal() {
        return sucursal;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setSucursal(SucursalEntity sucursal) {
        this.sucursal = sucursal;
    }

    
}
