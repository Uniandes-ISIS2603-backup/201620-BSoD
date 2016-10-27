/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import java.util.List;

/**
 *
 * @author aj.paredes10
 */
public interface IMedioPagoLogic {
    
    public List<MedioPagoEntity> darMedios(Long idCliente);
    
    public MedioPagoEntity darMedio(Long pId);
    
    public MedioPagoEntity crearMedio(MedioPagoEntity nuevoMedio);
    
    public MedioPagoEntity actualizarMedio(MedioPagoEntity medioActualizado);
    
    public void eliminarMedio(Long pId);
    
}
