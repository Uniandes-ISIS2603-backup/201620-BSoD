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
    private int id;
    
    private Date fechaCaducidad;
    
    int acumulado;
    
    public TarjetaPuntosDTO()
    {   }
    
    public TarjetaPuntosDTO(int pId, Date pFechaCaducidad,int pAcumulado)
    {
        super();
        this.id = pId;
        this.fechaCaducidad = pFechaCaducidad;
        this.acumulado = pAcumulado;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
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
        
    /**
     * Representacion JSON de una tarjeta de puntos.
     * @return La representacion JSON de un DTO tarjeta de puntos.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +", fechaCaducidad : \""+ fechaCaducidad
                +", acumulado: "+acumulado+" }" ;  
    }
    
}
