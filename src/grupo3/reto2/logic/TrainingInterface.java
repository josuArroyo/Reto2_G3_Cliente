/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import grupo3.reto2.exception.CreateException;
import grupo3.reto2.exception.DeleteException;
import grupo3.reto2.exception.ReadException;
import grupo3.reto2.exception.UpdateException;
import grupo3.reto2.model.Entrenamiento;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jessica
 */
public interface TrainingInterface {
    
    

    public <T> T findObjetivo_XML(GenericType<T> responseType, String idObjetivo) throws ClientErrorException;

    public <T> T findObjetivo_JSON(GenericType<T> responseType, String idObjetivo) throws ClientErrorException;
     
  

    public void edit_XML(Object requestEntity) throws ClientErrorException ;
    public void edit_JSON(Object requestEntity) throws ClientErrorException ;
    

    public <T> T findById_XML(GenericType<T> responseType, String idEntrenamiento) throws ClientErrorException ;

    public <T> T findById_JSON(Class<T> responseType, String idEntrenamiento) throws ClientErrorException ;

    public <T> T findDuracion_XML(GenericType<T> responseType, String duracion) throws ClientErrorException;

    public <T> T findDuracion_JSON(GenericType<T> responseType, String duracion) throws ClientErrorException;
    

    public void create_XML(Object requestEntity) throws ClientErrorException ;
    
    public void create_JSON(Object requestEntity) throws ClientErrorException ;
    

    public <T> T findIntensidad_XML(GenericType<T> responseType, String intensidad) throws ClientErrorException;
    
    public <T> T findIntensidad_JSON (GenericType<T> responseType, String intensidad) throws ClientErrorException;

    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;

    public <T> T findAll_JSON(GenericType<T> responseType) throws ClientErrorException;
    

    public void remove(Integer id) throws ClientErrorException;

   
}
