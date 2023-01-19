/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;



import java.io.Serializable;
import java.util.Set;


/**
 *
 * @author 2dam
 */

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private Integer idUser;
    
    
    public Integer getId() {
        return idUser;
    }
    
    public void setId(Integer id) {
        this.idUser = id;
    }
    
        public User() {
        super();
    }
    
    private String nomUser;
    private String nombre;
    private String email;
    private String passwd;
    private String confPasswd;
    
    
    private Set<SignIn> listaSignIn;
    
    
    public void setListaSignIn(Set<SignIn> listaSignIn) {
        this.listaSignIn = listaSignIn;
    }

    
    public Set<SignIn> getListaSignIn() {
        return listaSignIn;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setConfPasswd(String confPasswd) {
        this.confPasswd = confPasswd;
    }

    public String getConfPasswd() {
        return confPasswd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + idUser + " ]";
    }
    
}
