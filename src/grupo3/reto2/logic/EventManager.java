/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import grupo3.reto2.entities.Evento;
import grupo3.reto2.exception.BusinessLogicException;
import java.util.Collection;

/**
 *
 * @author Ale
 */
public interface EventManager {
    public Collection<Evento> getAllEvents() throws BusinessLogicException;
    public void updateEvent(Evento evento) throws BusinessLogicException;
    public void deleteEvent(Evento evento) throws BusinessLogicException;
    public void createEvent(Evento evento) throws BusinessLogicException;
    public Collection<Evento> getEventsByEventId(String idEvento) throws BusinessLogicException;
    public Collection<Evento> getEventsByEventType(String tipoEvento) throws BusinessLogicException;
}