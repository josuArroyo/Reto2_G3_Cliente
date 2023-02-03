/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception to warn that the password format is incorrect
 * @author Jessica y Alejandro
 */
public class PasswdFormatException extends Exception{
 
     
    public PasswdFormatException() {
        try {
            throw new Exception ("Error en el formato de la contraseña introducida. Vuelve a intentarlo. ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}