/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;
import co.edu.uniandes.bsod.restauranteselsabor.api.ITarjetaPuntosLogic;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDetailDTO;
import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
    ITarjetaPuntosLogic tarjetaPuntosLogic;
    
    @GET
    public TarjetaPuntosDetailDTO getTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws RestauranteLogicException  
    {
        return new TarjetaPuntosDetailDTO(tarjetaPuntosLogic.getTarjetaPuntos(idCliente));
    }
    
    @POST
    public TarjetaPuntosDetailDTO createTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws RestauranteLogicException 
    {
        return new TarjetaPuntosDetailDTO(tarjetaPuntosLogic.createTarjetaPuntos(idCliente));
    }
    
    @PUT
    public TarjetaPuntosDetailDTO updateTarjetaPuntos(@PathParam("idCliente") Long idCliente, TarjetaPuntosDetailDTO pTarjetaPuntosAActualziar) throws RestauranteLogicException  
    {
        return new TarjetaPuntosDetailDTO(tarjetaPuntosLogic.updateTarjetaPuntos(idCliente, pTarjetaPuntosAActualziar.toEntity()));
    }
    
    
    @PUT
    @Path("sumaPuntos")
    public TarjetaPuntosDetailDTO sumarPuntosTarjetaPuntos(@PathParam("idCliente") Long idCliente, @QueryParam("compra") int pCompra) throws RestauranteLogicException  
    {
        return new TarjetaPuntosDetailDTO(tarjetaPuntosLogic.sumarPuntosTarjetaPuntos(idCliente, pCompra));
    }
    
    
    @DELETE
    public void deleteTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws RestauranteLogicException 
    {
        tarjetaPuntosLogic.deleteTarjetaPuntos(idCliente);
    }
}
