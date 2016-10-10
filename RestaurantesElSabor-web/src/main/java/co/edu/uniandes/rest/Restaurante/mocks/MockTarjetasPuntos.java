package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.ClienteDTO;
import co.edu.uniandes.rest.Restaurante.dtos.TarjetaPuntosDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

import java.util.ArrayList;
import java.util.Date;
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
    
    private static MockClientes mockClientes;
    /**
     * Constructor.
     */
    public MockTarjetasPuntos() 
    {
        mockClientes = new MockClientes();
    	
        if (tarjetasPuntos == null) 
        {
            tarjetasPuntos = new ArrayList<>();
        }
        
    	// Indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// Muestra información 
    	logger.info("Inicializada la lista de tarjetas de puntos.");
    	logger.info("Tarjetas de puntos:\n" + tarjetasPuntos );
    }    
    
    public TarjetaPuntosDTO darTarjetaPuntos(Long idCliente) throws LogicaRestauranteException 
    {
        logger.info("Recibiendo solicitud de dar la tarjeta de puntos del cliente "+idCliente+".");
        
    	ClienteDTO cliente = mockClientes.darCliente(idCliente);
        
        if(cliente!=null)
        {
            return cliente.getTarjetaPuntos();
        }
    	logger.info("no se encontro, retornando NULL.");
    	return null;
    }

    public TarjetaPuntosDTO crearTarjetaPuntos(Long idCliente) throws LogicaRestauranteException
    {
        logger.info("Recibiendo solicitud de agregar tarjeta de puntos al cliente "+idCliente+".");
        
        logger.info("Generando id para un nueva tarjeta de puntos.");
        long newId = 1;
        for (TarjetaPuntosDTO tarjetaPuntos: tarjetasPuntos) 
        {
            if (newId <= tarjetaPuntos.getId()) 
            {
                newId = tarjetaPuntos.getId() + 1;
            }
        }
        
    	// Se busca que el cliente no tenga una tarjeta de puntos.
        ClienteDTO clienteAAgregarTarjeta = mockClientes.darCliente(idCliente);
        
        if(clienteAAgregarTarjeta.getTarjetaPuntos()!=null)
        {
            logger.severe("Error de uso: Se pidio agregar una tarjeta de puntos a un cliente que ya tenia una.");
            throw new LogicaRestauranteException("Error de uso: Se pidio agregar una tarjeta de puntos a un cliente que ya tenia una.");
        }
        TarjetaPuntosDTO nuevaTarjetaPuntos = new TarjetaPuntosDTO(newId, new Date(), 0);
        clienteAAgregarTarjeta.setTarjetaPuntos(nuevaTarjetaPuntos);
        
        logger.info("Agregada satisfactoriamente la tarjeta de puntos.");
        tarjetasPuntos.add(nuevaTarjetaPuntos);
        return nuevaTarjetaPuntos;
    }

    public TarjetaPuntosDTO actualizarTarjetaPuntos(Long idCliente, TarjetaPuntosDTO tarjetaPuntosActualizada) throws LogicaRestauranteException
    {   
       Long id = tarjetaPuntosActualizada.getId();
       logger.info("Recibiendo solicitud de actualizar la tarjeta de puntos "+id+" del cliente "+idCliente+"."); 
       
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
   
    public void eliminarTarjetaPuntos(Long idCliente) throws LogicaRestauranteException
    {
       logger.info("Recibiendo solicitud de eliminar la tarjeta de puntos del cliente " +idCliente+".");

        ClienteDTO cliente = mockClientes.darCliente(idCliente);
        cliente.setTarjetaPuntos(null);
        logger.info("Eliminada satisfactoriamente.");
    }
      
    public TarjetaPuntosDTO sumarPuntosTarjetaPuntos(Long idCliente, int pCompra) throws LogicaRestauranteException
    {
        logger.info("Recibiendo solicitud de sumar puntos a la tarjeta de puntos del cliente " +idCliente+" correspondiente a una compra de "+pCompra+".");
        ClienteDTO cliente = mockClientes.darCliente(idCliente);
        TarjetaPuntosDTO tarjeta = cliente.getTarjetaPuntos();
        int puntos = pCompra/10000;
        int acumulado = tarjeta.getAcumulado();
        int nuevoAcumulado = acumulado + puntos;
        tarjeta.setAcumulado(nuevoAcumulado);
        return tarjeta;
    }
}
