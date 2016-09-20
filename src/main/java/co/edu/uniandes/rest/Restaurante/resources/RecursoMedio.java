/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.rest.Restaurante.dtos.MedioDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;
import co.edu.uniandes.rest.Restaurante.mocks.MockMedio;
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
        MockMedio mockMedio = new MockMedio();
        

    /**
     * Obtiene una lista con todos los medios de pago.
     * @return lista de medios de pago.
     * @throws LogicaRestauranteException Si no existe una lista de medios de pago en el sistema.
     */
    @GET
    public List<MedioDTO> darMedios(@PathParam("idCliente") Long id) throws LogicaRestauranteException
    {
        return mockMedio.darMedios(id);
    }

    @GET
    @Path("{id: \\d+}")
      public MedioDTO darMedio(@PathParam("id") Long pId) throws LogicaRestauranteException
    {
        return mockMedio.darMedio(pId);
    }

    @POST
    public MedioDTO crearMedio(MedioDTO nuevoMedio) throws LogicaRestauranteException
    {
        return mockMedio.crearMedio(nuevoMedio);
    }

    @PUT
    public MedioDTO actualizarMedio(MedioDTO medioActualizado) throws LogicaRestauranteException
    {
        return mockMedio.actualizarMedio(medioActualizado);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void eliminarMedio(@PathParam("id") Long pId) throws LogicaRestauranteException
    {
        mockMedio.eliminarMedio(pId);
    }
}
