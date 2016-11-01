/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import java.util.Date;

/**
 *
 * @author aj.paredes10
 */
public class ReservaDTO {
    
    //ATRIBUTOS
    
    private Long id;
    private Date fecha;
    private int numPersonas;
    private int piso;
   
    /**
     * Constructor por defecto
     */ 
    public ReservaDTO(){
        
    }
    /**
     * Constructor con parámetros.
     * @param id
     * @param fecha
     * @param numPersonas
     * @param piso
     */
    public ReservaDTO(ReservaEntity entity){
        if(entity != null){
        this.id = entity.getId();
        this.fecha = entity.getFecha();
        this.numPersonas = entity.getNumPersonas();
        this.piso = entity.getPiso();
        }
    }
    public ReservaEntity toEntity() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(this.getId());
        entity.setFecha(this.getFecha());
        entity.setNumPersonas(this.getNumPersonas());
        entity.setPiso(this.getPiso());
        
        return entity;
    }
    
    //METODOS
    
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id =id;
    }
    
    public Date getFecha(){
        return fecha;
    }
    public void setFecha(Date fecha){
        this.fecha =fecha;
    }
    
    public int getNumPersonas(){
        return numPersonas;
    }
    public void setNumPersonas(int numPersonas){
        this.numPersonas =numPersonas;
    }
    
    public int getPiso(){
        return piso;
    }
    public void setPiso(int piso){
        this.piso =piso;
    }
}
