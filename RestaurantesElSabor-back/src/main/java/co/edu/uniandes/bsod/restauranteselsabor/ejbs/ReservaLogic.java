/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author aj.paredes10
 */
@Stateless
public class ReservaLogic implements IReservaLogic{

    @Inject
    private ReservaPersistence persistence;
    @Inject
    private IClienteLogic clienteLogic;
    @Inject
    private ISucursalLogic sucursalLogic;
    
    /**
     * Obtiene la lista de reservas de un cliente.
     * @param idCliente
     * @return 
     */
    @Override
    public List<ReservaEntity> getReservas(Long idCliente) throws RestauranteLogicException {
        ClienteEntity cli = clienteLogic.getCliente(idCliente);
        if(cli!=null){
             return cli.getReservas();
        }
        else{
            throw new RestauranteLogicException("Erro: No se pueden dar las reservas del cliente, el cliente no fue encontrado");
        }
    }
    @Override
    public List<ReservaEntity> getReservasSucursal(Long idSucursal) throws RestauranteLogicException {
        SucursalEntity suc = sucursalLogic.getSucursal(idSucursal);
        if(suc!=null){
             return suc.getReservas();
        }
        else{
            throw new RestauranteLogicException("Erro: No se pueden dar las reservas de la sucursal, el cliente no fue encontrado");
        }
    }

    @Override
    public ReservaEntity getReserva(Long idCliente, Long id) throws RestauranteLogicException{
        ClienteEntity cli = clienteLogic.getCliente(idCliente);
        try{
             return persistence.find(idCliente, id);
        }
        catch(Exception e){
            throw new RestauranteLogicException("Erro: No se puede dar la reserva, "+ e.getMessage());
        }
    }

    @Override
    public ReservaEntity createReserva(Long idSucursal,Long idCliente, ReservaEntity nuevaReserva) throws RestauranteLogicException {
       ReservaEntity alreadyExist = getReserva(idCliente, nuevaReserva.getId());
        if (alreadyExist != null) {
            throw new RestauranteLogicException("Ya existe una reserva con ese id");
        } else {
            SucursalEntity suc = sucursalLogic.getSucursal(idSucursal);
            ClienteEntity  cli = clienteLogic.getCliente(idCliente);
            nuevaReserva.setCliente(cli);
            nuevaReserva.setSucursal(suc);
            nuevaReserva = persistence.create(nuevaReserva);
        }
        return nuevaReserva;
    }

    @Override
    public ReservaEntity updateReserva(Long idCliente, ReservaEntity reserva) throws RestauranteLogicException {
        ClienteEntity cli = clienteLogic.getCliente(idCliente);
        SucursalEntity suc = sucursalLogic.getSucursal(reserva.getSucursal().getId());
        reserva.setCliente(cli);
        reserva.setSucursal(suc);
        if(getReserva(idCliente, reserva.getId()) != null){
            return persistence.update(reserva);
        }
        else{
            throw new RestauranteLogicException("La reserva a actualizar no existe");
        }
        
    }

    @Override
    public void deleteReserva(Long idCliente, Long id) throws RestauranteLogicException {
        ReservaEntity entity = getReserva(idCliente, id);
        if(entity != null){
            persistence.delete(id);
        }
        else{
            throw new RestauranteLogicException("Erro: la reserva a eliminar no fue encontrada");
        }
    }

    @Override
    public List<ReservaEntity> getReservasEnFecha(Date fecha) {
        return persistence.findByDate(fecha);
    }
    
}
