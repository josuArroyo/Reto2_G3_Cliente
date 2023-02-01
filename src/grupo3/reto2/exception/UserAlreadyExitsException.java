/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception is used to show a warning if the user registered already exists
 * @author Alejandro
 */
public class UserAlreadyExitsException extends Exception{
    public UserAlreadyExitsException() {
        try {
            throw new Exception ("El usuario ya existe ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExitsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
