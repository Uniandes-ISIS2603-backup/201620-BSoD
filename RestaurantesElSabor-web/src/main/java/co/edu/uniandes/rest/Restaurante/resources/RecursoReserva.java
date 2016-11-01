/*
 * RecursoReserva.java
 * Clase que representa el recurso "/reservas"
 * Implementa varios métodos para manipular las reservas del restaurante
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IReservaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDetailDTO;
import co.edu.uniandes.rest.Restaurante.dtos.ReservaDTO;        
import co.edu.uniandes.rest.Restaurante.dtos.ReservaDetailDTO;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *Clase que implementa el recurso REST correspondiente a "reservas".
 *
 * Este recurso tiene la ruta "reservas". Al ejecutar la aplicación, el recurse
 * será accesibe a través de la ruta "/api/reservas"
 * @author aj.paredes10
 */

@Path("clientes/{idCliente: \\d+}/reservas")
@Produces("application/json")
@Consumes("application/json")
public class RecursoReserva {
    
    @Inject
    private IClienteLogic clienteLogic;
    
    @Inject
    private ISucursalLogic sucursalLogic;
    
    @Inject
    private IReservaLogic reservaLogic;
    
    @PathParam("idCliente")
    private Long idCliente;
    
    private List<ReservaDetailDTO> listEntityDTO(List<ReservaEntity> entityList){
            List<ReservaDetailDTO> list = new ArrayList<>();
            for (ReservaEntity entity : entityList){
                list.add(new ReservaDetailDTO(entity));
            }
            return list;
        }
        public void existsCliente(Long clienteId) {
        ClienteDetailDTO clien = new ClienteDetailDTO(clienteLogic.getCliente(clienteId));
        if (clien == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        }
        
        public void existsReserva(Long reservaId) {
            try{
            ReservaDetailDTO res = new ReservaDetailDTO(reservaLogic.getReserva(idCliente, reservaId));
            if (res == null) {
            throw new WebApplicationException("El medio de pago no existe", 404);
            }
            }catch(RestauranteLogicException e){
                throw new WebApplicationException("El medio de pago no existe", 404);
            }
        }
    /**
     * Obtiene el listado de reservas.
     *
     * @return lista de reservas
     * @throws LogicaRestauranteException excepción retornada por la lógica
     */
    @GET
    public List<ReservaDetailDTO> getReservas() throws RestauranteLogicException {
        existsCliente(idCliente);
        List<ReservaEntity> reservas = reservaLogic.getReservas(idCliente);
        return listEntityDTO(reservas);
    }
    @GET
    @Path ("{dia: \\d+}/{mes: \\d+}/{anho: \\d+}")
    public List<ReservaDetailDTO> getReservasByDate(@PathParam ("dia")int dia, @PathParam ("mes")int mes, @PathParam ("anho")int anho) throws RestauranteLogicException {
        Date fecha = new Date(anho, mes, dia);
        List<ReservaEntity> reservas = reservaLogic.getReservasEnFecha(fecha);
        return listEntityDTO(reservas);
    }
    /**
     * Crea una reserva.
     * @param reserva
     * @return datos de la reserva agragada
     * @throws LogicaRestauranteException si la reserva ya existe.
     */
    @POST
    public ReservaDTO createReserva( ReservaDTO nuevaReserva) throws RestauranteLogicException  {
        existsCliente(idCliente);
        return new ReservaDetailDTO(reservaLogic.createReserva(idCliente, nuevaReserva.toEntity()));
    }
    /**
     * Obtiene una reserva.
     * @param id de la reserva a busccar
     * @return la reserva can el id solicitado
     * @throws LogicaRestauranteException en caso de no encontrar la rweserva buscada
     */
    @GET
    @Path ("{id: \\d+}")
    public ReservaDTO getReserva (@PathParam ("id")Long id) throws RestauranteLogicException{
        existsCliente(idCliente);
        ReservaEntity entity = reservaLogic.getReserva(idCliente, id);
        if (entity.getCliente() != null && !idCliente.equals(entity.getCliente().getId())) {
            throw new WebApplicationException(404);
        }
        return new ReservaDTO(entity);
    }   
    /**
     * Acrualiza los datos de una reserva.
     * @param reserva a con los datos a modificar.
     * @return la reserva con los datos ya actualizados.
     * @throws LogicaRestauranteException si no encuentra la reserva a modificar.
     */
    
    @PUT
    @Path ("{id: \\d+}")
    public ReservaDTO uptadeReserva (ReservaDTO reserva) throws RestauranteLogicException {
        existsCliente(idCliente);
        existsReserva(reserva.getId());
        ReservaEntity entity = reserva.toEntity();
        return new ReservaDetailDTO(reservaLogic.updateReserva(idCliente,entity));
    }
    /**
     * Elimina una reserva
     * @param id de la reserva a eliminar.
     * @throws LogicaRestauranteException si la reserva no es encontrada.
     */
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteReserva (@PathParam ("id")Long id) throws RestauranteLogicException {
        existsCliente(idCliente);
        existsReserva(id);
        reservaLogic.deleteReserva(idCliente, id);
    } 
    
}
