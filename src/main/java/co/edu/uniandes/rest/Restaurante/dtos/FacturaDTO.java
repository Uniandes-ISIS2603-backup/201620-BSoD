/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

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
    private Date fecha;
    private int total;
    
    /**
     * Constructor por defecto
     */
    public FacturaDTO()
    {   }
    /**
     * Constructor por con parámetros dados
     * @param pId
     * @param pTotal
     */
    public FacturaDTO(Long pId,Long pIdCliente,Long pIdSucursal,Date fecha, int pTotal)
    {
        super();
        this.id = pId;
        this.idCliente = pIdCliente;
        this.idsucursal = pIdSucursal;
        this.fecha = fecha;
        this.total = pTotal;
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

