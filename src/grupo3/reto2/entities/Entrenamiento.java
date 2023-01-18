/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.omg.CORBA.PERSIST_STORE;

/**
 *
 * @author Jessica
 */
@Entity
@Table(name = "Entrenamiento", schema = "Fuerza_G3")

@NamedQueries({
    @NamedQuery(
            name = "viewAllTraining", query = "SELECT E FROM Entrenamiento E"
    )
    ,
     @NamedQuery(
            name = "viewByDuration", query = "SELECT E FROM Entrenamiento E where E.duracion=:duracion"
    )
    ,
      @NamedQuery(
            name = "viewByIntensity", query = "SELECT E FROM Entrenamiento E where E.intensidad=:intensidad"
    )
    ,
//      @NamedQuery(
//            name="insertNewTraining", query="INSERT INTO entrenamiento (descripcion, duracion, fechaPeriod, intensidad, repeticion) VALUES(?,?,?,?,?,?,"
//      ),
       @NamedQuery(
            name = "viewByObjective", query = "SELECT E FROM Entrenamiento E where E.objetivo=:objetivo"
    ), //        @NamedQuery(
//            name="deleteTraining", query="DELETE ENTRENAMIENTO WHERE idEntrenamiento = ?"
//      ),
//        @NamedQuery(
//            name="modifyTraining", query="UPDATE ENTRENAMIENTO SET descripcion = ?, duracion = ?, fechaPeriod= ?, intensidad = ?, repeticion = ? LIKE idEntrenamiento = ?"
//      ),
})

@XmlRootElement
public class Entrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEntrenamiento;

    private String descripcion;

    private Integer duracion;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(as = Date.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")

    private Date fechaPeriod;

    private Integer intensidad;

    private Integer repeticion;

    @ManyToOne(cascade = MERGE)
    private Objetivo objetivo;

    @ManyToMany()
    @JoinTable(name = "adminEntrenamiento", schema = "Fuerza_G3")
    private Set<Admin> admin;

    public void setIdEntrenamiento(Integer idEntrenamiento) {
        this.idEntrenamiento = idEntrenamiento;
    }

    public Integer getIdEntrenamiento() {
        return idEntrenamiento;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    @XmlTransient
    public Set<Admin> getAdmin() {
        return admin;
    }

    public void setAdmin(Set<Admin> admin) {
        this.admin = admin;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setFechaPeriod(Date fechaPeriod) {
        this.fechaPeriod = fechaPeriod;
    }

    public Date getFechaPeriod() {
        return fechaPeriod;
    }

    public void setIntensidad(Integer intensidad) {
        this.intensidad = intensidad;
    }

    public Integer getIntensidad() {
        return intensidad;
    }

    public void setRepeticion(Integer repeticion) {
        this.repeticion = repeticion;
    }

    public Integer getRepeticion() {
        return repeticion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEntrenamiento != null ? idEntrenamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entrenamiento)) {
            return false;
        }
        Entrenamiento other = (Entrenamiento) object;
        if ((this.idEntrenamiento == null && other.idEntrenamiento != null) || (this.idEntrenamiento != null && !this.idEntrenamiento.equals(other.idEntrenamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Entrenamiento[ id=" + idEntrenamiento + " ]";
    }

}
