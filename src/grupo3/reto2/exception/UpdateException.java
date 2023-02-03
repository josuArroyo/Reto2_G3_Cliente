/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Error for when training cannot be modified
 * @author Alejandro y Jessi
 */
public class UpdateException extends Exception{
    
 
    public UpdateException() {
        try {
            throw new Exception ("Error al intentar modificar. ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}