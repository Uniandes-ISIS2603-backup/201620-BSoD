/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
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
    private TarjetaPuntosDTO tarjetaPuntos;
    
    private ArrayList<MedioPagoDTO> mediosPago;

    private ArrayList<ReservaDTO> reservas;    
    
    public ClienteDetailDTO()
    {   super();    }
    
    public ClienteDetailDTO(ClienteEntity clienteEntity) 
    {
        super(clienteEntity);
        
        if(clienteEntity.getTarjetaPuntos()!=null)
        {
            this.tarjetaPuntos = new TarjetaPuntosDTO(clienteEntity.getTarjetaPuntos());
        }
        
        if(clienteEntity.getMediosPago()!=null)
        {
            List<MedioPagoEntity> mediosPagoLogic = clienteEntity.getMediosPago();
            for(MedioPagoEntity medioPagoLogic : mediosPagoLogic) 
            {
              this.mediosPago.add(new MedioPagoDTO(medioPagoLogic));
            }
        }
        
        if(clienteEntity.getReservas()!=null)
        {
            List<ReservaEntity> reservasLogic = clienteEntity.getReservas();
            for(ReservaEntity reservaLogic : reservasLogic) 
            {
              this.reservas.add(new ReservaDTO(reservaLogic));
            }
        }
    }
    
    public ClienteEntity toEntity()
    {
        ClienteEntity clienteEntity = super.toEntity();
        
        if(tarjetaPuntos!=null)
        {
            clienteEntity.setTarjetaPuntos(this.tarjetaPuntos.toEntity());
        }  
         
        for(MedioPagoDTO medioPagoDTO: mediosPago)
        {
            clienteEntity.getMediosPago().add(medioPagoDTO.toEntity());
        }
        
        for(ReservaDTO reservaDTO: reservas)
        {
            clienteEntity.getReservas().add(reservaDTO.toEntity());
        }
        
        return clienteEntity;
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

    public ArrayList<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    
    
    @Override
    public String toString() 
    {
    	return "{ id : " + getId() + ", "+
                "documentoIdentidad : "+ getDocumentoIdentidad() +", "+
                "tipoDocumentoIdentidad : \""+getTipoDocumentoIdentidad()+"\", "+
                "name : \""+getName()+"\", "+
                "apellidos : \""+getApellidos()+"\", "+
                "direccion : \""+getDireccion()+"\", "+
                "telefono : "+ getTelefono()+ ", "+
                "tarjetaPuntos : "+ tarjetaPuntos + ", "+
                "mediosPago : "+ mediosPago +", "+
                "reservas : "+ reservas+ "}";
    }
    
}
