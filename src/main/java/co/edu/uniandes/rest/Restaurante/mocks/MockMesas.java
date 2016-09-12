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
     * Agrega un cliente al sistema.
     * @param nuevoCliente Cliente a agregar.
     * @return Cliente agregado.
     * @throws LogicaRestauranteException Cuando se intenta crear un cliente con un id que ya existe. 
     */
    public ClienteDTO crearCliente(ClienteDTO nuevoCliente) throws LogicaRestauranteException
    {
        Long idClienteAAgregar = nuevoCliente.getId();
    	logger.info("Recibiendo solicitud de agregar cliente con id "+idClienteAAgregar+".");
        
    	// Se busca que no exista un cliente con ese id.
	for (ClienteDTO cliente : clientes) 
        {
            if(cliente.getId().equals(idClienteAAgregar))
            {
                logger.severe("Error de uso: Se intento crear un cliente con un id "+idClienteAAgregar+" que ya existia.");
                throw new LogicaRestauranteException("Error de uso: Se intento crear un cliente con un id "+idClienteAAgregar+" que ya existia.");
            }
        }
        // Se Agrega el cliente.
    	logger.info("Agregado satisfactoriamente el cliente");
        clientes.add(nuevoCliente);
        return nuevoCliente;
    }


    /**
     * Recibe un cliente y lo actualiza en el sistema.
     * @param nuevoCliente Cliente a agregar.
     * @return Cliente agregado.
     * @throws LogicaRestauranteException Cuando no existe un cliente con el id a actualizar. 
     */
    public ClienteDTO actualizarCliente(ClienteDTO clienteActualizado) throws LogicaRestauranteException
    {   
       Long id = clienteActualizado.getId();
       logger.info("Recibiendo solicitud de actualizar el cliente con id "+id+"."); 
       
       
       // Se busca el cliente a actualizar
        for (ClienteDTO cliente : mesas) 
        {
            if(cliente.getId().equals(id))
            {
                String nombre = clienteActualizado.getNombre();
                String apellidos =clienteActualizado.getApellidos();
                String direccion = clienteActualizado.getDireccion();
                
                if(nombre != null && !nombre.equalsIgnoreCase(""))
                {
                    cliente.setNombre(nombre);
                }
                if(apellidos !=null && !apellidos.equalsIgnoreCase(""))
                {
                    cliente.setApellidos(apellidos);
                }
                if(direccion != null && !direccion.equalsIgnoreCase(""))
                {
                    cliente.setDireccion(direccion);
                }
                logger.info("Actualizado satisfactoriamente."); 
                return cliente;
            }
	}
       
      // Si se llega hasta aca es porque no se encontro cliente con el id buscado.
        logger.severe("Error de uso: Se pidio actualizar un cliente que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio actualizar un cliente que no existe.");
   }
   
     /**
     * Elimina el cliente con el id dado.
     * @param pId Identificacion del cliente que se quiere eliminar.
     * @throws LogicaRestauranteException Cuando no existe un cliente con el id que se quiere eliminar. 
     */
      public void eliminarCliente(Long pId) throws LogicaRestauranteException
   {
       logger.info("Recibiendo solicitud de eliminar el cliente con id: " + pId+".");
       boolean eliminado = false;
       
        // Se busca el cliente a eliminar
        for (int i = 0; i< mesas.size() && !eliminado; i++) 
        {
            ClienteDTO cliente = mesas.get(i);
            if(cliente.getId().equals(pId))
            {
                mesas.remove(i);
                eliminado = true;
            }
        }
       
        if(!eliminado)
        {
        // Si se llega hasta aca es porque no se encontro cliente con el id buscado.
        logger.severe("Error de uso: Se pidio eliminar un cliente con id "+pId+" que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio eliminar un cliente con id "+pId+" que no existe.");
        }
        logger.info("Eliminado satisfactoriamente.");
   }
}
