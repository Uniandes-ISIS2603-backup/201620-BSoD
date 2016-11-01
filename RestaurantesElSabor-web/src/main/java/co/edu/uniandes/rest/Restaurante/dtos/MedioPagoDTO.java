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
public class MedioPagoDTO
{
    private Long id;
    
    private Integer efectivo;

    private String tarjeta;

    private Long numerosTarjeta;

    private Date fechaVencimiento;

    private Integer codigoSeguridad;

    private String franquicia;
    
    public MedioPagoDTO()
    {   }
    
    public MedioPagoDTO(MedioPagoEntity medioPagoEntity)
    {
        this.id = medioPagoEntity.getId();
        this.efectivo = medioPagoEntity.getEfectivo();
        this.tarjeta = medioPagoEntity.getTarjeta();
        this.numerosTarjeta = medioPagoEntity.getNumerosTarjeta();
        this.fechaVencimiento = medioPagoEntity.getFechaVencimiento();
        this.codigoSeguridad = medioPagoEntity.getCodigoSeguridad();
        this.franquicia = medioPagoEntity.getFranquicia();
    }
    
    public MedioPagoEntity toEntity()
    {
        MedioPagoEntity medioPagoEntity = new MedioPagoEntity();
        
        medioPagoEntity.setId(this.id);
        medioPagoEntity.setEfectivo(this.efectivo);
        medioPagoEntity.setTarjeta(this.tarjeta);
        medioPagoEntity.setNumerosTarjeta(this.numerosTarjeta);
        medioPagoEntity.setFechaVencimiento(this.fechaVencimiento);
        medioPagoEntity.setCodigoSeguridad(this.codigoSeguridad);
        medioPagoEntity.setFranquicia(this.franquicia);
        
        return medioPagoEntity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "efectivo : \""+ getEfectivo() +"\", "+
                "tarjeta : \""+getTarjeta()+"\", "+
                "numeros de Tarjeta : \""+getNumerosTarjeta()+"\", "+
                "fecha de vencimiento : \""+ getFechaVencimiento()+", "+
                "codigoSeguridad : \""+getCodigoSeguridad()+", "+
                "franquicia : \""+getFranquicia()+" }" ;
    }
}
