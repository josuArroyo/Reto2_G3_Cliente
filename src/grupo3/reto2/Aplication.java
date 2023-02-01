/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2;

import grupo3.reto2.controller.EventController;
import grupo3.reto2.controller.PrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;



/*
 * @author 2dam
 */
public class Aplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Ventana_evento.fxml")); 
        Parent root = (Parent)loader.load();
        EventController ec = ((EventController)loader.getController());
        ec.setStage(stage);
        ec.initStage(root);

//        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Training.fxml")); 
//        Parent root = (Parent)loader.load();
//        TrainingController trainCont= ((TrainingController)loader.getController());
//        trainCont.setStage(stage);
//        trainCont.initStage(root);

//      Parent root = FXMLLoader.load(getClass().getResource("Training.fxml"));
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();
    }

     /* @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}