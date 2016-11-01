package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IDomicilioLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.rest.Restaurante.dtos.DomicilioDetailDTO;
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
 * @author cc.novoa11
 */
@Path("clientes/{idCliente: \\d+}/domicilios")
@Produces("application/json")
@Consumes("application/json")
public class RecursoDomicilio {
    
    @Inject
    private IFacturaLogic facturaLogic;
    
    @Inject
    private IDomicilioLogic domicilioLogic;



    /**
     * Obtiene el domicilio con el identificador buscado.
     *
     * @param id Identificador del domicilio buscado.
     * @return DomicilioDTO domicilio buscado.
     * @throws LogicaRestauranteException Si no existe un domicilio con el
     * identificador dado.
     */
    @GET
    @Path("{id: \\d+}")
    public DomicilioDTO getDomicilio(@PathParam("id") Long id ) throws RestauranteLogicException {
        DomicilioEntity entity = domicilioLogic.getDomicilio(id);
        return new DomicilioDTO(entity);
    }

    /**
     * Crea un domicilio con la información enviada como parámetro.
     *
     * @param domi La instancia domicilio que se quiere crear.
     * @return DomicilioDTO domicilio creado.
     * @throws LogicaRestauranteException Si ya existe un domicilio con ese id.
     */
    @POST
    public DomicilioDetailDTO createDomicilio(DomicilioDTO domi) throws RestauranteLogicException {
        return new DomicilioDetailDTO(domicilioLogic.createDomicilio(domi.toEntity()));
    }

    /**
     * Actualiza un domicilio con la información enviada como parámetro.
     *
     * @param Domicilio domi La instancia domicilio que se quiere actualizar.
     * @throws LogicaRestauranteException Si ya existe un domicilio con ese id.
     */
    @PUT
    @Path("{id: \\d+}")
    public DomicilioDetailDTO uptadeDomicilio(DomicilioDTO domi) throws RestauranteLogicException {
        return new DomicilioDetailDTO(domicilioLogic.updateDomicilio(domi.toEntity()));
    }

    /**
     * Elimina un domicilio con la información enviada como parámetro.
     *
     * @param DomicilioDTO domi La instancia domicilio que se quiere eliminar.
     * @throws LogicaRestauranteException Si ya existe un domicilio con ese id.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDomicilio(@PathParam("id") Long id) throws RestauranteLogicException {
        domicilioLogic.deleteDomicilio(id);
    }
}
