/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.model;

import grupo3.reto2.model.Entrenamiento;
import java.util.Set;


/**
 *
 * @author grupo3c
 */

public class Admin extends User{

    private static final long serialVersionUID = 1L;
    
    
    
    
    private String licencia;
    

    /**
     * @associates <{uml.Evento}>
     */
    
    private Set<Evento> listaEvento;

    /**
     * @associates <{uml.Lugar}>
     */
  
    private Set<Lugar> listaLugar;

    /**
     * @associates <{uml.Entrenamiento}>
     */
    
    private Set<Entrenamiento> listaEntrenamiento;

    /**
     * @associates <{uml.Objetivo}>
     */
    
    private Set<Objetivo> listaObjetivo;


    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setListaEvento(Set<Evento> listaEvento) {
        this.listaEvento = listaEvento;
    }

    
    public Set<Evento> getListaEvento() {
        return listaEvento;
    }

    public void setListaLugar(Set<Lugar> listaLugar) {
        this.listaLugar = listaLugar;
    }

    
    public Set<Lugar> getListaLugar() {
        return listaLugar;
    }

    public void setListaEntrenamiento(Set<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    
    public Set<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }

    public void setListaObjetivo(Set<Objetivo> listaObjetivo) {
        this.listaObjetivo = listaObjetivo;
    }

    
    public Set<Objetivo> getListaObjetivo() {
        return listaObjetivo;
    }

   
    @Override
    public String toString() {
        return "entities.Admin[ id=" + licencia + " ]";
    }
    
}
