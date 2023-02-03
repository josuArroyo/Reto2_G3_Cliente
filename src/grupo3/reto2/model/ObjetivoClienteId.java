/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import java.io.Serializable;
import java.util.Objects;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author DiegoF
 */
@XmlRootElement
public class ObjetivoClienteId implements Serializable{
    
    private Integer idUser;
    private Integer idObjetivo;

    @XmlElement(name="IdObjetivo")
    public Integer getIdObjetivo() {
        return idObjetivo;
    }
    
    public void setIdObjetivo(Integer idObjetivo) {
        this.idObjetivo = idObjetivo;
    }
    @XmlElement(name="idUser")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idUser);
        hash = 89 * hash + Objects.hashCode(this.idObjetivo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ObjetivoClienteId other = (ObjetivoClienteId) obj;
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }
        if (!Objects.equals(this.idObjetivo, other.idObjetivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ObjetivoUserId{" + "edad=" + idUser + ", idObjetivo=" + idObjetivo + '}';
    }

   
    
    
}

