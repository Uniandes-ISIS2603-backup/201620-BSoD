/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDTO;
import co.edu.uniandes.rest.Restaurante.mocks.MockTarjetasPuntos;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

import java.util.List;
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
    MockTarjetasPuntos mockTarjetasPuntos = new MockTarjetasPuntos();
    
    @GET
    public TarjetaPuntosDTO darTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.darTarjetaPuntos(idCliente);
    }
    
    @POST
    public TarjetaPuntosDTO crearTarjetaPuntos(@PathParam("idCliente") Long idCliente, TarjetaPuntosDTO pNuevaTarjetaPuntos) throws LogicaRestauranteException
    {
        return mockTarjetasPuntos.crearTarjetaPuntos(idCliente, pNuevaTarjetaPuntos);
    }
    
    @PUT
    public TarjetaPuntosDTO actualizarTarjetaPuntos(@PathParam("idCliente") Long idCliente, TarjetaPuntosDTO pTarjetaPuntosAActualziar) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.actualizarTarjetaPuntos(idCliente, pTarjetaPuntosAActualziar);
    }
    
    @DELETE
    public void eliminarTarjetaPuntos(@PathParam("idCliente") Long idCliente) throws LogicaRestauranteException 
    {
        mockTarjetasPuntos.eliminarTarjetaPuntos(idCliente);
    }
}