/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2;

import grupo3.reto2.controller.PlaceController;
import grupo3.reto2.controller.SignInController;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class Aplication extends Application {

    private static final Logger LOGGER = Logger.getLogger(Aplication.class.getName());

    @Override
    public void start(Stage stage) throws Exception {

        LOGGER.info("Carga del FXML de SignIn");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/SignIn.fxml"));
        Parent root = (Parent) loader.load();
        LOGGER.info("Llamada al controlador del FXML");
        SignInController controller = ((SignInController) loader.getController());
        controller.setStage(stage);
        controller.initStage(root);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
