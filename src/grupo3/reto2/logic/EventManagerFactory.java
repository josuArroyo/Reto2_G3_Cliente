/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

import java.util.Collection;

/**
 *
 * @author Ale
 */

public class EventManagerFactory {
    public EventManager getFactory() {
        EventManager em = (EventManager) new EventRESTfulClient();       
        return em;
    }
}