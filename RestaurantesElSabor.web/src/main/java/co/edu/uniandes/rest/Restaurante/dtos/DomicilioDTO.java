package co.edu.uniandes.rest.Restaurante.dtos;

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



    public DomicilioDTO(Long pId, String pDir, String pPlato, int pPrecio, Long pIdCliente) {
        super();
        this.id = pId;
        this.direccion = pDir;
        this.plato = pPlato;
        this.precio = pPrecio;
        this.idCliente = pIdCliente;
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

    public Long getidCliente() 
    {
        return idCliente;
    }

    public void setidCliente(Long id) 
    {
         this.idCliente = id;
    }
}
