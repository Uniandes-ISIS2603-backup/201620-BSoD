/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.ejbs;

import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.ClientePersistence;
import co.edu.uniandes.bsod.restauranteselsabor.persistence.TarjetaPuntosPersistence;
import javax.inject.Inject;

/**
 *
 * @author jdguz
 */
public class TarjetaPuntosLogic 
{
    //@Inject private TarjetaPuntosPersistence persistence;
    private static ClientePersistence clientePersistence;
    
    public TarjetaPuntosEntity getTarjetaPuntos(Long idCliente)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity createTarjetaPuntos(Long idCliente)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if(cliente.getTarjetaPuntos()!=null)
        {
            //LANZAR EXCEPTION
        }
        cliente.setTarjetaPuntos(new TarjetaPuntosEntity());
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity updateTarjetaPuntos(Long idCliente, TarjetaPuntosEntity tarjetaPuntos)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        if(cliente.getTarjetaPuntos()==null)
        {
            //LANZAR EXCEPTION
        }
        cliente.setTarjetaPuntos(tarjetaPuntos);
        return cliente.getTarjetaPuntos();
    }
    
    public TarjetaPuntosEntity sumarPuntosTarjetaPuntos(Long idCliente, int compra)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        TarjetaPuntosEntity tarjeta = cliente.getTarjetaPuntos();
        if(cliente.getTarjetaPuntos()==null)
        {
            //LANZAR EXCEPTION
        }
        int puntos = compra/10000;
        int acumulado = tarjeta.getAcumulado();
        int nuevoAcumulado = acumulado + puntos;
        tarjeta.setAcumulado(nuevoAcumulado);
        return tarjeta;
    }
    
    public void deleteTarjetaPuntos(Long idCliente)
    {
        ClienteEntity cliente = clientePersistence.find(idCliente);
        TarjetaPuntosEntity tarjetaPuntosAEliminar = cliente.getTarjetaPuntos();
        if(cliente.getTarjetaPuntos()==null)
        {
            //LANZAR EXCEPTION
        }
        //persistence.delete(tarjetaPuntosAEliminar.getId());
        cliente.setTarjetaPuntos(null);
        //persistence.delete(tarjetaPuntosAEliminar.getId());
    }
}
