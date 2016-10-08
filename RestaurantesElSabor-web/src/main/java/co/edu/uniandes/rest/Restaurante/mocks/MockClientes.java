package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.DomicilioDTO;
import co.edu.uniandes.rest.Restaurante.dtos.MedioPagoDTO;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Mock del recurso Cliente.
 * @author jdguz
 */
public class MockClientes 
{	
    // Objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(MockClientes.class.getName());
	
    // Arreglo de clientes.
    private static ArrayList<ClienteDTO> clientes;
    
    public MockClientes() 
    {
    	if (clientes == null) 
        {                     
            clientes = new ArrayList<>();
            clientes.add(new ClienteDTO(1L, 940101, "CC", "Nombre1", "Apellidos1", "Direccion1", 3131, null, null));
            clientes.add(new ClienteDTO(2L, 940101, "CC", "Nombre2", "Apellidos2", "Direccion2", 3132, null, null));
            clientes.add(new ClienteDTO(3L, 940101, "CC", "Nombre3", "Apellidos3", "Direccion3", 3133, null, null));
        }
        
    	// Indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// Muestra información 
    	logger.info("Inicializada la lista de clientes");
    	logger.info("Clientes:\n" + clientes );
    }    
    
    public List<ClienteDTO> darClientes() throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar todos los clientes.");
        
    	if (clientes == null) 
        {
    		logger.severe("Error interno: lista de clientes no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de clientes no existe.");    		
    	}
    	logger.info("Retornando todos los clientes.");
    	return clientes;
    }
  
    public ClienteDTO darCliente(Long pId) throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar el cliente con id "+pId+".");
        
    	if (clientes == null) 
        {
    		logger.severe("Error interno: lista de clientes no existe.");
    		throw new LogicaRestauranteException("Error interno: lista de clientes no existe.");    		
    	}
        
        for(ClienteDTO cliente:clientes)
        {
            if(cliente.getId().equals(pId))
            {
                logger.info("Retornando el cliente.");
                return cliente;
            }
        }
    	logger.severe("Error de uso: Se pidio un cliente que no existe.");
    	throw new LogicaRestauranteException("Error de uso: Se pidio un cliente que no existe.");
    }

    public ClienteDTO crearCliente(ClienteDTO nuevoCliente) throws LogicaRestauranteException
    {
    	logger.info("Recibiendo solicitud de agregar cliente.");
        
    	// Se busca que no exista un cliente con ese id.
	if (nuevoCliente.getId() != null) 
        {
            for (ClienteDTO cliente : clientes) 
            {
                if (Objects.equals(cliente.getId(), nuevoCliente.getId())) 
                {
                    logger.severe("Ya existe un cliente con ese id");
                    throw new LogicaRestauranteException("Ya existe un cliente con ese id");
                }
            }
        } 
        else 
        {
            logger.info("Generando id para un nuevo cliente");
            long newId = 1;
            for (ClienteDTO cliente : clientes) 
            {
                if (newId <= cliente.getId()) 
                {
                    newId = cliente.getId() + 1;
                }
            }
            nuevoCliente.setId(newId);
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
       logger.info("Recibiendo solicitud de actualizar el cliente: "+clienteActualizado); 
       
       // Se busca el cliente a actualizar
        for (ClienteDTO cliente : clientes) 
        {
            if(cliente.getId().equals(id))
            {
                int documentoIdentidad = clienteActualizado.getDocumentoIdentidad();
                String tipoDocumentoIdentidad = clienteActualizado.getTipoDocumentoIdentidad();
                String name = clienteActualizado.getName();
                String apellidos =clienteActualizado.getApellidos();
                String direccion = clienteActualizado.getDireccion();
                int telefono = clienteActualizado.getTelefono();
                TarjetaPuntosDTO tarjetaPuntos = clienteActualizado.getTarjetaPuntos();
                ArrayList<MedioPagoDTO> mediosPago = clienteActualizado.getMediosPago();
                
                cliente.setDocumentoIdentidad(documentoIdentidad);
                cliente.setTipoDocumentoIdentidad(tipoDocumentoIdentidad);
                cliente.setName(name);
                cliente.setName(name);
                cliente.setApellidos(apellidos);
                cliente.setDireccion(direccion);
                cliente.setTelefono(telefono);
                cliente.setTarjetaPuntos(tarjetaPuntos);
                cliente.setMediosPago(mediosPago);
                
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
       logger.info("Recibiendo solicitud de eliminar el cliente+.");
       boolean eliminado = false;
       
        // Se busca el cliente a eliminar
        for (int i = 0; i< clientes.size() && !eliminado; i++) 
        {
            ClienteDTO cliente = clientes.get(i);
            if(cliente.getId().equals(pId))
            {
                clientes.remove(i);
                eliminado = true;
            }
        }
       
        if(!eliminado)
        {
        // Si se llega hasta aca es porque no se encontro cliente con el id buscado.
        logger.severe("Error de uso: Se pidio eliminar un cliente con id que no existe.");
        throw new LogicaRestauranteException("Error de uso: Se pidio eliminar un cliente con id que no existe.");
        }
        logger.info("Eliminado satisfactoriamente.");
   }
}
