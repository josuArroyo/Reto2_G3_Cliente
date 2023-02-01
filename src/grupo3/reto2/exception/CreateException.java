/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Error for when training cannot be created
 * @author Jessica y Alejandro
 */
public class CreateException extends Exception{
 
     /**
     * Creates a new instance of <code>CreateException</code> without detail
     * message.
     */
   
        public CreateException() {
        try {
            throw new Exception ("Error al intentar crear. ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExitsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
