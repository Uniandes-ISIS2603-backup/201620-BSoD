/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;

/**
 *
 * @author aj.paredes10
 */
public interface IReservaLogic {
    
    public List<ReservaEntity> getReservas(Long idCliente);
    
    public ReservaEntity getReserva(Long idCliente, Long id);
    
    public ReservaEntity createReserva(Long idCliente, ReservaEntity nuevaReserva);
    
    public ReservaEntity updateReserva(Long idCliente, ReservaEntity reserva);
    
    public void deleteReserva(Long idCliente, Long id);
    
    public List<ReservaEntity> getreservasEnFecha(Date fecha);
}
