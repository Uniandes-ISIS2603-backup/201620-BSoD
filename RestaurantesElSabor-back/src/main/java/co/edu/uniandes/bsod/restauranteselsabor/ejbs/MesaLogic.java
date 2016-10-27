/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.IMesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.MesaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.pinzon10
 */
@Stateless
public class MesaLogic implements IMesaLogic {

    @Inject 
    private MesaPersistence persistence;
    
    /**
     * Obtiene la lista de los registros de Mesa.
     *
     * @return Colección de objetos de MesaEntity.
     * 
     */
    @Override
    public List<MesaEntity> getMesas() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Mesa a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de MesaEntity con los datos del Mesa consultado.
     * 
     */
    @Override
    public MesaEntity getMesa(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Mesa en la base de datos.
     *
     * @param entity Objeto de MesaEntity con los datos nuevos
     * @return Objeto de MesaEntity con los datos nuevos y su ID.
     * 
     */
    @Override
    public MesaEntity createMesa(MesaEntity entity) {
        return persistence.create(entity);
    }

    /**
     * Actualiza la información de una instancia de Mesa.
     *
     * @param entity Instancia de MesaEntity con los nuevos datos.
     * @return Instancia de MesaEntity con los datos actualizados.
     * 
     */
    @Override
    public MesaEntity updateMesa(MesaEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Mesa de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * 
     */
    @Override
    public void deleteMesa(Long id) {
        persistence.delete(id);
    }
    
}
