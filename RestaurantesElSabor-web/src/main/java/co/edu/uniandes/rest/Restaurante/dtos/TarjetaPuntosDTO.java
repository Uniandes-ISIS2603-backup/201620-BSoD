/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdguz
 */
@XmlRootElement
public class TarjetaPuntosDTO
{   
    private Long id;
    
    private Date fechaCaducidad;
    
    private int acumulado;
    
    public TarjetaPuntosDTO()
    {   }

    public TarjetaPuntosDTO(TarjetaPuntosEntity tarjetaPuntosEntity)
    {
        this.id = tarjetaPuntosEntity.getId();
        this.fechaCaducidad = tarjetaPuntosEntity.getFechaCaducidad();
        this.acumulado = tarjetaPuntosEntity.getAcumulado();
    }
    
    public TarjetaPuntosEntity toEntity()
    {
        TarjetaPuntosEntity tarjetaPuntosEntity = new TarjetaPuntosEntity();
        
        tarjetaPuntosEntity.setId(this.id);
        tarjetaPuntosEntity.setFechaCaducidad(this.fechaCaducidad);
        tarjetaPuntosEntity.setAcumulado(this.acumulado);
        
        return tarjetaPuntosEntity;
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
