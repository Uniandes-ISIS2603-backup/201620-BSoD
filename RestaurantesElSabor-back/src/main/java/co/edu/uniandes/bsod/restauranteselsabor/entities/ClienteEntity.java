/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jdguz
 */
@Entity
public class ClienteEntity extends BaseEntity implements Serializable
{
    private int documentoIdentidad;
    
    private String tipoDocumentoIdentidad;
    
    private String apellidos;
    
    private String direccion;
    
    private int telefono;
    
    @PodamExclude
    @OneToOne
    private TarjetaPuntosEntity tarjetaPuntos;
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MedioPagoEntity> mediosPago = new ArrayList<>();

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

    public TarjetaPuntosEntity getTarjetaPuntos() 
    {
        return tarjetaPuntos;
    }

    public void setTarjetaPuntos(TarjetaPuntosEntity tarjetaPuntos) 
    {
        this.tarjetaPuntos = tarjetaPuntos;
    }

    public List<MedioPagoEntity> getMediosPago() 
    {
        return mediosPago;
    }

    public void setMediosPago(List<MedioPagoEntity> mediosPago) 
    {
        this.mediosPago = mediosPago;
    }    
    
}
