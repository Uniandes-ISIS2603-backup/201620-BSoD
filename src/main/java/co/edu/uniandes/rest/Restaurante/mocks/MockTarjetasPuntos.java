package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock del recurso TarjetaPuntos.
 * @author jdguz
 */
public class MockTarjetasPuntos 
{	
    // Objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(MockTarjetasPuntos.class.getName());
	
    // Arreglo de tarjetas de puntos.
    private static ArrayList<TarjetaPuntosDTO> tarjetasPuntos;
    
    /**
     * Constructor.
     */
    public MockTarjetasPuntos() 
    {
    	if (tarjetasPuntos == null) 
        {
            tarjetasPuntos = new ArrayList<TarjetaPuntosDTO>();
            tarjetasPuntos.add(new TarjetaPuntosDTO(1, new Date(), 0));
            tarjetasPuntos.add(new TarjetaPuntosDTO(2, new Date(), 0));
        }
        
    	// Indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// Muestra información 
    	logger.info("Inicializada la lista de tarjetas de puntos.");
    	logger.info("Tarjetas de puntos:\n" + tarjetasPuntos );
    }    
    
    /**
    * Obtiene el listado de tarjetas de puntos. 
    * @return Lista de tarjetas de puntos.
    * @throws LogicaRestauranteException cuando no existe la lista en memoria  
    */    
    public List<TarjetaPuntosDTO> darTarjetasPuntos() throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar todas las tarjetas de puntos.");
        
    	if (tarjetasPuntos == null) 
        {
    		logger.severe("Error interno: lista de tarjetas de puntos no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de tarjetas de puntos no existe.");    		
    	}
    	logger.info("Retornando todas las tarjetas de puntos.");
    	return tarjetasPuntos;
    }
    
    /**
    * Obtiene la tarjeta de puntos con el id que entra por parametro. 
    * @param pId Id de la tarjeta puntos que se busca.
    * @return TarjetaPuntosDTO Tarjeta de puntos buscada.
    * @throws LogicaRestauranteException Cuando no existe una tarjeta de puntos con el id buscado.  
    */    
    public TarjetaPuntosDTO darTarjetaPuntos(int pId) throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar la tarjeta de puntos con id "+pId+".");
        
    	if (tarjetasPuntos == null) 
        {
    		logger.severe("Error interno: lista de tarjeta de puntos no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de tarjeta de puntos no existe.");    		
    	}
        
        for(TarjetaPuntosDTO tarjetaPuntos:tarjetasPuntos)
        {
            if(tarjetaPuntos.getId()==(pId))
            {
                logger.info("Retornando la tarjeta de puntos.");
                return tarjetaPuntos;
            }
        }
    	logger.severe("Error de uso: Se pidio una tarjeta de puntos que no existe.");
    	throw new LogicaRestauranteException("Error de uso: Se pidio una tarjea de puntos que no existe.");
    }

    /**
     * Agrega una tarjeta de puntos al sistema.
     * @param nuevaTarjetaPuntos Tarjeta de puntos a agregar.
     * @return TarjetaPuntos Tarjeta de puntos agregada.
     * @throws LogicaRestauranteException Cuando se intenta crear una tarjeta de puntos con un id que ya existe 
     */
    public TarjetaPuntosDTO crearTarjetaPuntos(TarjetaPuntosDTO nuevaTarjetaPuntos) throws LogicaRestauranteException
    {
        int idTarjetaPuntosAAgregar = nuevaTarjetaPuntos.getId();
        logger.info("Recibiendo solicitud de agregar tarjeta de puntos con id "+idTarjetaPuntosAAgregar+".");
        
    	// Se busca que no exista un cliente con ese id.
	for (TarjetaPuntosDTO tarjetaPuntos: tarjetasPuntos) 
        {
            if(tarjetaPuntos.getId()==(idTarjetaPuntosAAgregar))
            {
                logger.severe("Error de uso: Se intento crear una tarjeta de puntos con un id "+idTarjetaPuntosAAgregar+" que ya existia.");
                throw new LogicaRestauranteException("Error de uso: Se intento crear unaa tarjeta de puntos con un id "+idTarjetaPuntosAAgregar+" que ya existia.");
            }
        }
        // Se Agrega el cliente.
    	logger.info("Agregada satisfactoriamente la tarjeta de puntos.");
        tarjetasPuntos.add(nuevaTarjetaPuntos);
        return nuevaTarjetaPuntos;
    }

    /**
     * Recibe una tarjeta de puntos y la actualiza en el sistema.
     * @param tarjetaPuntosActualizada TarjetaPuntos a actualizar.
     * @return TarjetaPuntos actualizada.
     * @throws LogicaRestauranteException Cuando no se le quiere modificar el id del dueño a la tarjeta de puntos.
     * @throws LogicaRestauranteException Cuando no existe una tarjeta de puntos con el id a actualizar. 
     */
    public TarjetaPuntosDTO actualizarTarjetaPuntos(TarjetaPuntosDTO tarjetaPuntosActualizada) throws LogicaRestauranteException
    {   
       int id = tarjetaPuntosActualizada.getId();
       logger.info("Recibiendo solicitud de actualizar la tarjeta de puntos con id "+id+"."); 
       
        for (TarjetaPuntosDTO tarjetaPuntos : tarjetasPuntos) 
        {
            if(tarjetaPuntos.getId()==(id))
            {
                tarjetaPuntos.setAcumulado(tarjetaPuntosActualizada.getAcumulado());
                tarjetaPuntos.setFechaCaducidad(tarjetaPuntosActualizada.getFechaCaducidad());
                logger.info("Actualizada satisfactoriamente."); 
                return tarjetaPuntos;
            }
	}
       
        logger.severe("Error de uso: Se pidio actualizar una tarjeta de puntos que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio actualizar una tarjeta de puntos que no existe.");
   }
   
     /**
     * Elimina la tarjeta de puntos con el id inidicado.
     * @param pId Identificacion de la tarjeta de puntos que se quiere eliminar.
     * @throws LogicaRestauranteException Cuando no existe una tarjeta de puntos con el id que se quiere eliminar. 
     */
      public void eliminarTarjetaPuntos(int pId) throws LogicaRestauranteException
   {
       logger.info("Recibiendo solicitud de eliminar la tarjeta de puntos con id: " + pId+".");
       boolean eliminado = false;
       
        for (int i = 0; i< tarjetasPuntos.size() && !eliminado; i++) 
        {
            TarjetaPuntosDTO tarjetaPuntos = tarjetasPuntos.get(i);
            if(tarjetaPuntos.getId()==(pId))
            {
                tarjetasPuntos.remove(i);
                eliminado = true;
            }
        }
       
        if(!eliminado)
        {
        logger.severe("Error de uso: Se pidio eliminar una tarjeta de puntos con id "+pId+" que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio eliminar una tarjeta de puntos con id "+pId+" que no existe.");
        }
        logger.info("Eliminada satisfactoriamente.");
   }
}
