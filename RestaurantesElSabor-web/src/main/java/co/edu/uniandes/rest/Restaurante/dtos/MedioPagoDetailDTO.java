package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MedioPagoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author aj.paredes10
 */
@XmlRootElement
public class MedioPagoDetailDTO extends MedioPagoDTO{
    @PodamExclude
    private ClienteDTO cliente;
    
    public MedioPagoDetailDTO(){
        super();
    }
    
    public MedioPagoDetailDTO(MedioPagoEntity entity) {
        super(entity);
        if (entity.getCliente() != null) {
            this.cliente = new ClienteDTO(entity.getCliente());
        }

    }
    
    @Override
    public MedioPagoEntity toEntity() {
        MedioPagoEntity entity = super.toEntity();
        if (this.getCliente() != null) {
            entity.setCliente(this.getCliente().toEntity());
        }
        return entity;
    }
    
    public ClienteDTO getCliente(){
        return cliente;
    }
    
    public void setCliente(ClienteDTO cliente){
        this.cliente = cliente;
    }
}
