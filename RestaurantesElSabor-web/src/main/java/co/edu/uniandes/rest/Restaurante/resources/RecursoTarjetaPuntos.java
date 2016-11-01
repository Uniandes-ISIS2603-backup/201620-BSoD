/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.TarjetaPuntosLogic;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDetailDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jdguz
 */
@Path("clientes/{idCliente}/tarjetaPuntos")
@Produces("application/json")
@Consumes("application/json")
public class RecursoTarjetaPuntos 
{   
    @Inject
    TarjetaPuntosLogic tarjetaPuntosLogic;
    
    @GET
    public TarjetaPuntosDetailDTO getTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws LogicaRestauranteException 
    {
        return null;
    }
    
    @POST
    public TarjetaPuntosDetailDTO createTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws LogicaRestauranteException
    {
        return null;
    }
    
    @PUT
    public TarjetaPuntosDetailDTO updateTarjetaPuntos(@PathParam("idCliente") Long idCliente, TarjetaPuntosDetailDTO pTarjetaPuntosAActualziar) throws LogicaRestauranteException 
    {
        return null;
    }
    
    @PUT
    public TarjetaPuntosDetailDTO sumarPuntosTarjetaPuntos(@PathParam("idCliente") Long idCliente, int pCompra) throws LogicaRestauranteException 
    {
        return null;
    }
    
    @DELETE
    public void deleteTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws LogicaRestauranteException 
    {
        
    }
}
