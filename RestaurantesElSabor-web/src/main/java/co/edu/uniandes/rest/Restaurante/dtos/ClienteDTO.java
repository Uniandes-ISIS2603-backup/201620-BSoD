/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import java.util.ArrayList;

/**
 *
 * @author jdguz
 */
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

    public ClienteDTO(Long id, int documentoIdentidad, String tipoDocumentoIdentidad, String name, String apellidos, String direccion, int telefono) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
        this.name = name;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
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
    	return "{ id : " + id + ","+
                "documentoIdentidad : "+ documentoIdentidad +", "+
                "tipoDocumentoIdentidad : \""+tipoDocumentoIdentidad+"\", "+
                "name : \""+name+"\", "+
                "apellidos : \""+apellidos+"\", "+
                "direccion : \""+direccion+"\", "+
                "telefono : "+ telefono + " }";
    }
    
}
