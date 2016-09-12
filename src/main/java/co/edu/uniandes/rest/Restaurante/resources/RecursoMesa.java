/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.rest.Restaurante.mocks.MockClientes;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MesaDTO;
import co.edu.uniandes.rest.Restaurante.mocks.MockMesas;
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
@Path("mesas")
@Produces("application/json")
@Consumes("application/json")
public class RecursoMesa 
{
    MockMesas mockMesas = new MockMesas();
    
    /**
     * Retorna la lista de mesas.
     * @return lista de mesas.
     * @throws LogicaRestauranteException Si no existe una lista de mesas en el sistema.
     */
    @GET
    public List<MesaDTO> darMesas() throws LogicaRestauranteException 
    {
        return mockMesas.darMesas();
    }
    
     /**
     * Obtiene la mesa con el identificador buscado.
     * @param pId Identificador de la mesa buscada.
     * @return MesaDTO Mesa buscada.
     * @throws LogicaRestauranteException Si no existe una mesa con el identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public MesaDTO darMesa(@PathParam("id") Long pId) throws LogicaRestauranteException 
    {
        return mockMesas.darMesa(pId);
    }
    
     /**
     * Crea una mesa con la información enviada como parámetro.
     * @param pNuevaMesa La instancia mesa que se quiere crear.
     * @return MesaDTO mesa creada.
     * @throws LogicaRestauranteException Si ya existe una mesa con ese id.
     */
    @POST
    public MesaDTO crearMesa(MesaDTO pNuevaMesa) throws LogicaRestauranteException
    {
        return mockMesas.crearMesa(pNuevaMesa);
    }
    
    /**
     * Actualiza la información de la mesa.
     * @param pMesaAActualizar Cliente a actualizar.
     * @throws LogicaRestauranteException Si no existe un cliente con el id dado.
     */
    @PUT
    public MesaDTO actualizarMesa(MesaDTO pMesaAActualizar) throws LogicaRestauranteException 
    {
        return mockMesas.actualizarMesa(pMesaAActualizar);
    }
    
    /**
     * Elimina la mesa con el identificador indicado
     * @param pId Identificador de la misa que se quiere eliminar.
     * @throws LogicaRestauranteException Si no existe ninguna mesa con el id dado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarMesa(@PathParam("id") int pId) throws LogicaRestauranteException 
    {
        mockMesas.eliminarMesa(pId);
    }
}
