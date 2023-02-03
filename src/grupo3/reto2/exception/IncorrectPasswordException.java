/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Incorrectly introduced password
 *
 * @author Alejandro y Jessica
 */
public class IncorrectPasswordException extends Exception {

    private static final long serialVersionUID = 1L;

    public IncorrectPasswordException() {
        try {
            throw new Exception("La contraseña no es correcta. Vuelva a introducirla. ");
        } catch (Exception ex) {
            Logger.getLogger(UserAlreadyExistsException.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
