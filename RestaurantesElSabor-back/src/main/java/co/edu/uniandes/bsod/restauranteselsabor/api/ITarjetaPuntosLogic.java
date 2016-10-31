/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;

/**
 *
 * @author jdguz
 */
public interface ITarjetaPuntosLogic 
{
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente);
    
    public TarjetaPuntosEntity createTarjetaPuntos(Long idCliente) throws RestauranteLogicException;
    
    public TarjetaPuntosEntity updateTarjetaPuntos(Long idCliente, TarjetaPuntosEntity tarjetaPuntos) throws RestauranteLogicException;
    
    public TarjetaPuntosEntity sumarPuntosTarjetaPuntos(Long idCliente, int compra) throws RestauranteLogicException;
    
    public void deleteTarjetaPuntos(Long idCliente) throws RestauranteLogicException;
    
}
