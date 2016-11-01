/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import java.util.Date;

/**
 *
 * @author cc.novoa11
 */
public class FacturaDTO 
{
    /**
     * Atributos
     */
    private Long id;
    private Long idCliente;
    private Long idsucursal;
    private Long idDomicilio;
    private Date fecha;
    private int total;
    
    /**
     * Constructor por defecto
     */
    public FacturaDTO()
    {   }
    /**
     * Constructor por con parámetros dados
     * @param entity
     */
    public FacturaDTO(FacturaEntity entity)
    {
        super();
        this.id = entity.getId();
        this.idCliente = entity.getCliente().getId();
        this.idsucursal = entity.getSucursal().getId();
        this.idDomicilio = entity.getDomicilio().getId();
        this.fecha = entity.getFecha();
        this.total = entity.getTotal();
    }
    
       public FacturaEntity toEntity(){
       FacturaEntity entity = new FacturaEntity();
       entity.setId(this.getId());
       entity.setTotal(this.getTotal());
       entity.setFecha(this.getFecha());
       return entity;
    }
    
    /**
     * Métodos
     */
    
    /**
     * Obtener el id de factura.
     * @return id
     */

    public Long getId() 
    {
        return id;
    }
    
    /**
     * Asignar un nuevo id.
     * @param id 
     */

    public void setId(Long id) 
    {
        this.id = id;
    }
    /**
     * Obtener el precio de factura.
     * @return total
     */
    
    public int getTotal() 
    {
        return total;
    }
    
    /**
     * Asignar un nuevo precio.
     * @param nTotal
     */

    public void setTotal(int nTotal) 
    {
        this.total = nTotal;
    }
     /**
     * Obtener fecha de factura.
     * @return fechaFactura
     */
    
    public Date getFecha() 
    {
        return fecha;
    }
    
    /**
     * Asignar un nuevo precio.
     * @param nFecha
     */

    public void setFecha(Date nFecha) 
    {
        this.fecha = nFecha;
    }
    
       public Long getIdCliente() 
    {
        return idCliente;
    }
    
    /**
     * Asignar un nuevo id.
     * @param idCliente 
     */

    public void setIdCliente(Long idCliente) 
    {
        this.idCliente = idCliente;
    }
       public Long getIdSucursal() 
    {
        return idsucursal;
    }
    
    /**
     * Asignar un nuevo id.
     * @param idSucursal 
     */

    public void setIdSucursal(Long idSucursal) 
    {
        this.idsucursal = idSucursal;
    }
    
    public Long getIdDomicilio() 
    {
        return idDomicilio;
    }
    
    /**
     * Asignar un nuevo id.
     * @param idDomicilio 
     */

    public void setIdDomicilio(Long idDomicilio) 
    {
        this.idDomicilio = idDomicilio;
    }
        
    /**
     * Representacion JSON de factura
     * @return La representacion JSON de un DTO factura.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +"\", "+"idCliente : \""+idCliente +"idSucursal : \""+idsucursal
                +"fecha : \""+fecha+ "total : \""+total+"\" }" ;  
    }
    
}

