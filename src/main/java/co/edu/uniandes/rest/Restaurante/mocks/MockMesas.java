package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MesaDTO;
import co.edu.uniandes.rest.cities.exceptions.LogicaRestauranteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock del recurso Cliente.
 * @author jdguz
 */
public class MockMesas 
{	
    // Objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(MockMesas.class.getName());
	
    // Arreglo de clientes.
    private static ArrayList<MesaDTO> mesas;
    
    /**
     * Constructor.
     */
    public MockMesas() 
    {
    	if (mesas == null) 
        {
            mesas = new ArrayList<MesaDTO>();
            mesas.add(new MesaDTO(1, 1, 4 , false));
            mesas.add(new MesaDTO(2, 1, 4 , false));
            mesas.add(new MesaDTO(3, 1, 4 , false));
            mesas.add(new MesaDTO(4, 1, 4 , true));
        }
        
    	// Indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// Muestra información 
    	logger.info("Inicializada la lista de mesas");
    	logger.info("Mesas:\n" + mesas );
    }    
    
    /**
    * Obtiene el listado de mesas. 
    * @return Lista de mesas.
    * @throws LogicaRestauranteException cuando no existe la lista en memoria  
    */    
    public List<MesaDTO> darMesas() throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar todas las mesas.");
        
    	if (mesas == null) 
        {
    		logger.severe("Error interno: lista de mesas no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de mesas no existe.");    		
    	}
    	logger.info("Retornando todas las mesas.");
    	return mesas;
    }
    
        /**
    * Obtiene la mesa con el id que entra por parametro. 
    * @param pId Id de la mesa que se busca.
    * @return MesaDTO Mesa buscada.
    * @throws LogicaRestauranteException Cuando no existe una mesa con el id buscado.  
    */    
    public MesaDTO darMesa(Long pId) throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar la mesa con id "+pId+".");
        
    	if (mesas == null) 
        {
    		logger.severe("Error interno: lista de mesas no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de mesas no existe.");    		
    	}
        
        for(MesaDTO mesa:mesas)
        {
            if(mesa.getId()==(pId))
            {
                logger.info("Retornando la mesa.");
                return mesa;
            }
        }
    	logger.severe("Error de uso: Se pidio una mesa que no existe.");
    	throw new LogicaRestauranteException("Error de uso: Se pidio una mesa que no existe.");
    }

    /**
     * Agrega una mesa al sistema.
     * @param nuevaMesa Mesa a agregar.
     * @return Mesa agregada.
     * @throws LogicaRestauranteException Cuando se intenta crear una mesa con un id que ya existe. 
     */
    public MesaDTO crearMesa(MesaDTO nuevaMesa) throws LogicaRestauranteException
    {
        int idMesaAAgregar = nuevaMesa.getId();
    	logger.info("Recibiendo solicitud de agregar la mesa con id "+idMesaAAgregar+".");
        
    	// Se busca que no exista una mesa con ese id.
	for (MesaDTO mesa : mesas) 
        {
            if(mesa.getId()==(idMesaAAgregar))
            {
                logger.severe("Error de uso: Se intento crear una mesa con un id "+idMesaAAgregar+" que ya existia.");
                throw new LogicaRestauranteException("Error de uso: Se intento crear una mesa con un id "+idMesaAAgregar+" que ya existia.");
            }
        }
        // Se Agrega la mesa.
    	logger.info("Agregada satisfactoriamente la mesa");
        mesas.add(nuevaMesa);
        return nuevaMesa;
    }


    /**
     * Recibe una mesa y la actualiza en el sistema.
     * @param nuevaMesa Mesa a agregar.
     * @return Mesa agregada.
     * @throws LogicaRestauranteException Cuando no existe una mesa con el id a actualizar. 
     */
    public MesaDTO actualizarMesa(MesaDTO mesaActualizada) throws LogicaRestauranteException
    {   
       int id = mesaActualizada.getId();
       logger.info("Recibiendo solicitud de actualizar la mesa con id "+id+"."); 
       
       
       // Se busca la mesa a actualizar
        for (MesaDTO mesa : mesas) 
        {
            if(mesa.getId()==(id))
            {
                int piso = mesaActualizada.getPiso();
                int sillas =mesaActualizada.getSillas();
                boolean estado = mesaActualizada.isEstado();
                
                mesa.setPiso(piso);
                mesa.setSillas(sillas);              
                mesa.setEstado(estado);
                
                logger.info("Actualizada satisfactoriamente."); 
                return mesa;
            }
	}
       
      // Si se llega hasta aca es porque no se encontro mesa con el id buscado.
        logger.severe("Error de uso: Se pidio actualizar una mesa que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio actualizar una mesa que no existe.");
   }
   
     /**
     * Elimina el cliente con el id dado.
     * @param pId Identificacion del cliente que se quiere eliminar.
     * @throws LogicaRestauranteException Cuando no existe un cliente con el id que se quiere eliminar. 
     */
      public void eliminarMesa(int pId) throws LogicaRestauranteException
   {
       logger.info("Recibiendo solicitud de eliminar la mesa con id: " + pId+".");
       boolean eliminado = false;
       
        // Se busca el cliente a eliminar
        for (int i = 0; i< mesas.size() && !eliminado; i++) 
        {
            MesaDTO mesa = mesas.get(i);
            if(mesa.getId()==(pId))
            {
                mesas.remove(i);
                eliminado = true;
            }
        }
       
        if(!eliminado)
        {
        // Si se llega hasta aca es porque no se encontro mesa con el id buscado.
        logger.severe("Error de uso: Se pidio eliminar una mesa con id "+pId+" que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio eliminar una mesa con id "+pId+" que no existe.");
        }
        logger.info("Eliminada satisfactoriamente.");
   }
}
