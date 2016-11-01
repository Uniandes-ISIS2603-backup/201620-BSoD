/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;

import java.util.Date;
import java.util.List;

/**
 *
 * @author aj.paredes10
 */
public interface IReservaLogic {
    
    public List<ReservaEntity> getReservas(Long idCliente)throws RestauranteLogicException;
    
    public ReservaEntity getReserva(Long idCliente, Long id)throws RestauranteLogicException;
    
    public ReservaEntity createReserva(Long idCliente, ReservaEntity nuevaReserva)throws RestauranteLogicException;
    
    public ReservaEntity updateReserva(Long idCliente, ReservaEntity reserva)throws RestauranteLogicException;
    
    public void deleteReserva(Long idCliente, Long id)throws RestauranteLogicException;
    
    public List<ReservaEntity> getReservasEnFecha(Date fecha);
}
