/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Diego y Jessica
 */
@Entity
@Table(name="SignIn", schema="Fuerza_G3")
@XmlRootElement
public class SignIn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    
    private String nomUser;
    
    private String passwd;
 

    //@OneToMany(mappedBy="User")
    @ManyToMany //(mappedBy = "listaSignIn")
    public Set<User> listaUsers;

    @XmlTransient
    public Set<User> getListaUsers() {
        return listaUsers;
    }

    public void setListaUsers(Set<User> listaUsers) {
        this.listaUsers = listaUsers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SignIn)) {
            return false;
        }
        SignIn other = (SignIn) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SignIn[ id=" + id + " ]";
    }
    
}
