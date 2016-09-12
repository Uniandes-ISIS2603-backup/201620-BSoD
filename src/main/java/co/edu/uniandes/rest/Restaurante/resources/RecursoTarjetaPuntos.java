/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.mocks.MockTarjetasPuntos;
import co.edu.uniandes.rest.cities.exceptions.LogicaRestauranteException;

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
@Path("clientes/{idCliente: \\d+}/tarjetasPuntos")
@Produces("application/json")
@Consumes("application/json")
public class RecursoTarjetaPuntos 
{
    MockTarjetasPuntos mockTarjetasPuntos = new MockTarjetasPuntos();
    
    /**
     * Retorna la lista de clientes.
     * @return lista de clientes.
     * @throws LogicaRestauranteException Si no existe una lista de clientes en el sistema.
     */
    @GET
    public List<TarjetaPuntosDTO> darClientes() throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.darClientes();
    }
    
     /**
     * Obtiene el cliente con el identificador buscado.
     * @param pId Identificador del cliente buscado.
     * @return ClienteDTO Cliente buscado.
     * @throws LogicaRestauranteException Si no existe un cliente con el identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDTO darCliente(@PathParam("id") Long pId) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.darCliente(pId);
    }
    
     /**
     * Crea un cliente con la información enviada como parámetro.
     * @param pNuevoCliente La instancia cliente que se quiere buscar.
     * @return ClienteDTO cliente creado.
     * @throws LogicaRestauranteException Si ya existe un cliente con ese id.
     */
    @POST
    public ClienteDTO crearCliente(ClienteDTO pNuevoCliente) throws LogicaRestauranteException
    {
        return mockTarjetasPuntos.crearCliente(pNuevoCliente);
    }
    
    /**
     * Actualiza la información del cliente
     * @param ClienteDTO Cliente a actualizar.
     * @throws LogicaRestauranteException Si no existe un cliente con el id dado.
     */
    @PUT
    public ClienteDTO actualizarCliente(ClienteDTO pClienteAActualizar) throws LogicaRestauranteException 
    {
        return mockTarjetasPuntos.actualizarTarjetaPuntos(pClienteAActualizar);
    }
    
    /**
     * Elimina el cliente con el identificador indicado
     * @param pId Identificador del cliente que se quiere eliminar.
     * @throws LogicaRestauranteException Si no existe ningun cliente con el id dado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarCliente(@PathParam("id") Long pId) throws LogicaRestauranteException 
    {
        mockTarjetasPuntos.eliminarTarjetaPuntos(pId);
    }
}
