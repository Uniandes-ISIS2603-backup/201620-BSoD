/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.api;

import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;

/**
 *
 * @author jdguz
 */
public interface ITarjetaPuntosLogic 
{
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente);
    
    public TarjetaPuntosEntity createTarjetaPuntos(Long idCliente);
    
    public TarjetaPuntosEntity updateTarjetaPuntos(Long idCliente, TarjetaPuntosEntity tarjetaPuntos);
    
    public TarjetaPuntosEntity sumarPuntosTarjetaPuntos(Long idCliente, int compra);
    
    public void deleteTarjetaPuntos(Long idCliente);
    
}
