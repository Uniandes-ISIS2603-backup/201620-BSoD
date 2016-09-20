/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

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
    private Long idCliente;
    private SucursalDTO idSucursal;
   
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
     * @param idMesa
     * @param idCliente
     * @param idSucursal
     */
    public ReservaDTO(Long id, Date fecha, int numPersonas, int piso, Long idCliente, SucursalDTO idSucursal){
        this.id = id;
        this.fecha = fecha;
        this.numPersonas = numPersonas;
        this.piso = piso;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
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
    
    public Long getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(Long idCliente){
        this.idCliente = idCliente;
    }
    
    public SucursalDTO getIdSucursal(){
        return idSucursal;
    }
    
    public void setIdSucursal(SucursalDTO idSucursal){
        this.idSucursal = idSucursal;
    }
}
