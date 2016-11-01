/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jdguz
 */
@XmlRootElement
public class ClienteDetailDTO extends ClienteDTO
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
    
    public ClienteDetailDTO()
    {   super();    }
    
    public ClienteDetailDTO(ClienteEntity clienteEntity) 
    {
        super(clienteEntity);
        
        this.tarjetaPuntos = new TarjetaPuntosDTO(clienteEntity.getTarjetaPuntos());
        
        List<MedioPagoEntity> mediosPagoLogic = clienteEntity.getMediosPago();
        for(MedioPagoEntity medioPagoLogic : mediosPagoLogic) 
        {
            this.mediosPago.add(new MedioPagoDTO(medioPagoLogic));
        }
    }
    
    public ClienteEntity toEntity()
    {
        ClienteEntity clienteEntity = super.toEntity();
        
        clienteEntity.setTarjetaPuntos(this.tarjetaPuntos.toEntity());
        
        for(MedioPagoDTO medioPagoDTO: mediosPago)
        {
            clienteEntity.getMediosPago().add(medioPagoDTO.toEntity());
        }
        
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
