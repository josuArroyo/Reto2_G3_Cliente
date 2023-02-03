/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import grupo3.reto2.exception.CreateException;
import grupo3.reto2.exception.DeleteException;
import grupo3.reto2.exception.ReadException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author 2dam
 */
public interface EventManager {
    public <T> T findEventByEventId_XML(GenericType<T> responseType, String idEvento) throws ClientErrorException, ReadException;
    public <T> T findEventByEventId_JSON(GenericType<T> responseType, String idEvento) throws ClientErrorException, ReadException;
    public void modifyEvent_XML(Object requestEntity) throws ClientErrorException, ReadException;
    public void modifyEvent_JSON(Object requestEntity) throws ClientErrorException, ReadException;
    public void createEvent_XML(Object requestEntity) throws ClientErrorException, CreateException, ReadException;
    public void createEvent_JSON(Object requestEntity) throws ClientErrorException, CreateException, ReadException;
    public <T> T viewEvents_XML(GenericType<T> responseType) throws ClientErrorException, ReadException;
    public <T> T viewEvents_JSON(GenericType<T> responseType) throws ClientErrorException, ReadException;
    public <T> T findEventByType_XML(GenericType<T> responseType, String tipoEvento) throws ClientErrorException, ReadException;
    public <T> T findEventByType_JSON(GenericType<T> responseType, String tipoEvento) throws ClientErrorException, ReadException;
    public void deleteEvent(String idEvento) throws ClientErrorException, DeleteException, ReadException;
}
