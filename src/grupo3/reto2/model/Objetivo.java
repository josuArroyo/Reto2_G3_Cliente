/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import grupo3.reto2.model.Admin;
import grupo3.reto2.model.Entrenamiento;
import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Diego
 */

@XmlRootElement
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer idObjetivo;

    private String descripcion;

    private String valorParam;

    private String descriParam;

    
    private Admin admin;

    /**
     * @associates <{uml.ObjetivoUser}>
     */
//Relaciones
    private Set<ObjetivoCliente> listaClientes;

    /**
     * @associates <{uml.Entrenamiento}>
     */
    private Set<Entrenamiento> listaEntrenamiento;

    public void setListaEntrenamiento(Set<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    
    public Set<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }

    
    public Set<ObjetivoCliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Set<ObjetivoCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    @XmlElement(name="admin")
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }
    @XmlElement(name="idObjetivo")
    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    @XmlElement(name="Descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setValorParam(String valorParam) {
        this.valorParam = valorParam;
    }
    @XmlElement(name="ValorParam")
    public String getValorParam() {
        return valorParam;
    }

    public void setDescriParam(String descriParam) {
        this.descriParam = descriParam;
    }
    @XmlElement(name="DescriParam")
    public String getDescriParam() {
        return descriParam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObjetivo != null ? idObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objetivo)) {
            return false;
        }
        Objetivo other = (Objetivo) object;
        if ((this.idObjetivo == null && other.idObjetivo != null) || (this.idObjetivo != null && !this.idObjetivo.equals(other.idObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Objetivo[ id=" + idObjetivo + " ]";
    }

}
