/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import java.util.Date;

/**
 *
 * @author jdguz
 */
public class TarjetaPuntosDTO 
{   
    private Long id;
    
    private Date fechaCaducidad;
    
    private int acumulado;
    
    public TarjetaPuntosDTO()
    {   }

    public TarjetaPuntosDTO(Long id, Date fechaCaducidad, int acumulado) 
    {
        this.id = id;
        this.fechaCaducidad = fechaCaducidad;
        this.acumulado = acumulado;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Date getFechaCaducidad() 
    {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) 
    {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getAcumulado() 
    {
        return acumulado;
    }

    public void setAcumulado(int acumulado) 
    {
        this.acumulado = acumulado;
    }
    
    public void sumarAcumulado(int pCompra)
    {
        int suma = pCompra/10000;
        this.acumulado += suma;
    }
    
    @Override
    public String toString() 
    {
    	return "{ id : "+ id +","+
                "fechaCaducidad : "+fechaCaducidad+", "+
                "acumulado: "+acumulado+" }";
    }
    
}
