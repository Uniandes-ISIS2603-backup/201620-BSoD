/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author af.pinzon10
 */
@XmlRootElement
public class SucursalDTO {

    private Long id;

    private String ciudad;

    private String direccion;

    private List<String> calificacion;

    public SucursalDTO() {
    }

    public SucursalDTO(SucursalEntity p) {
        
        super();
        this.id = p.getId();
        this.ciudad = p.getCiudad();
        this.direccion = p.getDireccion();
        this.calificacion = p.getCalificaciones();
    }
    
    public SucursalEntity toEntity() {
        SucursalEntity entity = new SucursalEntity();
        entity.setCiudad(this.getCiudad());
        entity.setDireccion(this.getDireccion());
        entity.setCalificaciones(this.getCalificacion());
        entity.setId(this.getId());
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }




    public List<String> getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(List<String> calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Representacion JSON de un cliente
     *
     * @return La representacion JSON de un DTO Sucursal.
     */
    @Override
    public String toString() {
        return "{ id : " + id + ", ciudad : \"" + ciudad + "\", direccion : \"" + direccion + "\", mesas : \"" + "\", calificacion : \"" + calificacion + "\" }";
    }

}
