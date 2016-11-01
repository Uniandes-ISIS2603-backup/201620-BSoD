/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.ReservaDTO;
import co.edu.uniandes.rest.Restaurante.dtos.SucursalDTO;
import co.edu.uniandes.rest.Restaurante.dtos.SucursalDetailDTO;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author af.pinzon10
 */
@Path("sucursal")
@Produces("application/json")
@Consumes("application/json")
public class RecursoSucursal 
{
    
    @Inject
    private ISucursalLogic sucursalLogic;

    /**
     * Convierte una lista de SucursalEntity a una lista de SucursalDetailDTO.
     *
     * @param entityList Lista de SucursalEntity a convertir.
     * @return Lista de SucursalDetailDTO convertida.
     *
     */
    private List<SucursalDetailDTO> listEntity2DTO(List<SucursalEntity> entityList) {
        List<SucursalDetailDTO> list = new ArrayList<>();
        for (SucursalEntity entity : entityList) {
            list.add(new SucursalDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene la lista de los registros de Sucursal
     *
     * @return Colección de objetos de SucursalDetailDTO
     *
     */
    @GET
    public List<SucursalDetailDTO> darSucursales() {

        return listEntity2DTO(sucursalLogic.getSucursales());
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalDetailDTO con los datos del Sucursal
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public SucursalDetailDTO darSucursal(@PathParam("id") Long id) {
        return new SucursalDetailDTO(sucursalLogic.getSucursal(id));
    }

    /**
     * Obtiene los datos de una instancia de Sucursal a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de SucursalDetailDTO con los datos del Sucursal
     * consultado
     *
     */
    @GET
    @Path("name")
    public SucursalDetailDTO darSucursalPorNombre(@QueryParam("name") String name) {
        SucursalEntity sucursalE = sucursalLogic.getSucursalByName(name);
        if (sucursalE == null) {
            throw new WebApplicationException("La compañía no existe", 404);
        } else {
            return new SucursalDetailDTO(sucursalE);
        }
    }

    /**
     * Se encarga de crear un Sucursal en la base de datos
     *
     * @param dto Objeto de SucursalDetailDTO con los datos nuevos
     * @return Objeto de SucursalDetailDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public SucursalDetailDTO crearSucursal(SucursalDetailDTO dto) throws RestauranteLogicException {
        return new SucursalDetailDTO(sucursalLogic.createSucursal(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Sucursal
     *
     * @param id Identificador de la instancia de Sucursal a modificar
     * @param dto Instancia de SucursalDetailDTO con los nuevos datos
     * @return Instancia de SucursalDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public SucursalDetailDTO actualizarSucursal(@PathParam("id") Long id, SucursalDetailDTO dto) {
        SucursalEntity entity = dto.toEntity();
        entity.setId(id);
        return new SucursalDetailDTO(sucursalLogic.updateSucursal(entity));
    }

    /**
     * Elimina una instancia de Sucursal de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarSucursal(@PathParam("id") Long id) {
        sucursalLogic.deleteSucursal(id);
    }
/*
    @GET
    @Path("{id: \\d+}/numberofemployees")
    public Integer getNumberOfEmployeesSucursal(@PathParam("id") Long id) {
        return sucursalLogic.getNumberOfEmployeesSucursal(id);
    }
    @Inject 
    private ISucursalLogic sucursalLogic;


    

    
    @GET
    @Path("{id: \\d+}/mesasDisponibles/{ano}/{mes}/{dia}")
    @Produces("text/plain")
    public String darMesasFecha(@PathParam("id") Long pId, @PathParam("ano") int ano, @PathParam("mes") int mes, @PathParam("dia") int dia )throws LogicaRestauranteException
    {
        Calendar calendario = Calendar.getInstance();
        calendario.set(ano, mes, dia);
        Date pFecha = calendario.getTime();
        
        return mockSucursales.mesasFecha(pId, pFecha);
    }*/
}
