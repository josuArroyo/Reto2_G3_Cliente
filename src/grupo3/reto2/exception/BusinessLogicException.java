/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.exception;

/**
 *
 * @author Ale
 */
public class BusinessLogicException extends Exception{
    private static final long serialVersionUID = 1L;
    
    public BusinessLogicException (String message){
        super(message);          
    }
}