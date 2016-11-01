/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.resources;

import co.edu.uniandes.bsod.restauranteselsabor.api.IClienteLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.IFacturaLogic;
import co.edu.uniandes.bsod.restauranteselsabor.api.ISucursalLogic;
import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.exceptions.RestauranteLogicException;
import co.edu.uniandes.rest.Restaurante.dtos.FacturaDTO;
import co.edu.uniandes.rest.Restaurante.dtos.FacturaDetailDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author cc.novoa11
 */
@Path("sucursal/{idSucursal: \\d+}/factura ")
@Produces("application/json")
@Consumes("application/json")
public class RecursoFactura 
{
    @Inject
    private IClienteLogic clienteLogic;
    
    @Inject
    private ISucursalLogic sucursalLogic;
    
    @Inject
    private IFacturaLogic facturaLogic;
    
    @PathParam("idSucursal")
    private Long idSucursal;
    
    private List<FacturaDetailDTO> listEntityDTO(List<FacturaEntity> entityList){
            List<FacturaDetailDTO> list = new ArrayList<>();
            for (FacturaEntity entity : entityList){
                list.add(new FacturaDetailDTO(entity));
            }
            return list;
        }
    
        public void existsFactura(Long facturaId) {
            try{
            FacturaDetailDTO res = new FacturaDetailDTO(facturaLogic.getFactura(idSucursal, facturaId));
            if (res == null) {
            throw new WebApplicationException("factura no existe", 404);
            }
            }catch(RestauranteLogicException e){
                throw new WebApplicationException("factura no existe", 404);
            }
        }
    
    /**
     * Retorna la lista de facturas.
     * @return lista de facturas.
     * @throws LogicaRestauranteException Si no existe una lista de facturas en el sistema.
     */
   
    @GET
    public List<FacturaDetailDTO> getFacturas(@PathParam("idSucursal") Long idSucursal) throws RestauranteLogicException 
    {
        List<FacturaEntity> facturas = facturaLogic.getFacturas(idSucursal);
        return listEntityDTO(facturas);
    }
   
    /**
     * Obtiene factura con el identificador buscado.
     * @param id Identificador factura buscada.
     * @return FacturaDTO factura buscada.
     * @throws LogicaRestauranteException Si no existe factura con el identificador dado.
     */
    @GET
    @Path ("{id: \\d+}")
    public FacturaDTO getFactura (@PathParam ("id")Long id) throws RestauranteLogicException 
    {
        FacturaEntity entity= facturaLogic.getFactura(idSucursal, id);
        return new FacturaDTO(entity);
    }   
    
    /**
     * Crea factura con la información enviada como parámetro.
     * @param fac La instancia factura que se quiere crear.
     * @return FacturaDTO factura creada.
     * @throws LogicaRestauranteException Si ya existe factura con ese id.
     */
   @POST
    public FacturaDTO createFactura(FacturaDTO nFactura) throws RestauranteLogicException
    {
        return new FacturaDetailDTO(facturaLogic.createFactura(idSucursal, nFactura.toEntity()));
    }
 
    /**
     * Actualiza factura con la información enviada como parámetro.
     * @param fact La instancia factura que se quiere actualizar.
     * @throws LogicaRestauranteException Si ya existe un domicilio con ese id.
     */
   
    @PUT
    @Path ("{id: \\d+}")
    public FacturaDTO uptadeFactura (FacturaDTO factura) throws RestauranteLogicException 
    {
        FacturaEntity entity = factura.toEntity();
        return new FacturaDetailDTO(facturaLogic.updateFactura(idSucursal,entity));
    }
    
     /**
     * Elimina factura con la información enviada como parámetro.
     * @param fact La instancia factura que se quiere eliminar.
     * @throws LogicaRestauranteException Si ya existe factura con ese id.
     */
    
    @DELETE
    @Path ("{id: \\d+}")
    public void deleteFactura (@PathParam ("id")Long id) throws RestauranteLogicException {
       facturaLogic.deleteFactura(idSucursal, id);
    } 
}

