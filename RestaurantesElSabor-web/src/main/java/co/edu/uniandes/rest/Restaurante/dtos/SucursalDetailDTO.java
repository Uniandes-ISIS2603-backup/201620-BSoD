/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.SucursalEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author af.pinzon10
 */
public class SucursalDetailDTO extends SucursalDTO {
     
    private List<MesaDTO> mesas = new ArrayList<>();
    private List<PlatoDTO> platos = new ArrayList<>();

    public SucursalDetailDTO() {
        super();
    }
    

    /**
     * Crea un objeto SucursalDetailDTO a partir de un objeto SucursalEntity
     * incluyendo los atributos de SucursalDTO.
     *
     * @param entity Entidad SucursalEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public SucursalDetailDTO(SucursalEntity entity) {
        super(entity);
        List<MesaEntity> mesasList = entity.getMesas();
        for (MesaEntity mesa : mesasList) {
            this.mesas.add(new MesaDTO(mesa));
        }
        List<PlatoEntity> platosList = entity.getPlatos();
        for (PlatoEntity plato : platosList) {
            this.platos.add(new PlatoDTO(plato));
        }
    }

    /**
     * Convierte un objeto SucursalDetailDTO a SucursalEntity incluyendo los
     * atributos de SucursalDTO.
     *
     * @return objeto SucursalEntity.
     *
     */
    @Override
    public SucursalEntity toEntity() {
        SucursalEntity entity = super.toEntity();
         List<MesaDTO> mesas = this.getMesas();
        for (MesaDTO dept : this.mesas) {         
            entity.getMesas().add(dept.toEntity());
        }
         List<PlatoDTO> platos = this.getPlatos();
        for (PlatoDTO plato : this.platos) {         
            entity.getPlatos().add(plato.toEntity());
        }
        return entity;
    }

    /**
     * @return the mesas
     */
    public List<MesaDTO> getMesas() {
        return mesas;
    }

    /**
     * @param mesas the mesas to set
     */
    public void setMesas(List<MesaDTO> mesas) {
        this.mesas = mesas;
    }

    /**
     * @return the platos
     */
    public List<PlatoDTO> getPlatos() {
        return platos;
    }

    /**
     * @param platos the platos to set
     */
    public void setPlatos(List<PlatoDTO> platos) {
        this.platos = platos;
    }
}
