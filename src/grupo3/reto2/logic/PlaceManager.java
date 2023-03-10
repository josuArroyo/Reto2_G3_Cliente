/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author 2dam
 */
public interface PlaceManager {

    public void edit_XML(Object requestEntity) throws ClientErrorException;

    public void edit_JSON(Object requestEntity) throws ClientErrorException;

    public <T> T find_XML(Class<T> responseType, String id) throws ClientErrorException;

    public <T> T find_JSON(Class<T> responseType, String id) throws ClientErrorException;

    public void create_XML(Object requestEntity) throws ClientErrorException;

    public void create_JSON(Object requestEntity) throws ClientErrorException;

    public <T> T findAll_XML(GenericType<T> responseType) throws ClientErrorException;

    public <T> T findAll_JSON(Class<T> responseType) throws ClientErrorException;

    public void remove(String id) throws ClientErrorException;

    public <T> T findByType_XML(GenericType<T> responseType, String tipoLugar) throws ClientErrorException;

    public <T> T findByType_JSON(Class<T> responseType, String tipoLugar) throws ClientErrorException;

}

