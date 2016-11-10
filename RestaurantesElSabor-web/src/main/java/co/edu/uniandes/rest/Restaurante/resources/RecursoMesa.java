/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IMesaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MesaDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MesaDetailDTO;
import java.util.ArrayList;

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
@Path("sucursal/{idSucursal: \\d+}/mesa")
@Produces("application/json")
@Consumes("application/json")
public class RecursoMesa 
{

    @Inject
    private IMesaLogic mesaLogic;

    /**
     * Convierte una lista de MesaEntity a una lista de MesaDetailDTO.
     *
     * @param entityList Lista de MesaEntity a convertir.
     * @return Lista de MesaDetailDTO convertida.
     *
     */
    private List<MesaDetailDTO> listEntity2DTO(List<MesaEntity> entityList) {
        List<MesaDetailDTO> list = new ArrayList<>();
        for (MesaEntity entity : entityList) {
            list.add(new MesaDetailDTO(entity));
        }
        return list;
    }

    /**
     * Obtiene todos los Mesas 
     * @return la lista de mesas 
     *
     */
    @GET
    public List<MesaDetailDTO> darMesas() {
        return listEntity2DTO(mesaLogic.getMesas());
    }

    /**
     * Obtiene los datos de una instancia de Mesa a partir de su ID
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de MesaDetailDTO con los datos del Mesa
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public MesaDetailDTO darMesa(@PathParam("id") Long id) {
        return new MesaDetailDTO(mesaLogic.getMesa(id));
    }

    
    /**
     * Se encarga de crear un Mesa en la base de datos
     *
     * @param dto Objeto de MesaDetailDTO con los datos nuevos
     * @return Objeto de MesaDetailDTOcon los datos nuevos y su ID
     *
     */
    @POST
    public MesaDetailDTO crearMesa(MesaDetailDTO dto) {
        return new MesaDetailDTO(mesaLogic.createMesa(dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Mesa
     *
     * @param id Identificador de la instancia de Mesa a modificar
     * @param dto Instancia de MesaDetailDTO con los nuevos datos
     * @return Instancia de MesaDetailDTO con los datos actualizados
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public MesaDetailDTO actualizarMesa(@PathParam("id") Long id, MesaDetailDTO dto) {
        MesaEntity entity = dto.toEntity();
        entity.setId(id);
        
        return new MesaDetailDTO(mesaLogic.updateMesa(entity));
    }

    /**
     * Elimina una instancia de Mesa de la base de datos
     *
     * @param id Identificador de la instancia a eliminar
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarMesa(@PathParam("id") Long id) {
        mesaLogic.deleteMesa(id);
    }

}
