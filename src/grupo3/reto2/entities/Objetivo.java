/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import java.io.Serializable;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego
 */
@Entity
@Table(name = "Objetivo", schema = "Fuerza_G3")

@NamedQueries({
    @NamedQuery(name = "viewObjective", query = "SELECT o FROM Objetivo o ORDER BY o.idObjetivo")
    ,
    @NamedQuery(name = "filterObjectiveByValue",
            query = "SELECT o FROM Objetivo o WHERE o.valorParam = :valorParam")
//@NamedQuery(name="createObjective", query="INSERT INTO Objetivo VALUES(?,?,?,?)"),
//@NamedQuery(name="modifyObjective", query="UPDATE Objetivo SET descripcion = ?, valorParam = ?, descriParam = ? LIKE idObjetivo = ?"),
//@NamedQuery(name="deleteObjective", query="DELETE Objetivo WHERE idObjetivo = ?")
})
//funcionaporfi
@XmlRootElement
public class Objetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idObjetivo;

    private String descripcion;

    private String valorParam;

    private String descriParam;

    @ManyToOne
    private Admin admin;

    /**
     * @associates <{uml.ObjetivoUser}>
     */
//Relaciones
    @OneToMany(cascade = ALL, mappedBy = "objetivo")
    private Set<ObjetivoCliente> listaClientes;

    /**
     * @associates <{uml.Entrenamiento}>
     */
    @OneToMany(cascade = ALL, mappedBy = "objetivo")
    private Set<Entrenamiento> listaEntrenamiento;

    public void setListaEntrenamiento(Set<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    @XmlTransient
    public Set<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }

    @XmlTransient
    public Set<ObjetivoCliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(Set<ObjetivoCliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }

    public Integer getIdObjetivo() {
        return idObjetivo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setValorParam(String valorParam) {
        this.valorParam = valorParam;
    }

    public String getValorParam() {
        return valorParam;
    }

    public void setDescriParam(String descriParam) {
        this.descriParam = descriParam;
    }

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