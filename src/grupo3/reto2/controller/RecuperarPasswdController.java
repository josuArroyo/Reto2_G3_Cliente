/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import static grupo3.reto2.controller.SignUpController.LOGGER;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author poker
 */
public class RecuperarPasswdController {
    
    @FXML
    private TextField txtNomb, txtEmailRecup;
    
    @FXML
    private Button btnEnviarEmail, btnCancelar;
    
    
    
    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Passwd stage.");

        Scene scene = new Scene(root);
        
        btnEnviarEmail.setDisable(false);
        btnEnviarEmail.setOnAction(this:: handleEnviarButtonAction);
        
         //El botón cerrar está habilitado y visible. 
        btnCancelar.setDisable(false);
        btnCancelar.setOnAction(this::handleCancelarButtonAction);
        
        //La ventana no es redimensionable.
        Stage stage = new Stage();
        stage.setResizable(false);

        //La ventana es una ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        //El foco inicialmente estará en el campo de nombre de usuario.
        txtNomb.requestFocus();

        //El título de la ventana es “Training”.
        stage.setTitle("Forgot Passwd");

        stage.show();
    }
 
    
    @FXML
    private void handleEnviarButtonAction(ActionEvent event) {
        
        try {
                //Introducir el nombre de usuario y verificar que existe en la base de datos
                
                //Introducir el correo electronico y verificar qque esta en la BD para enviarle un email
                
                
            
        }catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
        }
        
    }
    @FXML
    private void handleCancelarButtonAction(ActionEvent event) {
        try {


            //El botón está habilitado
            btnCancelar.setDisable(false);
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
}
