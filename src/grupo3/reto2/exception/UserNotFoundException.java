/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *This exception is used to show a warning if the user signed in is not found
 * @author Alejandro
 */
public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        try {
            throw new Exception ("No se ha encontrado al usuario");
        } catch (Exception ex) {
            Logger.getLogger(UserNotFoundException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
