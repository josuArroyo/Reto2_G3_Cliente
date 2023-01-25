/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2;

import grupo3.reto2.controller.ObjectiveController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


/**
 *
 * @author Diego
 */
public class Aplication extends Application {
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Objective.fxml")); 
            Parent root = (Parent)loader.load();
            ObjectiveController objeti = (ObjectiveController)loader.getController();
            objeti.setStage(stage);
            objeti.initStage(root);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
