/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author grupo3c
 */
@Entity
@DiscriminatorValue("CL")
@XmlRootElement
public class Cliente extends User{

    private static final long serialVersionUID = 1L;

  

    private int edad;

    /**
     * @associates <{uml.ObjetivoUser}>
     */
    @OneToMany (mappedBy = "cliente")
    private Set<ObjetivoCliente> listaObjetivoCliente;

    /**
     * @associates <{uml.Evento}>
     */
    @ManyToMany(mappedBy = "listaCliente")
    private Set<Evento> listaEvento;

    @XmlTransient
    public Set<ObjetivoCliente> getListaObjetivoCliente() {
        return listaObjetivoCliente;
    }

    public void setListaObjetivoCliente(Set<ObjetivoCliente> listaObjetivoCliente) {
        this.listaObjetivoCliente = listaObjetivoCliente;
    }

   

    public void setListaEvento(Set<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    @XmlTransient
    public Set<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "entities.Cliente[ id=" + edad + " ]";
    }

}
