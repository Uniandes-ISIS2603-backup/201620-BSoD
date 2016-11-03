/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.FacturaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.PlatoEntity;
import co.edu.uniandes.bsod.restauranteselsabor.entities.ReservaEntity;
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
    private List<FacturaDTO> facturas = new ArrayList<>();
    private List<ReservaDTO> reservas = new ArrayList<>();

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
        List<FacturaEntity> facturasList = entity.getFacturas();
        for (FacturaEntity factura : facturasList) {
            this.facturas.add(new FacturaDTO(factura));
        }
        List<ReservaEntity> reservasList = entity.getReservas();
        for (ReservaEntity reserva : reservasList) {
            this.reservas.add(new ReservaDTO(reserva));
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
        for (MesaDTO mesa : this.mesas) {         
            entity.getMesas().add(mesa.toEntity());
        }
         List<PlatoDTO> platos = this.getPlatos();
        for (PlatoDTO plato : this.platos) {         
            entity.getPlatos().add(plato.toEntity());
        }
         List<FacturaDTO> facturas = this.getFacturas();
        for (FacturaDTO factura : this.facturas) {         
            entity.getFacturas().add(factura.toEntity());
        }
         List<ReservaDTO> reservas = this.getReservas();
        for (ReservaDTO reserva : this.reservas) {         
            entity.getReservas().add(reserva.toEntity());
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
    
    /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
}
