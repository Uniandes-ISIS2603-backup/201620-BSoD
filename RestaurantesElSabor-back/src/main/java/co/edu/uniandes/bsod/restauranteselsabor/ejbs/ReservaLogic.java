/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
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
    
    
    /**
     * Obtiene la lista de reservas de un cliente.
     * @param idCliente
     * @return 
     */
    @Override
    public List<ReservaEntity> getReservas(Long idCliente) {
        return persistence.findAll();
    }

    @Override
    public ReservaEntity getReserva(Long idCliente, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservaEntity createReserva(Long idCliente, ReservaEntity nuevaReserva) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReservaEntity updateReserva(Long idCliente, ReservaEntity reserva) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteReserva(Long idCliente, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReservaEntity> getreservasEnFecha(Date fecha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
