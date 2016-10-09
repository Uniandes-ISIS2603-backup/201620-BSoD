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
 * @author af.pinzon10
 */
@Entity
public class MesaEntity extends BaseEntity implements Serializable{
    
    private int piso;
    
    private int cantSillas;
    
    private boolean estado;
    
    @ManyToOne
    private int idSucursal;

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getCantSillas() {
        return cantSillas;
    }

    public void setCantSillas(int cantSillas) {
        this.cantSillas = cantSillas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    
}
