/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Ale 
 */


public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idEvento;
    
  
    private String tipoEvento;
    
    
    private Integer numPart;
    
    private String descripcion;
    
    private Date fecha;
    
    
    private String premio;
    
    private Admin admin;
    
    private Lugar lugar;

    /**
     * @associates <{uml.Cliente}>
     */
    
    private Set<Cliente> listaCliente;
    

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setNumPart(Integer numPart) {
        this.numPart = numPart;
    }

    public Integer getNumPart() {
        return numPart;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setPremio(String premio) {
        this.premio = premio;
    }

    public String getPremio() {
        return premio;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setListaCliente(Set<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Set<Cliente> getListaCliente() {
        return listaCliente;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEvento != null ? idEvento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.idEvento == null && other.idEvento != null) || (this.idEvento != null && !this.idEvento.equals(other.idEvento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evento[ id=" + idEvento + " ]";
    }
    
}