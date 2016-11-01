/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author af.pinzon10
 */
@XmlRootElement
public class MesaDetailDTO extends MesaDTO{
    
    @PodamExclude
    private SucursalDTO sucursal;

    /**
     *
     */
    public MesaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto MesaDetailDTO a partir de un objeto MesaEntity
     * incluyendo los atributos de MesaDTO.
     *
     * @param entity Entidad MesaEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public MesaDetailDTO(MesaEntity entity) {
        super(entity);
        if (entity.getSucursal() != null) {
            this.sucursal = new SucursalDTO(entity.getSucursal());
        }

    }

    /**
     * Convierte un objeto MesaDetailDTO a MesaEntity incluyendo los
     * atributos de MesaDTO.
     *
     * @return objeto MesaEntity.
     *
     */
    @Override
    public MesaEntity toEntity() {
        MesaEntity entity = super.toEntity();
        if (this.getSucursal() != null) {
            entity.setSucursal(this.getSucursal().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo sucursal.
     *
     * @return atributo sucursal.
     *
     */
    public SucursalDTO getSucursal() {
        return sucursal;
    }

    /**
     * Establece el valor del atributo sucursal.
     *
     * @param sucursal nuevo valor del atributo
     *
     */
    public void setSucursal(SucursalDTO sucursal) {
        this.sucursal = sucursal;
    }

}
