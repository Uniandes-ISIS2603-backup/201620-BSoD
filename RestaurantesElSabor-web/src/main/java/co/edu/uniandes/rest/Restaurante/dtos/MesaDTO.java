/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

import co.edu.uniandes.bsod.restauranteselsabor.entities.MesaEntity;

/**
 *
 * @author zl.castaneda
 */
public class MesaDTO 
{
    private Long id;
    
    private int piso;
    
    private int cantSillas;
    
    private boolean estado;
    
    public MesaDTO()
    {   }
    
    public MesaDTO(MesaEntity p)
    {
        super();
        this.id = p.getId();
        this.piso = p.getPiso();
        this.cantSillas = p.getCantSillas();
        this.estado = p.isEstado();
    }

    public Long getId() 
    {
        return id;
    }

    public void setId(Long id) 
    {
        this.id = id;
    }

    public int getPiso() 
    {
        return piso;
    }

    public void setPiso(int piso) 
    {
        this.piso = piso;
    }

    public int getCantSillas() 
    {
        return cantSillas;
    }

    public void setCantSillas(int cantSillas) 
    {
        this.cantSillas = cantSillas;
    }

    public boolean isEstado() 
    {
        return estado;
    }

    public void setEstado(boolean estado) 
    {
        this.estado = estado;
    }
        
    /**
     * Representacion JSON de un cliente
     * @return La representacion JSON de un DTO cliente.
     */
    @Override
    public String toString() 
    {
    	return "{ id : " + id +", piso : \""+ piso +"\", cantSillas : \""+cantSillas+"\", estado : \""+estado+"\" }" ;  
    }

    
    /**
     * Convierte un objeto MesaDTO a MesaEntity.
     *
     * @return Nueva objeto MesaEntity.
     * 
     */
    public MesaEntity toEntity() {
        MesaEntity entity = new MesaEntity();
        entity.setCantSillas(cantSillas);
        entity.setEstado(estado);
        entity.setPiso(piso);
        entity.setId(this.getId());
        return entity;
    }

      
    
    
}
