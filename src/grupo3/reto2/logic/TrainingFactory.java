/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.logic;

/**
 *
 * @author Jessica
 */
public class TrainingFactory {
    
   public TrainingInterface getFactory() {
       TrainingInterface tInter;
       tInter = new TrainingRESTfulClient();
       return tInter;
   } 
   
  

}