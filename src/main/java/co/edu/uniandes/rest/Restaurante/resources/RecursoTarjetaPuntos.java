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
@Path("tarjetasPuntos")
@Produces("application/json")
@Consumes("application/json")
public class RecursoTarjetaPuntos 
{
    MockTarjetasPuntos mockTarjetasPuntos = new MockTarjetasPuntos();
    
    /**
     * Retorna la lista de tarjetas de puntos.
     * @return lista de tarjetas de puntos.
     * @throws LogicaRestauranteException Si no existe una lista de tarjetas de puntos en el sistema.
     */
    @GET
    public List<TarjetaPuntosDTO> darTarjetasPuntos() throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.darTarjetasPuntos();
    }
    
     /**
     * Obtiene la tarjeta de puntos con el identificador buscado.
     * @param pId Identificador de la tarjeta de puntos buscada.
     * @return Tarjeta de puntos buscada.
     * @throws LogicaRestauranteException Si no existe una tarjeta de puntos con el identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public TarjetaPuntosDTO darTarjetaPuntos(@PathParam("id") int pId) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.darTarjetaPuntos(pId);
    }
    
     /**
     * Crea una tarjeta de puntos con la información enviada como parámetro.
     * @param pNuevaTarjetaPuntos La instancia tarjeta de puntos que se quiere buscar.
     * @return TarjetaPuntosDTO tarjeta de puntos creada.
     * @throws LogicaRestauranteException Si ya existe una tarjeta de puntos con ese id.
     */
    @POST
    public TarjetaPuntosDTO crearTarjetaPuntos(TarjetaPuntosDTO pNuevaTarjetaPuntos) throws LogicaRestauranteException
    {
        return mockTarjetasPuntos.crearTarjetaPuntos(pNuevaTarjetaPuntos);
    }
    
    /**
     * Actualiza la información de la tarjeta de puntos.
     * @param ClienteDTO Tarjeta de puntos a actualizar.
     * @throws LogicaRestauranteException Si no existe una tarjeta de puntos con el id dado.
     */
    @PUT
    public TarjetaPuntosDTO actualizarTarjetaPuntos(TarjetaPuntosDTO pTarjetaPuntosAActualziar) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.actualizarTarjetaPuntos(pTarjetaPuntosAActualziar);
    }
    
    /**
     * Elimina la tarjeta de puntos con el identificador indicado
     * @param pId Identificador de la tarjeta de puntos que se quiere eliminar.
     * @throws LogicaRestauranteException Si no existe ninguna tarjeta de puntos con el id dado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarTarjetaPuntos(@PathParam("id") int pId) throws LogicaRestauranteException 
    {
        mockTarjetasPuntos.eliminarTarjetaPuntos(pId);
    }
}
