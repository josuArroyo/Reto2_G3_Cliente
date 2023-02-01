/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import static grupo3.reto2.controller.GenericController.LOGGER;
import grupo3.reto2.entities.User;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class PrincipalController {

    @FXML
    private Stage stage;

    @FXML
    private Label label;
    String nombre = "Pablo";
    @FXML
    private Label lblTexto;
    @FXML
    private Button btnExit;
    @FXML
    private ImageView image;

    private User user;

    @FXML
    public void initStage(Parent root) {

        LOGGER.info("Initializing Principal stage.");

        Scene scene = new Scene(root);
        //El campo de texto está deshabilitado
        lblTexto.setDisable(true);
        //El botón está habilitado
        btnExit.setDisable(false);
        lblTexto.setVisible(true);
        btnExit.setVisible(true);

        Stage stage = new Stage();
        stage.setResizable(false);

        stage.setScene(scene);
        stage.setTitle("PRINCIPAL");

        stage.show();
        lblTexto.setText(user.getLogin() + " " + lblTexto.getText());

    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        try {

            //El campo de texto está deshabilitado
            lblTexto.setDisable(true);

            //El botón está habilitado
            btnExit.setDisable(false);
            //Con esto vamos a crear una ventana de confirmación al pulsar el botón de salir
            Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("¿Deseas Salir?");
            //Con este Optional<ButtonType> creamos botones de Ok y cancelar
            Optional<ButtonType> action = ventanita.showAndWait();
            //Si le da a OK el programa cesará de existir, se cierra por completo
            if (action.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                //Si le da a cancelar la ventana emergente se cerrará pero la ventana principal se mantiene
                ventanita.close();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    public void getUser(User user) {
        this.user = user;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
