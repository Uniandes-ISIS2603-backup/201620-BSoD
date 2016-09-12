/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

/**
 *
 * @author jdguz
 */
public class MesaDTO 
{
    private int id;
    
    private int piso;
    
    private int sillas;
    
    private boolean estado;

    public MesaDTO()
    {   }
    
    public MesaDTO(int pId, int pPiso, int pSillas, boolean pEstado)
    {
        super();
        this.id = pId;
        this.piso = pPiso;
        this.sillas = pSillas;
        this.estado = pEstado;
    }
    
    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
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

    public int getSillas() 
    {
        return sillas;
    }

    public void setSillas(int sillas) 
    {
        this.sillas = sillas;
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
     * Representacion JSON de una silla
     * @return La representacion JSON de un DTO silla.
     */
    @Override
    public String toString() 
    {
    	return "{  id : " + id +", piso : " + piso +", sillas : \""+ sillas + ", estado : \""+estado+"\" }" ;  
    }
    
}
