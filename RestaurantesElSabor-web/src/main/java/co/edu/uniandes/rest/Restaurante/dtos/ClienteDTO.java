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

    private TarjetaPuntosDTO tarjetaPuntos;
    
    private ArrayList<MedioPagoDTO> mediosPago;
    
    public ClienteDTO()
    {   }

    public ClienteDTO(Long id, int documentoIdentidad, String tipoDocumentoIdentidad, String name, String apellidos, String direccion, int telefono, TarjetaPuntosDTO tarjetaPuntos, ArrayList<MedioPagoDTO> mediosPago) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.tipoDocumentoIdentidad = tipoDocumentoIdentidad;
        this.name = name;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tarjetaPuntos = tarjetaPuntos;
        this.mediosPago = mediosPago;
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

    public TarjetaPuntosDTO getTarjetaPuntos() 
    {
        return tarjetaPuntos;
    }

    public void setTarjetaPuntos(TarjetaPuntosDTO tarjetaPuntos) 
    {
        this.tarjetaPuntos = tarjetaPuntos;
    }

    public ArrayList<MedioPagoDTO> getMediosPago() 
    {
        return mediosPago;
    }

    public void setMediosPago(ArrayList<MedioPagoDTO> mediosPago) 
    {
        this.mediosPago = mediosPago;
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
                "telefono : "+ telefono + ", "+
                "tarjetaPuntos : "+ tarjetaPuntos + ", "+
                "mediosPago : "+ mediosPago +" }";
    }
    
}
