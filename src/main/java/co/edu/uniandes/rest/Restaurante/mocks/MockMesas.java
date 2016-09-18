package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MesaDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

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
	
    // Arreglo de mesas.
    private static ArrayList<MesaDTO> mesas;
    
    /**
     * Constructor.
     */
    public MockMesas() 
    {
    	if (mesas == null) 
        {
            mesas = new ArrayList<MesaDTO>();
            mesas.add(new MesaDTO(1L, 1, 4 , 1L));
            mesas.add(new MesaDTO(2L, 1, 4 , 2L));
            mesas.add(new MesaDTO(3L, 1, 4 , 1L));
            mesas.add(new MesaDTO(4L, 1, 4 , 2L));
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
    public List<MesaDTO> darMesas(Long idSucursal) throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar todas las mesas.");
        
    	if (mesas == null) 
        {
    		logger.severe("Error interno: lista de mesas no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de mesas no existe.");    		
    	}
         ArrayList<MesaDTO> mesasSucursal = new ArrayList<MesaDTO>();
        
        for (MesaDTO mesa : mesas) {
            if( idSucursal== mesa.getidSucursal() || mesa.getidSucursal() ==0){
                mesasSucursal.add(mesa);
            }
        }
    	logger.info("Retornando todas las mesas.");
    	return mesasSucursal;
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
    public MesaDTO crearMesa(Long idSucursal, MesaDTO nuevaMesa) throws LogicaRestauranteException
    {
        logger.info("Recibiendo solicitud de agregar una mesa.");
        Long idMesaAAgregar = nuevaMesa.getId();
    	// Se busca que no exista una mesa con ese id.
	if ( nuevaMesa.getId() != null ) 
        {
	    	for (MesaDTO domi : mesas) 
                {
	            if (Objects.equals(domi.getId(), nuevaMesa.getId()))
                    {
	            	logger.severe("Ya existe una domicilio con ese id");
	                throw new LogicaRestauranteException("Ya existe una domicilio con ese id");
	            }
	        }
	        
    	} else 
        {
    		logger.info("Generando id para un nuevo domicilio");
    		long newId = 1;
	        for (MesaDTO domi : mesas) 
                {
	            if (newId <= domi.getId()){
	                newId =  domi.getId() + 1;
	            }
	        }
	        nuevaMesa.setId(newId);
                nuevaMesa.setidSucursal(idSucursal);
    	}
    	// Se busca que no exista un cliente con ese id.
	
	
        // Se Agrega el cliente.
    	logger.info("Agregando Mesa: " + nuevaMesa);
        mesas.add(nuevaMesa);
        return nuevaMesa;
    }


    /**
     * Recibe una mesa y la actualiza en el sistema.
     * @param nuevaMesa Mesa a agregar.
     * @return Mesa agregada.
     * @throws LogicaRestauranteException Cuando no existe una mesa con el id a actualizar. 
     */
    public MesaDTO actualizarMesa(Long id, MesaDTO mesaActualizada) throws LogicaRestauranteException
    {   
       logger.info("Recibiendo solicitud de actualizar la mesa con id "+id+"."); 
       
       
       // Se busca la mesa a actualizar
        for (MesaDTO mesa : mesas) 
        {
            if(Objects.equals(mesa.getId(), id))
            {
                int piso = mesaActualizada.getPiso();
                int cantSillas =mesaActualizada.getCantSillas();
                
                mesa.setPiso(piso);
                mesa.setCantSillas(cantSillas);
                
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
      public void eliminarMesa(Long pId) throws LogicaRestauranteException
   {
       logger.info("Recibiendo solicitud de eliminar la mesa con id: " + pId+".");
       boolean eliminado = false;
       
        // Se busca el cliente a eliminar
         for (MesaDTO mesa : mesas) {
            if (Objects.equals(mesa.getId(), pId)) {

                // elimina la ciudad
                logger.info("eliminando mesa " + mesa);
                mesas.remove(mesa);
                return;
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
