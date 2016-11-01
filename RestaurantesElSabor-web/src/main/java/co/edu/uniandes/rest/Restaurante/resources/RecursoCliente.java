/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.ejbs.ClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ClienteEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.TarjetaPuntosEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDetailDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MedioPagoDTO;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDetailDTO;
import java.util.ArrayList;
import java.util.Date;

//

import java.util.List;
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
public class RecursoCliente 
{
    @Inject
    ClienteLogic clienteLogic;
    
    /**
     * Retorna la lista de clientes.
     * @return lista de clientes.
     * @throws LogicaRestauranteException Si no existe una lista de clientes en el sistema.
     */
    @GET
    public List<ClienteDetailDTO> getClientes() throws RestauranteLogicException 
    {
        ArrayList<ClienteDetailDTO> clientesWeb = new ArrayList<>();
        List<ClienteEntity> clientesLogica = clienteLogic.getClientes();
        
        for(ClienteEntity clienteLogica: clientesLogica)
        {
            ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(clienteLogica);
            clientesWeb.add(clienteDetailDTO);
        }
        return clientesWeb;
    }
    
     /**
     * Obtiene el cliente con el identificador buscado.
     * @param pId Identificador del cliente buscado.
     * @return ClienteDTO Cliente buscado.
     * @throws LogicaRestauranteException Si no existe un cliente con el identificador dado.
     */
    @GET
    @Path("{idCliente}")
    public ClienteDetailDTO getCliente(@PathParam("idCliente") Long pId) throws RestauranteLogicException 
    {
        ClienteEntity clienteEntity = clienteLogic.getCliente(pId);
        return new ClienteDetailDTO(clienteEntity);
    }
    
     /**
     * Crea un cliente con la información enviada como parámetro.
     * @param pNuevoCliente La instancia cliente que se quiere buscar.
     * @return ClienteDTO cliente creado.
     * @throws LogicaRestauranteException Si ya existe un cliente con ese id.
     */
    @POST
    public ClienteDetailDTO createCliente(ClienteDTO pNuevoCliente) throws RestauranteLogicException
    {
        ClienteEntity clienteEntity = pNuevoCliente.toEntity();
        return new ClienteDetailDTO(clienteLogic.createCliente(clienteEntity));
    }
    
    /**
     * Actualiza la información del cliente
     * @param ClienteDTO Cliente a actualizar.
     * @throws LogicaRestauranteException Si no existe un cliente con el id dado.
     */
    @PUT
    public ClienteDTO updateCliente(ClienteDTO pClienteAActualizar) throws RestauranteLogicException
    {
        return new ClienteDetailDTO(clienteLogic.updateCliente(pClienteAActualizar.toEntity()));
    }
    
    /**
     * Elimina el cliente con el identificador indicado
     * @param pId Identificador del cliente que se quiere eliminar.
     * @throws LogicaRestauranteException Si no existe ningun cliente con el id dado.
     */
    @DELETE
    @Path("{idCliente}")
    public void deleteCliente(@PathParam("idCliente") Long pId) throws RestauranteLogicException
    {
       clienteLogic.deleteCliente(pId);
    }
    
}
