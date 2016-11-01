/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.rest.Restaurante.dtos.MedioPagoDTO;
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
 * @author igomez10
 */
@Path("/clientes/{idCliente: \\d+}/medios")
@Produces("application/json")
@Consumes("application/json")
public class RecursoMedio
{
    @GET
    public List<MedioPagoDTO> darMedios(@PathParam("idCliente") Long id) throws LogicaRestauranteException
    {
        return null;
    }

    @GET
    @Path("{id: \\d+}")
      public MedioPagoDTO darMedio(@PathParam("id") Long pId) throws LogicaRestauranteException
    {
        return null;
    }

    @POST
    public MedioPagoDTO crearMedio(MedioPagoDTO nuevoMedio) throws LogicaRestauranteException
    {
        return null;
    }

    @PUT
    public MedioPagoDTO actualizarMedio(MedioPagoDTO medioActualizado) throws LogicaRestauranteException
    {
        return null;
    }

    @DELETE
    @Path("{id: \\d+}")
    public void eliminarMedio(@PathParam("id") Long pId) throws LogicaRestauranteException
    {
        
    }
}
