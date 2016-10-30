/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IMedioPagoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.MedioPagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author aj.paredes10
 */
@Stateless
public class MedioPagoLogic implements IMedioPagoLogic{
    
    @Inject
    private MedioPagoPersistence medioPagoPersistence;
    
    @Inject
    private IClienteLogic clienteLogic;

    @Override
    public List<MedioPagoEntity> darMedios(Long idCliente) throws RestauranteLogicException{
        ClienteEntity cli = clienteLogic.getCliente(idCliente);
        if(cli!=null)
        {
             return cli.getMediosPago();
        }
        else{
            throw new RestauranteLogicException("Erro: No se pueden dar los medios de pago del cliente, el cliente no fue encontrado");
        }
       
    }

    @Override
    public MedioPagoEntity darMedio(Long pId) throws RestauranteLogicException{
       try{
       return medioPagoPersistence.find(pId);
        }
        catch(Exception e){
            throw new RestauranteLogicException("Error al buscar el medio de pago: "+ e.getMessage());
        }
    }

    @Override
    public MedioPagoEntity crearMedio(MedioPagoEntity nuevoMedio) throws RestauranteLogicException{
        MedioPagoEntity buscado = medioPagoPersistence.find(nuevoMedio.getId());
        
        if(buscado != null)
        {
            throw new RestauranteLogicException("Error: el emdio de pago a crear ya existe");
        }
        else{
          return  medioPagoPersistence.create(nuevoMedio);
        }
    }

    @Override
    public MedioPagoEntity actualizarMedio(MedioPagoEntity medioActualizado) throws RestauranteLogicException{
        MedioPagoEntity buscado = medioPagoPersistence.find(medioActualizado.getId());
        if(buscado != null)
        {
           return medioPagoPersistence.update(medioActualizado);
        }
        else{
            throw new RestauranteLogicException("Error: el medio de pago a actualizar no fue encontrado");
        }
    }

    @Override
    public void eliminarMedio(Long pId) throws RestauranteLogicException{
       MedioPagoEntity buscado = medioPagoPersistence.find(pId);
        if(buscado != null)
        {
          medioPagoPersistence.delete(pId);
        }
        else{
           throw new RestauranteLogicException("Error: el medio de pago a eliminar no fue encontrado");
        }
    }
    
}
