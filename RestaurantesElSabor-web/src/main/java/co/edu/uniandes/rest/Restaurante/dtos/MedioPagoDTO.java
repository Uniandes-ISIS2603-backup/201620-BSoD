/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import java.util.Date;

/**
 *
 * @author igomez10
 */


public class MedioPagoDTO
{
    //ATRIBUTOS ATRIBUTOS ATRIBUTOS ATRIBUTOS ----------

    private Long id;

    private Long idCliente;
    
    private Integer efectivo;

    private String tarjeta;

    private Long numerosTarjeta;

    private Date fechaVencimiento;

    private Integer codigoSeguridad;

    private String franquicia;
    
    public MedioPagoDTO()
    {   }

    //CONSTRUCTOR CONSTRUCTOR CONSTRUCTOR CONSTRUCTOR-----------

    public MedioPagoDTO(Long pId ,Long pIdCliente, Integer pEfectivo, String pTarjeta, Long pNumerosTarjeta, Date pFechaVencimiento, Integer pCodigoSeguridad, String pFranquicia )
    {
        super();
        this.id = pId;
        this.idCliente=pIdCliente;
        this.efectivo = pEfectivo;
        this.tarjeta = pTarjeta;
        this.numerosTarjeta = pNumerosTarjeta;
        this.fechaVencimiento=pFechaVencimiento;
        this.codigoSeguridad=pCodigoSeguridad;
        this.franquicia=pFranquicia;
    }

    //ASIGNAR ASIGNAR ASIGNAR ASIGNAR---------------------

    public void setId(Long pId)
    {
        id = pId;
    }
        public void setIdCliente(Long pIdCliente)
    {
        idCliente=pIdCliente;
    }
    public void setEfectivo(Integer pEfectivo)
    {
      efectivo=pEfectivo;
    }
    public void setTarjeta(String pTarjeta)
    {
      tarjeta=pTarjeta;
    }
    public void setNumerosTarjeta(Long pNumerosTarjeta)
    {
      numerosTarjeta=pNumerosTarjeta;
    }
    public void setFechaVencimiento(Date pFechaVencimiento)
    {
      fechaVencimiento=pFechaVencimiento;
    }
    public void setCodigoSeguridad(Integer pCodigoSeguridad)
    {
      codigoSeguridad=pCodigoSeguridad;
    }
    public void setFranquicia(String pFranquicia)
    {
      franquicia=pFranquicia;
    }

    //DAR DAR DAR DAR DAR----------------------------------

    public Long getId() {
        return id;
    }
    public Long getIdCliente(){
        return idCliente;
    }
    public Integer getEfectivo(){
      return efectivo;
    }
    public String getTarjeta(){
      return tarjeta;
    }
    public Long getNumerosTarjeta(){
      return numerosTarjeta;
    }
    public Date getFechaVencimiento(){
      return fechaVencimiento;
    }
    public Integer getCodigoSeguridad(){
      return codigoSeguridad;
    }
    public String getFranquicia(){
      return franquicia;
    }

    //TOSTRING TOSTRING TOSTRING TOSTRING ---------------------------

    @Override
    public String toString()
    {
    	return "{ id : " + getId() +", idCliente : \""+ getIdCliente()+", efectivo : \""+ getEfectivo() +"\", tarjeta : \""+getTarjeta()+"\", "
                + ", numeros de Tarjeta : \""+getNumerosTarjeta()+"\""+", fecha de vencimiento : \""+ getFechaVencimiento()+", codigoSeguridad : \""+getCodigoSeguridad()+", franquicia : \""+getFranquicia()
                + " }" ;
    }
}
