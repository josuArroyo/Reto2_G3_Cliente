/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import java.io.Serializable;
import java.util.Set;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author 2dam
 */

@XmlRootElement
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    
    private Integer idUser;
   
    private String login;
    private String nombre;
    private String email;
    private String passwd;
    private String confPasswd;
    
    
    private UserPrivilege userPrivilege;
    
    @XmlElement(name="Id")
    public Integer getId() {
        return idUser;
    }
    
    public void setId(Integer id) {
        this.idUser = id;
    }
    
        public User() {
        super();
    }
    
    
        @XmlElement(name="Login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    @XmlElement(name="Privilege")
    public UserPrivilege getPrivilege() {
        return userPrivilege;
    }

    public void setPrivilege(UserPrivilege privilege) {
        this.userPrivilege = privilege;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @XmlElement(name="Nombre")
    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement(name="Email")
    public String getEmail() {
        return email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    @XmlElement(name="Passwd")
    public String getPasswd() {
        return passwd;
    }

    public void setConfPasswd(String confPasswd) {
        this.confPasswd = confPasswd;
    }
    @XmlElement(name="ConfPasswd")
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
