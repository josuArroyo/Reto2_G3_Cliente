/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data entered in the user field is wrong
 * @author Alejandro y Jessica
 * 
 */
public class UserNameErrorException extends Exception{
 
        private static final long serialVersionUID = 1L;
    
    public UserNameErrorException (String message){
        super(message);
           
    } 
}
