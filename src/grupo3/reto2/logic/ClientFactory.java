/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

/**
 *
 * @author 2dam
 */
public class ClientFactory {
    
     public ClientInterface getFactory() {
        ClientInterface uInter;
        uInter = (ClientInterface) new ClientRESTfulClient();
        return uInter;

    }
    
}