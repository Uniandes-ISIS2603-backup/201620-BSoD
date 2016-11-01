package co.edu.uniandes.rest.Resturante.mappers;

import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 * Convertidor de Excepciones LogicaRestauranteException a mensajes REST.
 */
@Provider
public class RestauranteLogicExceptionMapper implements ExceptionMapper<RestauranteLogicException> 
{
	/**
	 * Generador de una respuesta a partir de una excepción
	 * @param ex excecpión a convertir a una respuesta REST
	 */
	public Response toResponse(RestauranteLogicException ex) 
        {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}
	
}
