/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import grupo3.reto2.model.Objetivo;
import grupo3.reto2.model.Cliente;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author Diego Y Jessica
 */

public class ObjetivoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    private ObjetivoClienteId objetivoClienteId;
     
    //@JoinColumn(name ="cliente",referencedColumnName="idUser") 
   
    private Cliente cliente;
     
    
    //@JoinColumn(name ="objetivo",referencedColumnName="idObjetivo")
    private Objetivo objetivo;
    
    
    
   
    private Date fechaCon;
    
    
    public void setFechaCon(Date fechaCon) {
        this.fechaCon = fechaCon;
    }

    public Date getFechaCon() {
        return fechaCon;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public ObjetivoClienteId getObjetivoUserId() {
        return objetivoClienteId;
    }

    public void setObjetivoUserId(ObjetivoClienteId objetivoUserId) {
        this.objetivoClienteId = objetivoUserId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

   
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (objetivoClienteId!= null ? objetivoClienteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoCliente)) {
            return false;
        }
        ObjetivoCliente other = (ObjetivoCliente) object;
        if ((this.objetivoClienteId == null && other.objetivoClienteId != null) || (this.objetivoClienteId != null && !this.objetivoClienteId.equals(other.objetivoClienteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ObjetivoUser[ id=" + objetivoClienteId + " ]";
    }

}
