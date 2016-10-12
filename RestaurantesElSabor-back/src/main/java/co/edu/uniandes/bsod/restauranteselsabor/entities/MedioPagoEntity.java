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
/**
 *
 * @author aj.paredes10
 */
@Entity
public class MedioPagoEntity extends BaseEntity implements Serializable
{    
    @PodamExclude
    @ManyToOne
    ClienteEntity cliente;
    
    private Integer efectivo;

    private String tarjeta;

    private Long numerosTarjeta;

    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;

    private Integer codigoSeguridad;

    private String franquicia;

    public ClienteEntity getCliente() {
        return cliente;
    }

    public Integer getEfectivo() {
        return efectivo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public Long getNumerosTarjeta() {
        return numerosTarjeta;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public void setEfectivo(Integer efectivo) {
        this.efectivo = efectivo;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void setNumerosTarjeta(Long numerosTarjeta) {
        this.numerosTarjeta = numerosTarjeta;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }
    
    
}
