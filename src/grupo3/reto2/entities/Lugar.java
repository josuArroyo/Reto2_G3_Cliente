/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author josuA
 */
@Entity
@Table(name = "Lugar", schema = "Fuerza_G3")

@NamedQueries({
    @NamedQuery(name = "findAllLocations",
            query = "SELECT l FROM Lugar l ORDER BY l.idLugar DESC"
    )
    ,
    @NamedQuery(
            name = "findAllLocationsByType",
            query = "SELECT l FROM Lugar l WHERE l.tipoLugar = :tipoLugar"
    )
/*
    @NamedQuery(
            name="insertNewLocation", query="INSERT INTO lugar (descripcion, nombre, tiempo, tipoLugar) VALUES(?,?,?.?) "
    ),
    @NamedQuery(
            name="DelateLocation", query="DELETE lugar WHERE idLugar = ? "
    ),
    @NamedQuery(
            name="ModifyLocation", query="UPDATE lugar SET descripcion = ?, nombre = ?, tiempo = ?, tipoLugar = ? "
    )
 */
})

//comienzo de la clase
@XmlRootElement
public class Lugar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idLugar;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private String tipoLugar;

    @Temporal(TemporalType.TIMESTAMP)

    private Date tiempo;

    /**
     * @associates <{uml.Evento}>
     */
    @OneToMany(mappedBy = "lugar")
    private Set<Evento> listaEvento;

    @ManyToOne
    private Admin admin;

    public void setListaEvento(Set<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    @XmlTransient
    public Set<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setIdLugar(Integer idLugar) {
        this.idLugar = idLugar;
    }

    public Integer getIdLugar() {
        return idLugar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setTipoLugar(String tipoLugar) {
        this.tipoLugar = tipoLugar;
    }

    public String getTipoLugar() {
        return tipoLugar;
    }

    public void setTiempo(Date tiempo) {
        this.tiempo = tiempo;
    }

    public Date getTiempo() {
        return tiempo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLugar != null ? idLugar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lugar)) {
            return false;
        }
        Lugar other = (Lugar) object;
        if ((this.idLugar == null && other.idLugar != null) || (this.idLugar != null && !this.idLugar.equals(other.idLugar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Lugar[ id=" + idLugar + " ]";
    }

}

