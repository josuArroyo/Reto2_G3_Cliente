/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import grupo3.reto2.exception.CreateException;
import grupo3.reto2.exception.DeleteException;
import grupo3.reto2.exception.ReadException;
import java.util.ResourceBundle;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 * Jersey REST client generated for REST resource:EventoFacadeREST
 * [entities.evento]<br>
 * USAGE:
 * <pre>
 *        EventRESTfukClient2 client = new EventRESTfukClient2();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Ale
 */
public class EventRESTfulClient implements EventManager{

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = ResourceBundle.getBundle("grupo3.reto2.model.Config").getString("baseURI");
    
    public EventRESTfulClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("entities.evento");
    }

    @Override
    public <T> T findEventByEventId_XML(GenericType<T> responseType, String idEvento) throws ClientErrorException, ReadException{
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("FindEventBy/{0}", new Object[]{idEvento}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public <T> T findEventByEventId_JSON(GenericType<T> responseType, String idEvento) throws ClientErrorException, ReadException{
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("FindEventBy/{0}", new Object[]{idEvento}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    @Override
    public void modifyEvent_XML(Object requestEntity) throws ClientErrorException, ReadException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    @Override
    public void modifyEvent_JSON(Object requestEntity) throws ClientErrorException, ReadException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public void createEvent_XML(Object requestEntity) throws ClientErrorException, CreateException, ReadException{
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    @Override
    public void createEvent_JSON(Object requestEntity) throws ClientErrorException, CreateException, ReadException{
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
    }

    @Override
    public <T> T viewEvents_XML(GenericType<T> responseType) throws ClientErrorException, ReadException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public <T> T viewEvents_JSON(GenericType<T> responseType) throws ClientErrorException, ReadException{
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    @Override
    public <T> T findEventByType_XML(GenericType<T> responseType, String tipoEvento) throws ClientErrorException, ReadException{
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("FindEventBy/String/{0}", new Object[]{tipoEvento}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(responseType);
    }

    @Override
    public <T> T findEventByType_JSON(GenericType<T> responseType, String tipoEvento) throws ClientErrorException, ReadException{
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("FindEventBy/String/{0}", new Object[]{tipoEvento}));
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
    }

    @Override
    public void deleteEvent(String idEvento) throws ClientErrorException, DeleteException, ReadException{
        webTarget.path(java.text.MessageFormat.format("DELETE-Evento/{0}", new Object[]{idEvento})).request().delete();
    }

    public void close() {
        client.close();
    }
    
}
