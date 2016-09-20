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
    private Date fechaFactura;
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
    public FacturaDTO(Long pId,Date fecha, int pTotal)
    {
        super();
        this.id = pId;
        this.fechaFactura = fecha;
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
        return fechaFactura;
    }
    
    /**
     * Asignar un nuevo precio.
     * @param nFecha
     */

    public void setFecha(Date nFecha) 
    {
        this.fechaFactura = nFecha;
    }
        
    /**
     * Representacion JSON de factura
     * @return La representacion JSON de un DTO factura.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +"\", "
                +"fecha : \""+fechaFactura+ "total : \""+total+"\" }" ;  
    }
    
}

