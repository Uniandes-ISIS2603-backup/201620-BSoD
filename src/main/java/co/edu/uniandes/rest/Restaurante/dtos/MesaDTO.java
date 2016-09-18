/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.Restaurante.dtos;

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
    
    private Long idSucursal;
    
    public MesaDTO()
    {   }
    
    public MesaDTO(Long pId, int pPiso, int pCantSillas, Long idSucursal)
    {
        super();
        this.id = pId;
        this.piso = pPiso;
        this.cantSillas = pCantSillas;
        this.estado = false;
        this.idSucursal = idSucursal;
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
    
    public Long getidSucursal() 
    {
        return idSucursal;
    }

    public void setidSucursal(Long id) 
    {
         this.idSucursal = id;
    }
      
    
    
}
