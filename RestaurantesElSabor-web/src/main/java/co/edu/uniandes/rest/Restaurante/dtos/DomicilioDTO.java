package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.DomicilioEntity;

/**
 *
 * @author cc.novoa11
 */
public class DomicilioDTO {

    /**
     * Atributos
     */
    private Long id;

    private String direccion;

    private String plato;

    private int precio;
    
    private Long idCliente;

    /**
     * Constructor por defecto
     */
    public DomicilioDTO() {
    }



    public DomicilioDTO(DomicilioEntity entity) {
        if (entity != null){
        this.id = entity.getId();
        this.direccion = entity.getDireccion();
        this.plato = entity.getPlato();
        this.precio = entity.getPrecio();
        }
    }
    
    public DomicilioEntity toEntity(){
       DomicilioEntity entity = new DomicilioEntity();
       entity.setId(this.getId());
       entity.setPrecio(this.getPrecio());
       entity.setDireccion(this.getDireccion());
       entity.setPlato(this.getPlato());
       return entity;
    }

    /**
     * Métodos
     */
    /**
     * Obtener el id del domicilio.
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Asignar un nuevo id.
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtener la dirección del domicilio.
     *
     * @return direccion
     */

    public String getDireccion() {
        return direccion;
    }

    /**
     * Asignar una nueva direccion.
     *
     * @param direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtener el plato del domicilio.
     *
     * @return plato
     */
    public String getPlato() {
        return plato;
    }

    /**
     * Asignar un nuevo plato.
     *
     * @param nPlato
     */
    public void setPlato(String nPlato) {
        this.plato = nPlato;
    }

    /**
     * Obtener el precio del domicilio.
     *
     * @return precio
     */

    public int getPrecio() {
        return precio;
    }

    /**
     * Asignar un nuevo precio.
     *
     * @param nPrecio
     */
    public void setPrecio(int nPrecio) {
        this.precio = nPrecio;
    }

    /**
     * Representacion JSON de un domicilio
     *
     * @return La representacion JSON de un DTO domicilio.
     */
    @Override
    public String toString() {
        return "{ id : " + id + ", idCliente : \"" + idCliente +", direccion : \"" + direccion + "\", plato : \"" + plato + "\", "
                + "precio : \"" + precio + "\" }";
    }
}
