/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

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
    public FacturaDTO(Long pId, int pTotal)
    {
        super();
        this.id = pId;
        this.total = pTotal;
    }
    
    /**
     * Métodos
     */
    
    /**
     * Obtener el id del domicilio.
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
     * Obtener el precio del domicilio.
     * @return total
     */
    
    public int getTotal() 
    {
        return total;
    }
    
    /**
     * Asignar un nuevo precio.
     * @param nPrecio 
     */

    public void setTotal(int nTotal) 
    {
        this.total = nTotal;
    }
        
    /**
     * Representacion JSON de un domicilio
     * @return La representacion JSON de un DTO domicilio.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +"\", "
                + "total : \""+total+"\" }" ;  
    }
    
}

