package co.edu.uniandes.rest.Restaurante.mocks;

import co.edu.uniandes.rest.Restaurante.dtos.FacturaDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cc.novoa11
 */
public class MockFactura {
/**
     * Presenta los logs de las operaciones.
     */
    private final static Logger logger = Logger.getLogger(MockFactura.class.getName());
    
    /**
     * Arreglo de facturas.
     */
    private static ArrayList<FacturaDTO> facturas;
    
    /**
     * Crea un nuevo Mock con los datos de ejemplo.
     */
    public MockFactura() {
        
        if (facturas == null) {
            facturas = new ArrayList<>();
            facturas.add(new FacturaDTO(1L, 1L, 1L, new Date(), 4000));

        }
        
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de domicilios");
    	logger.info("facturas" + facturas );
    }
   /**
    * Retorna la lista de facturas.
    * @return lista de facturas.
    * @throws LogicaRestauranteException 
    */
    public List<FacturaDTO> getFacturas(Long idSucursal) throws LogicaRestauranteException 
    {
    	if (facturas == null) {
    		logger.severe("Error interno: lista de facturas no se encuentra.");
    		throw new LogicaRestauranteException("Error interno: lista de facturas no se encuentra.");    		
    	}
        
        ArrayList<FacturaDTO> facturasSucursal = new ArrayList<FacturaDTO>();
        
        for (FacturaDTO fact : facturas) {
            if( idSucursal== fact.getIdSucursal() ){
                facturasSucursal.add(fact);
            }
        }
        
        logger.info("retornando todos los domicilios");
        return facturasSucursal;
    	
    }
    
    /**
     * Obtener la factura con el id dado por parámetro.
     * @param id
     * @return factura con el id dado.
     * @throws LogicaRestauranteException 
     */
    
    public FacturaDTO getFactura(Long idSucursal, Long id) throws LogicaRestauranteException{
     
          for (FacturaDTO fa : facturas) {

	            if (Objects.equals(fa.getId(), id)){
                         return fa;
                    }
        }
        logger.severe("Error interno: factura no existe.");
    		throw new LogicaRestauranteException("Error interno: factura no existe.");
    }
    
    /**
     * Crea nueva factura y la agrega a la lista.
     * @param nFactura 
     * @return factura creada.
     * @throws LogicaRestauranteException 
     */
    
    public FacturaDTO createFactura(Long idSucursal, FacturaDTO nFactura) throws LogicaRestauranteException 
    {
    	logger.info("recibiendo solicitud de agregar un domicilio " + nFactura);
    	
       	if ( nFactura.getId() != null ) 
        {
	    	for (FacturaDTO fact : facturas) 
                {
	            if (Objects.equals(fact.getId(), nFactura.getId()))
                    {
	            	logger.severe("Ya existe una factura con ese id");
	                throw new LogicaRestauranteException("Ya existe una factura con ese id");
	            }
	        }
	        
    	} else 
        {
    		logger.info("Generando id para un nuevo factura");
    		long newId = 1;
	        for (FacturaDTO fact : facturas) 
                {
	            if (newId <= fact.getId()){
	                newId =  fact.getId() + 1;
	            }
	        }
	        nFactura.setId(newId);
    	}
    	
    	logger.info("agregando factura " + nFactura);
        facturas.add(nFactura);
        return nFactura;
    }
    
    /**
     * Actualiza la factura dado por parámetro.
     * @param factura
     * @return factura actualizada con la información dada.
     * @throws LogicaRestauranteException 
     */
    
    public FacturaDTO updateFactura(Long idSucursal, FacturaDTO factura) throws LogicaRestauranteException{
       for (FacturaDTO fa : facturas) {

	    if (Objects.equals(fa.getId(), factura.getId()))
            {
                fa.setTotal(factura.getTotal());
                return fa;
            }
        }
       logger.severe("Error interno: Factura no existe.");
    		throw new LogicaRestauranteException("Error interno: Factura no existe.");
   }
    
    /**
     * Elimina factura con el id dado por parámetro.
     * @param id
     * @throws LogicaRestauranteException 
     */
    
    public void deleteFactura(Long idSucursal, Long id) throws LogicaRestauranteException
    {
        boolean eliminado = false;
        
         for (int i = 0; i< facturas.size() && !eliminado; i++) 
         {
             FacturaDTO fact = facturas.get(i);
             if(fact.getId().equals(id))
             {
                 facturas.remove(i);
                 eliminado = true;
        }
         }
        
         if(!eliminado)
         {
         logger.severe("Error de uso: Se pidio eliminar factura con id "+id+" que no existe.");
         throw new LogicaRestauranteException("Error de uso: Se pidio eliminar factura con id "+id+" que no existe.");
         }
    }
   
}

