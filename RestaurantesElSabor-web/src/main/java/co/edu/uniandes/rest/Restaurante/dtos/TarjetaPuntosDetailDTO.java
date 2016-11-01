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
public class TarjetaPuntosDetailDTO extends TarjetaPuntosDTO
{   
    private ClienteDTO cliente;
    
    private Long id;
    
    private Date fechaCaducidad;
    
    private int acumulado;
    
    public TarjetaPuntosDetailDTO()
    {   super();    }
    
    public TarjetaPuntosDetailDTO(TarjetaPuntosEntity tarjetaPuntosEntity)
    {
        super(tarjetaPuntosEntity);
        this.cliente = new ClienteDTO(tarjetaPuntosEntity.getCliente());
    }
    
    public TarjetaPuntosEntity toEntity()
    {
        TarjetaPuntosEntity tarjetaPuntosEntity = super.toEntity();
        
        tarjetaPuntosEntity.setCliente(this.cliente.toEntity());
        
        return tarjetaPuntosEntity;
    }

    public ClienteDTO getCliente() 
    {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) 
    {
        this.cliente = cliente;
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
    	return "{ cliente : "+ cliente +","+
                "id : "+ id +","+
                "fechaCaducidad : "+fechaCaducidad+", "+
                "acumulado: "+acumulado+" }";
    }
    
}
