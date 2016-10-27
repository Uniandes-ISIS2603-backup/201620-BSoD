/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import java.util.List;

/**
 *
 * @author af.pinzon10
 */
public interface IMesaLogic {
    public List<MesaEntity> getMesas();
    public MesaEntity getMesa(Long id);
    public MesaEntity createMesa(MesaEntity entity); 
    public MesaEntity updateMesa(MesaEntity entity);
    public void deleteMesa(Long id);
}
