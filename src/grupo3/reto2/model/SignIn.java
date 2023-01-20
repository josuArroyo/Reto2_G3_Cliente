/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import java.io.Serializable;
import java.util.Set;


/**
 *
 * @author Diego y Jessica
 */

public class SignIn implements Serializable {

    private static final long serialVersionUID = 1L;
  
    private Integer id;
    
    
    private String nomUser;
    
    private String passwd;
 

    //@OneToMany(mappedBy="User")
    public Set<User> listaUsers;

    
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
