/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.api.ITarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.TarjetaPuntosPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jdguz
 */
@Stateless
public class TarjetaPuntosLogic implements ITarjetaPuntosLogic
{
    @Inject private TarjetaPuntosPersistence persistence;
    @Inject private ClientePersistence clientePersistence;
    
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity createTarjetaPuntos(Long idCliente) throws RestauranteLogicException
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        
        if(cliente.getTarjetaPuntos()!=null)
        {
            throw new RestauranteLogicException("Se intento crear la tarjeta de puntos de un cliente que ya tenia una.");
        }
        
        TarjetaPuntosEntity tarjetaPuntos = new TarjetaPuntosEntity();
        tarjetaPuntos.setCliente(cliente);
        cliente.setTarjetaPuntos(tarjetaPuntos);
        persistence.create(tarjetaPuntos);
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity updateTarjetaPuntos(Long idCliente, TarjetaPuntosEntity tarjetaPuntos) throws RestauranteLogicException
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if(cliente.getTarjetaPuntos()==null)
        {
            throw new RestauranteLogicException("Se intento actualizar la tarjeta de puntos de un cliente que no tenia una.");
        }
        cliente.setTarjetaPuntos(tarjetaPuntos);
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity sumarPuntosTarjetaPuntos(Long idCliente, int compra) throws RestauranteLogicException
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        TarjetaPuntosEntity tarjeta = cliente.getTarjetaPuntos();
        if(cliente.getTarjetaPuntos()==null)
        {
            throw new RestauranteLogicException("Se intento sumar puntos a la tarjeta de puntos de un cliente que no tenia una.");
        }
        if(compra<0)
        {
            throw new RestauranteLogicException("Se intento sumar puntos a la tarjeta de puntos de un cliente de una compra negativa.");
        }
        int puntos = compra/10000;
        int acumulado = tarjeta.getAcumulado();
        int nuevoAcumulado = acumulado + puntos;
        tarjeta.setAcumulado(nuevoAcumulado);
        return tarjeta;
    }
    
    public void deleteTarjetaPuntos(Long idCliente) throws RestauranteLogicException
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        TarjetaPuntosEntity tarjetaPuntosAEliminar = cliente.getTarjetaPuntos();
        if(cliente.getTarjetaPuntos()==null)
        {
            throw new RestauranteLogicException("Se intento eliminar la tarjeta de puntos de un cliente que no tenia una.");
        }
        persistence.delete(tarjetaPuntosAEliminar.getId());
        cliente.setTarjetaPuntos(null);
    }
}
