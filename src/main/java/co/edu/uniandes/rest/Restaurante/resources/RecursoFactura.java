/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.rest.Restaurante.dtos.FacturaDTO;
import co.edu.uniandes.rest.Restaurante.exceptions.LogicaRestauranteException;
import co.edu.uniandes.rest.Restaurante.mocks.MockFactura;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author cc.novoa11
 */
@Path("sucursal/{idSucursal: \\d+}/facturas ")
@Produces("application/json")
@Consumes("application/json")
public class RecursoFactura 
{
    MockFactura mockFactura = new MockFactura();
    
    /**
     * Retorna la lista de facturas.
     * @return lista de facturas.
     * @throws LogicaRestauranteException Si no existe una lista de facturas en el sistema.
     */
   
    @GET
    public List<FacturaDTO> getFacturas(@PathParam("idSucursal") Long idSucursal) throws LogicaRestauranteException 
    {
        return mockFactura.getFacturas(idSucursal);
    }
   
    /**
     * Obtiene factura con el identificador buscado.
     * @param id Identificador factura buscada.
     * @return FacturaDTO factura buscada.
     * @throws LogicaRestauranteException Si no existe factura con el identificador dado.
     */
    @GET
    @Path ("{id: \\d+}")
    public FacturaDTO getFactura (@PathParam("idSucursal") Long idSucursal, @PathParam ("id")Long id) throws LogicaRestauranteException 
    {
     return mockFactura.getFactura(idSucursal, id);
    }   
    
    /**
     * Crea factura con la información enviada como parámetro.
     * @param fac La instancia factura que se quiere crear.
     * @return FacturaDTO factura creada.
     * @throws LogicaRestauranteException Si ya existe factura con ese id.
     */
   @POST
    public FacturaDTO createFactura(@PathParam("idSucursal") Long idSucursal, FacturaDTO fac) throws LogicaRestauranteException
    {
        return mockFactura.createFactura(idSucursal, fac);
    }
 
    /**
     * Actualiza factura con la información enviada como parámetro.
     * @param fact La instancia factura que se quiere actualizar.
     * @throws LogicaRestauranteException Si ya existe un domicilio con ese id.
     */
   
    @PUT
    @Path ("{id: \\d+}")
    public FacturaDTO uptadeFactura (@PathParam("idSucursal") Long idSucursal, FacturaDTO fact) throws LogicaRestauranteException 
    {
     return mockFactura.updateFactura(idSucursal,fact);
    }
    
     /**
     * Elimina factura con la información enviada como parámetro.
     * @param fact La instancia factura que se quiere eliminar.
     * @throws LogicaRestauranteException Si ya existe factura con ese id.
     */
    
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteFactura (@PathParam("idSucursal") Long idSucursal, @PathParam ("id")Long id) throws LogicaRestauranteException {
       mockFactura.deleteFactura(idSucursal,id);  
    } 
}

