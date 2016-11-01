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
    
    public MockTarjetasPuntos() 
    {      }    
    
    public TarjetaPuntosDTO darTarjetaPuntos(Long idCliente) throws LogicaRestauranteException 
    {   return null;    }

    public TarjetaPuntosDTO crearTarjetaPuntos(Long idCliente) throws LogicaRestauranteException
    {   return null;    }

    public TarjetaPuntosDTO actualizarTarjetaPuntos(Long idCliente, TarjetaPuntosDTO tarjetaPuntosActualizada) throws LogicaRestauranteException
    {   return null;    }
   
    public void eliminarTarjetaPuntos(Long idCliente) throws LogicaRestauranteException
    {    }
      
    public TarjetaPuntosDTO sumarPuntosTarjetaPuntos(Long idCliente, int pCompra) throws LogicaRestauranteException
    {   return null;    }
}
