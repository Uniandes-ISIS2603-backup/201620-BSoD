/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdguz
 */
@XmlRootElement
public class ClienteDTO 
{
    private Long id;
    
    private int documentoIdentidad;
    
    private String tipoDocumentoIdentidad;
    
    private String name;
    
    private String apellidos;
    
    private String direccion;
    
    private int telefono;
    
    public ClienteDTO()
    {   }
    
    public ClienteDTO(ClienteEntity clienteEntity)
    {
        this.id = clienteEntity.getId();
        this.documentoIdentidad = clienteEntity.getDocumentoIdentidad();
        this.tipoDocumentoIdentidad = clienteEntity.getTipoDocumentoIdentidad();
        this.name = clienteEntity.getName();
        this.apellidos = clienteEntity.getApellidos();
        this.direccion = clienteEntity.getDireccion();
        this.telefono = clienteEntity.getTelefono();
    }
    
    public ClienteEntity toEntity()
    {
        ClienteEntity clienteEntity = new ClienteEntity();
        
        clienteEntity.setId(this.id);
        clienteEntity.setDocumentoIdentidad(this.documentoIdentidad);
        clienteEntity.setTipoDocumentoIdentidad(this.tipoDocumentoIdentidad);
        clienteEntity.setName(this.name);
        clienteEntity.setApellidos(this.apellidos);
        clienteEntity.setDireccion(this.direccion);
        clienteEntity.setTelefono(this.telefono);
        
        return clienteEntity;
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public int getDocumentoIdentidad() 
    {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(int documentoIdentidad) 
    {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getTipoDocumentoIdentidad() 
    {
        return tipoDocumentoIdentidad;
    }

    public void setTipoDocumentoIdentidad(String tipoDocumentoIdentidad) 
    {
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
    }
    
    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getApellidos() 
    {
        return apellidos;
    }

    public void setApellidos(String apellidos) 
    {
        this.apellidos = apellidos;
    }

    public String getDireccion() 
    {
        return direccion;
    }

    public void setDireccion(String direccion) 
    {
        this.direccion = direccion;
    }

    public int getTelefono() 
    {
        return telefono;
    }

    public void setTelefono(int telefono) 
    {
        this.telefono = telefono;
    }
    
    @Override
    public String toString() 
    {
    	return "{ id : " + id + ", "+
                "documentoIdentidad : "+ documentoIdentidad +", "+
                "tipoDocumentoIdentidad : \""+tipoDocumentoIdentidad+"\", "+
                "name : \""+name+"\", "+
                "apellidos : \""+apellidos+"\", "+
                "direccion : \""+direccion+"\", "+
                "telefono : "+ telefono + " }";
    }
    
}
