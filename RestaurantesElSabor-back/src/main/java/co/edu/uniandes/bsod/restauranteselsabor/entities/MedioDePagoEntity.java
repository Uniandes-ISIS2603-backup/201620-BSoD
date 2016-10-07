/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.bsod.restauranteselsabor.entities;

import javax.persistence.*;
/**
 *
 * @author aj.paredes10
 */
@Entity
public class MedioDePagoEntity extends BaseEntity{
    
    @ManyToOne
    ClienteEntity cliente;
}
