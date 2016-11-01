package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import java.util.Date;

/**
 *
 * @author aj.paredes10
 */


public class MedioPagoDTO
{
    //ATRIBUTOS ATRIBUTOS ATRIBUTOS ATRIBUTOS ----------

    private Long id;

    //private Long idCliente;
    
    private Integer efectivo;

    private String tarjeta;

    private Long numerosTarjeta;

    private Date fechaVencimiento;

    private Integer codigoSeguridad;

    private String franquicia;
    
    public MedioPagoDTO()
    {   }

    //CONSTRUCTOR CONSTRUCTOR CONSTRUCTOR CONSTRUCTOR-----------

    public MedioPagoDTO(MedioPagoEntity entity)
    {
        if(entity != null){
        this.id = entity.getId();
        this.efectivo = entity.getEfectivo();
        this.tarjeta = entity.getTarjeta();
        this.numerosTarjeta = entity.getNumerosTarjeta();
        this.fechaVencimiento=entity.getFechaVencimiento();
        this.codigoSeguridad=entity.getCodigoSeguridad();
        this.franquicia=entity.getFranquicia();
        }
        
    }
    
     public MedioPagoEntity toEntity() {
        MedioPagoEntity entity = new MedioPagoEntity();
        entity.setId(this.getId());
        entity.setEfectivo(this.getEfectivo());
        entity.setTarjeta(this.getTarjeta());
        entity.setNumerosTarjeta(this.getNumerosTarjeta());
        entity.setFechaVencimiento(this.getFechaVencimiento());
        entity.setCodigoSeguridad(this.getCodigoSeguridad());
        entity.setFranquicia(this.getFranquicia());
        return entity;
    }

    //ASIGNAR ASIGNAR ASIGNAR ASIGNAR---------------------

    public void setId(Long pId)
    {
        id = pId;
    }
   // public void setIdCliente(Long pIdCliente)
   // {
   //     idCliente=pIdCliente;
   // }
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
//    public Long getIdCliente(){
//        return idCliente;
//    }
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
