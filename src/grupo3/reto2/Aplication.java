/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2;

import grupo3.reto2.controller.PlaceController;
import grupo3.reto2.controller.SignInController;
import grupo3.reto2.controller.SignUpController;
import java.util.logging.Logger;
import grupo3.reto2.controller.PrincipalController;
import grupo3.reto2.controller.TrainingController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


/**
 *
 * @author 2dam
 */
public class Aplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(Aplication.class.getName());

    @Override
    public void start(Stage stage) throws Exception {


//        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Principal.fxml")); 
//        Parent root = (Parent)loader.load();
//        PrincipalController principal= ((PrincipalController)loader.getController());
//        principal  .setStage(stage);
//        principal.initStage(root);
        
        FXMLLoader loader= new FXMLLoader(getClass().getResource("view/Training.fxml")); 
        Parent root = (Parent)loader.load();
        TrainingController trainCont= ((TrainingController)loader.getController());
        trainCont.setStage(stage);
        trainCont.initStage(root);
        
//      Parent root = FXMLLoader.load(getClass().getResource("Training.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();

//        LOGGER.info("Carga del FXML de SignIn");
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignIn.fxml"));
//        Parent root = (Parent) loader.load();
//        LOGGER.info("Llamada al controlador del FXML");
//        SignInController controller = ((SignInController) loader.getController());
//        controller.setStage(stage);
//        controller.initStage(root);


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
