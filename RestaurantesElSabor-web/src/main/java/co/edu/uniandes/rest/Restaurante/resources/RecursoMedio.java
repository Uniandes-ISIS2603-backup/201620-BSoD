package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IMedioPagoLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.MedioPagoDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MedioPagoDetailDTO;
import co.edu.uniandes.rest.Restaurante.dtos.ClienteDetailDTO;
import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author igomez10
 */
@Path("/clientes/{idCliente: \\d+}/medios")
@Produces("application/json")
@Consumes("application/json")
public class RecursoMedio
    {
        @Inject
        private IMedioPagoLogic medioLogic;
        @Inject
        private IClienteLogic clienteLogic;
        
        @PathParam("idCliente")
        private Long idCliente;
        
        private List<MedioPagoDetailDTO> listEntityDTO(List<MedioPagoEntity> entityList){
            List<MedioPagoDetailDTO> list = new ArrayList<>();
            for (MedioPagoEntity entity : entityList){
                list.add(new MedioPagoDetailDTO(entity));
            }
            return list;
        }
        public void existsCliente(Long clienteId) {
        ClienteDetailDTO clien = new ClienteDetailDTO(clienteLogic.getCliente(clienteId));
        if (clien == null) {
            throw new WebApplicationException("El cliente no existe", 404);
        }
        }
        public void existsMedioPago(Long medioId) {
            try{
            MedioPagoDetailDTO medio = new MedioPagoDetailDTO(medioLogic.darMedio(medioId));
            if (medio == null) {
            throw new WebApplicationException("El medio de pago no existe", 404);
            }
            }catch(RestauranteLogicException e){
                throw new WebApplicationException("El medio de pago no existe", 404);
            }
        }
    
    

    /**
     * Obtiene una lista con todos los medios de pago.
     * @return lista de medios de pago.
     * @throws LogicaRestauranteException Si no existe una lista de medios de pago en el sistema.
     */
    @GET
    public List<MedioPagoDetailDTO> darMedios() throws RestauranteLogicException
    {
        existsCliente(idCliente);
        List<MedioPagoEntity> medios = medioLogic.darMedios(idCliente);
        return listEntityDTO(medios);
    }

    @GET
    @Path("{id: \\d+}")
      public MedioPagoDTO darMedio(@PathParam("id") Long pId) throws RestauranteLogicException
    {
        existsCliente(idCliente);
        LOGGER.log(Level.INFO, "Consultando cliente con idCliente = {0}", idCliente);
        MedioPagoEntity entity = medioLogic.darMedio(pId);
        LOGGER.log(Level.INFO, "Consultando cliente con id = {0}", entity.getCliente().getId());
        if (entity.getCliente() != null && !idCliente.equals(entity.getCliente().getId())) {
            throw new WebApplicationException(404);
        }
        return new MedioPagoDTO(entity);
    }

    @POST
    public MedioPagoDTO crearMedio(MedioPagoDetailDTO nuevoMedio) throws RestauranteLogicException
    {
        return new MedioPagoDetailDTO(medioLogic.crearMedio(nuevoMedio.toEntity()));
    }

    @PUT
    public MedioPagoDTO actualizarMedio(MedioPagoDetailDTO medioActualizado) throws RestauranteLogicException 
    {
        existsCliente(idCliente);
        existsMedioPago(medioActualizado.getId());
        MedioPagoEntity entity = medioActualizado.toEntity();
        return new MedioPagoDetailDTO(medioLogic.actualizarMedio(entity));
    }

    @DELETE
    @Path("{id: \\d+}")
    public void eliminarMedio(@PathParam("id") Long pId) throws RestauranteLogicException 
    {
        existsCliente(idCliente);
        existsMedioPago(pId);
        medioLogic.eliminarMedio(pId);
    }
}
