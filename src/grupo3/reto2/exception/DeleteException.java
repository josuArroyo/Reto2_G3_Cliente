/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Error for when training cannot be deleted
 * @author Ale y jessi
 */
public class DeleteException extends Exception{
    
     /**
     * Creates a new instance of <code>DeleteException</code> without detail
     * message.
     */
    public DeleteException() {
        try {
            throw new Exception ("Error al intentar borrar. ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExitsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
