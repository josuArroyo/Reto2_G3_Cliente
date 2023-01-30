/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2;


import grupo3.reto2.controller.PlaceController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * This is the Application class for the client side.
 * @author Josu y Jessica
 */
public class Aplication extends Application{
    
 /**
 * This method is used to start the app
 *@param stage is the scenary
     * @throws java.lang.Exception
 */
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Place.fxml")); 
        Parent root = (Parent)loader.load();
        PlaceController place= ((PlaceController)loader.getController());
        place.setStage(stage);
        place.initStage(root);
        
        /*FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Place.fxml")); 
        Parent root = (Parent)loader.load();
        PlaceController PlaceC= ((PlaceController)loader.getController());
        PlaceC.setStage(stage);
        PlaceC.initStage(root);*/
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
}