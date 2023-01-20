/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;


/**
 *
 * @author Jessica
 */

public class Entrenamiento implements Serializable {

    private static final long serialVersionUID = 1L;

   
    private Integer idEntrenamiento;
    
 
    private String descripcion;
    
   
    private Integer duracion;
    
    
   
    private Date fechaPeriod;
    
  
    private Integer intensidad;
    
    
    private Integer repeticion;
    
  
   
    private Objetivo objetivo;
    
   
    private Set<Admin> admin;
   
    public void getEntrenamientos() {
		
		System.out.println(this.descripcion);
		System.out.println(this.duracion);
		System.out.println(this.fechaPeriod);
		System.out.println(this.intensidad);
		System.out.println(this.repeticion);
		System.out.println(this.objetivo);	
	}


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
