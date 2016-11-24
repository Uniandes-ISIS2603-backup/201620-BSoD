/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jdguz
 */
@Entity
public class TarjetaPuntosEntity extends BaseEntity implements Serializable
{
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private ClienteEntity cliente;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    
    private int acumulado;

    public ClienteEntity getCliente() 
    {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) 
    {
        this.cliente = cliente;
    }

    public Date getFechaCaducidad() 
    {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) 
    {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getAcumulado() 
    {
        return acumulado;
    }

    public void setAcumulado(int acumulado)
    {
        this.acumulado = acumulado;
    }
    
    
}
