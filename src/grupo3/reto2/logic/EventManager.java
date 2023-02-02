/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author 2dam
 */
public interface EventManager {

   public <T> T findEventByEventId_XML(GenericType<T> responseType, String idEvento) throws WebApplicationException;
    public <T> T findEventByEventId_JSON(GenericType<T> responseType, String idEvento) throws WebApplicationException;
    public void modifyEvent_XML(Object requestEntity) throws WebApplicationException;
    public void modifyEvent_JSON(Object requestEntity) throws WebApplicationException;
    public void createEvent_XML(Object requestEntity) throws WebApplicationException;
    public void createEvent_JSON(Object requestEntity) throws WebApplicationException;
    public <T> T viewEvents_XML(GenericType<T> responseType) throws WebApplicationException;
    public <T> T viewEvents_JSON(GenericType<T> responseType) throws WebApplicationException;
    public <T> T findEventByType_XML(GenericType<T> responseType, String tipoEvento) throws WebApplicationException;
    public <T> T findEventByType_JSON(GenericType<T> responseType, String tipoEvento) throws WebApplicationException;
    public void deleteEvent(String idEvento) throws WebApplicationException;

}

