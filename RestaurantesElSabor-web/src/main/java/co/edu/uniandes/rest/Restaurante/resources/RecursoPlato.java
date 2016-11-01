/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IPlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.ejbs.PlatoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.PlatoDTO;

import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;


import co.edu.uniandes.rest.Restaurante.dtos.PlatoDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.inject.Inject;

/**
 *
 * @author ccnovoa10
 */
@Path("sucursal/{idSucursal: \\d+}/plato")
@Produces("application/json")
@Consumes("application/json")
public class RecursoPlato 
{
    @Inject
    private IPlatoLogic platoLogic;
   
     /**
     * Convierte una lista de PlatoEntity a una lista de PlatoDetailDTO.
     *
     * @param entityList Lista de PlatoEntity a convertir.
     * @return Lista de PlatoDetailDTO convertida.
     *
     */
      private List<PlatoDetailDTO> listEntity2DTO(List<PlatoEntity> entityList) {
        List<PlatoDetailDTO> list = new ArrayList<>();
        for (PlatoEntity entity : entityList) {
            list.add(new PlatoDetailDTO(entity));
        }
        return list;
    }

    
    /**
     * Obtiene una lista con todos los platos.
     * @return lista de platos.
     * @throws LogicaRestauranteException Si no existe una lista de clientes en el sistema.
     */
    @GET
    public List<PlatoDetailDTO> darPlatos(@PathParam("idSucursal") Long idSucursal) throws LogicaRestauranteException 
    {
        return listEntity2DTO(platoLogic.darPlatos(idSucursal));
    }
    
     /**
     * Obtiene el cliente con el identificador buscado.
     * @param pId Identificador del cliente buscado
     * @return PlatoDTO Cliente buscado.
     * @throws LogicaRestauranteException Si no existe un cliente con el identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public PlatoDetailDTO darPlato(@PathParam("id") Long pId) throws LogicaRestauranteException 
    {
        return  new PlatoDetailDTO(platoLogic.darPlato(pId));
    }
    
    
     /**
     * Crea una nueva instancia de Cliente.
     * @param pId Identificacion del cliente a crear.
     * @param pNombre Nombre del cliente a crear.
     * @param pApellidos Apellidos del cliente a crear.
     * @param pDireccion Direccion del cliente a crear.
     * @return PlatoDTO cliente creado.
     * @throws LogicaRestauranteException Si ya existe un cliente con ese id.
     */
    @POST
    public PlatoDetailDTO crearPlato(@PathParam("idSucursal") Long idSucursal, PlatoDTO nuevoPlato) throws LogicaRestauranteException, RestauranteLogicException
    {
        return new PlatoDetailDTO(platoLogic.crearPlato(nuevoPlato.toEntity()));
    }
    
    /**
     *
     * @param PlatoDTO Cliente a actualizar.
     * @throws LogicaRestauranteException Si no existe un cliente con el id dado.
     */
    @PUT
     @Path("{id: \\d+}")
    public PlatoDetailDTO actualizarPlato(@PathParam("id") Long id, PlatoDetailDTO plato) throws LogicaRestauranteException 
    {
        PlatoEntity entity = plato.toEntity();
        entity.setId(id);
        return new PlatoDetailDTO(platoLogic.actualizarPlato(entity));
    }
    
    /**
     * Elimina el plato con el identificador indicado
     * @param pId Identificador del cliente que se quiere eliminar.
     * @throws LogicaRestauranteException Si no existe ningun cliente con el id dado.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarPlato(@PathParam("id") Long pId) throws LogicaRestauranteException 
    {
       platoLogic.eliminarPlato(pId);
    }
}
