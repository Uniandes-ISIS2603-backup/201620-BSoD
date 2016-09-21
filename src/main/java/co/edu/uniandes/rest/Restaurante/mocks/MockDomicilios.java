package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cc.novoa11
 */
public class MockDomicilios {

    /**
     * Presenta los logs de las operaciones.
     */
    private final static Logger logger = Logger.getLogger(MockDomicilios.class.getName());

    /**
     * Arreglo de domicilios.
     */
    private static ArrayList<DomicilioDTO> domicilios;

    /**
     * Crea un nuevo Mock con los datos de ejemplo.
     */
    public MockDomicilios() {

        if (domicilios == null) {
            domicilios = new ArrayList<>();
            domicilios.add(new DomicilioDTO(1L, "Direccion1", "Plato 1", 12000, 1L));
            domicilios.add(new DomicilioDTO(2L, "Direccion2", "Plato 2", 18000, 2L));
            domicilios.add(new DomicilioDTO(3L, "Direccion3", "Plato 3", 15000, 2L));

        }

        // indica que se muestren todos los mensajes
        logger.setLevel(Level.INFO);

        // muestra información 
        logger.info("Inicializa la lista de domicilios");
        logger.info("domicilios" + domicilios);
    }

    /**
     * Retorna la lista de domicilios.
     *
     * @return lista de domicilios.
     * @throws LogicaRestauranteException
     */
    public List<DomicilioDTO> getDomicilios(Long idCliente) throws LogicaRestauranteException {
        if (domicilios == null) {
            logger.severe("Error interno: lista de domicilios no se encuentra.");
            throw new LogicaRestauranteException("Error interno: lista de domicilios no se encuentra.");
        }

        ArrayList<DomicilioDTO> domiciliosCliente = new ArrayList<DomicilioDTO>();
        
        for (DomicilioDTO domicilio : domicilios) {
            if( idCliente== domicilio.getidCliente() ){
                domiciliosCliente.add(domicilio);
            }
        }
        
        logger.info("retornando todos los domicilios");
        return domiciliosCliente;
    }

    /**
     * Obtener el domicilio con el id dado por parámetro.
     *
     * @param id
     * @return domicilio con el id dado.
     * @throws LogicaRestauranteException
     */
    public DomicilioDTO getDomicilio(Long idCliente, Long id) throws LogicaRestauranteException {

        if (domicilios == null) 
        {
    		logger.severe("Error interno: lista de domicilios no existe.");
    		throw new LogicaRestauranteException("Error interno: lista dedomicilios no existe.");    		
    	}
        
        for(DomicilioDTO domicilio :domicilios)
        {
            if(domicilio.getId().equals(id))
            {
                logger.info("Retornando domicilio con id "+id);
                return domicilio;
            }
        }
        logger.severe("Error interno: domicilio no existe.");
        throw new LogicaRestauranteException("Error interno: domicilio no existe.");
    }

    /**
     * Crea un nuevo domicilio y lo agrega a la lista.
     *
     * @param nDomicilio
     * @return domicilio creado.
     * @throws LogicaRestauranteException
     */
    public DomicilioDTO createDomicilio(Long idCliente, DomicilioDTO nDomicilio) throws LogicaRestauranteException {
        logger.info("recibiendo solicitud de agregar un domicilio " + nDomicilio);

        if (nDomicilio.getId() != null) {
            for (DomicilioDTO domi : domicilios) {
                if (Objects.equals(domi.getId(), nDomicilio.getId())) {
                    logger.severe("Ya existe una domicilio con ese id");
                    throw new LogicaRestauranteException("Ya existe una domicilio con ese id");
                }
            }

        } else {
            logger.info("Generando id para un nuevo domicilio");
            long newId = 1;
            for (DomicilioDTO domi : domicilios) {
                if (newId <= domi.getId()) {
                    newId = domi.getId() + 1;
                }
            }
            nDomicilio.setId(newId);
            nDomicilio.setidCliente(idCliente);
        }
        

        logger.info("agregando domicilio " + nDomicilio);
        domicilios.add(nDomicilio);
        return nDomicilio;
    }

    /**
     * Actualiza el domicilio dado por parámetro.
     *
     * @param domicilio
     * @return domicilio actualizado con la información dada.
     * @throws LogicaRestauranteException
     */
    public DomicilioDTO updateDomicilio(Long idCliente,Long id, DomicilioDTO domicilio) throws LogicaRestauranteException {
        for (DomicilioDTO domi : domicilios) {

            if (Objects.equals(domi.getId(), domicilio.getId())) {
                domi.setId(id);
                domi.setDireccion(domicilio.getDireccion());
                domi.setPlato(domicilio.getPlato());
                domi.setPrecio(domicilio.getPrecio());
                return domi;
            }
        }
        logger.severe("Error interno: El domicilio no existe.");
        throw new LogicaRestauranteException("Error interno: El domicilio no existe.");
    }

    /**
     * Elimina el domicilio con el id dado por parámetro.
     *
     * @param id
     * @throws LogicaRestauranteException
     */
    public void deleteDomicilio(Long idCliente, Long id) throws LogicaRestauranteException {
        boolean eliminado = false;

        for (int i = 0; i < domicilios.size() && !eliminado; i++) {
            DomicilioDTO domi = domicilios.get(i);
            if (domi.getId().equals(id)) {
                domicilios.remove(i);
                eliminado = true;
            }
        }

        if (!eliminado) {
            logger.severe("Error de uso: Se pidio eliminar un domicilio con id " + id + " que no existe.");
            throw new LogicaRestauranteException("Error de uso: Se pidio eliminar un domicilio con id " + id + " que no existe.");
        }
    }

}
