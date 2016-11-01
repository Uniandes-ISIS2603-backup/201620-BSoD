/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zl.castaneda
 */
@XmlRootElement
public class PlatoDTO 
{
    private Long id;
    
    private String nombre;
    
    private int precio;
    
    private String descripcion;
    
    private Long idSucursal;
    
    
    
    public PlatoDTO()
    {   }
    
    public PlatoDTO(PlatoEntity entity)
    {
     if(entity != null){
        this.id = entity.getId();
        this.nombre = entity.getName();
        this.precio = entity.getPrecio();
        this.descripcion = entity.getDescripcion();
        this.idSucursal = entity.getSucursal().getId();
     }
       
    }
    
    
    public PlatoEntity toEntity(){
       PlatoEntity entity = new PlatoEntity();
       entity.setId(this.getId());
       entity.setName(this.getNombre());
       entity.setPrecio(this.getPrecio());
       entity.setDescripcion(this.getDescripcion());
       return entity;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getPrecio() 
    {
        return precio;
    }

    public void setPrecio(int elPrecio) 
    {
        this.precio = elPrecio;
    }

    public String getDescripcion() 
    {
        return descripcion;
    }

    public void setDescripcion(String desc) 
    {
        this.descripcion = desc;
    }
        
    /**
     * Representacion JSON de un cliente
     * @return La representacion JSON de un DTO cliente.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +", nombre : \""+ nombre +"\", precio : \""+precio+"\", descripcion : \""+descripcion+"\" }" ;  
    }
    
    public Long getidSucursal() 
    {
        return idSucursal;
    }

    public void setidSucursal(Long id) 
    {
         this.idSucursal = id;
    }
      
    
    
}
