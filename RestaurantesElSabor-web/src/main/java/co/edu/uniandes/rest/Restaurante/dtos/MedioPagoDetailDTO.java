/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdguz
 */
@XmlRootElement
public class MedioPagoDetailDTO extends MedioPagoDTO
{
    private Long id;

    private ClienteDTO idCliente;
    
    private Integer efectivo;

    private String tarjeta;

    private Long numerosTarjeta;

    private Date fechaVencimiento;

    private Integer codigoSeguridad;

    private String franquicia;
    
    public MedioPagoDetailDTO()
    {   }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClienteDTO getCliente() {
        return idCliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.idCliente = idCliente;
    }

    public Integer getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Integer efectivo) {
        this.efectivo = efectivo;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Long getNumerosTarjeta() {
        return numerosTarjeta;
    }

    public void setNumerosTarjeta(Long numerosTarjeta) {
        this.numerosTarjeta = numerosTarjeta;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Integer getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public void setCodigoSeguridad(Integer codigoSeguridad) {
        this.codigoSeguridad = codigoSeguridad;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    @Override
    public String toString()
    {
    	return "{ id : " + getId() +","+
                "cliente :" + getCliente() +", "+
                "efectivo : \""+ getEfectivo() +"\", "+
                "tarjeta : \""+getTarjeta()+"\", "+
                "numeros de Tarjeta : \""+getNumerosTarjeta()+"\", "+
                "fecha de vencimiento : \""+ getFechaVencimiento()+", "+
                "codigoSeguridad : \""+getCodigoSeguridad()+", "+
                "franquicia : \""+getFranquicia()+" }" ;
    }
}
