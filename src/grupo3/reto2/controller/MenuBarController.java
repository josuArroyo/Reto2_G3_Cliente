/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class MenuBarController {
    
    private Stage stage = new Stage();
    
    @FXML
    private HBox hBoxMenu;
    
    @FXML
    private Menu menuHome;
    
    @FXML
    private Menu menuNavegar;
    
    @FXML
    private Menu menuAyuda;
    
     @FXML
    private Menu menuCR;
    
    @FXML
    private MenuItem miVolver;
    
    @FXML
    private MenuItem miEvento;
    
    @FXML
    private MenuItem miEntrenamiento;
    
    @FXML
    private MenuItem miLugar;
    
    @FXML
    private MenuItem miObjetivo;
    
    @FXML
    private MenuItem miDescripcionVentana;
    
    @FXML
    private MenuItem miManualUsuario;
    
    @FXML
    private MenuItem miSobreNosotros;
    
    @FXML
    private MenuItem miCerrarSesion;
    
    
    public void initialize(URL url, ResourceBundle rb) {
        //Habilitación del menu
        menuHome.setDisable(false);
        menuNavegar.setDisable(false);
        menuCR.setDisable(false);
        menuAyuda.setDisable(false);
        miCerrarSesion.setDisable(false);
        miDescripcionVentana.setDisable(false);
        miEntrenamiento.setDisable(false);
        miEvento.setDisable(false);
        miLugar.setDisable(false);
        miManualUsuario.setDisable(false);
        miObjetivo.setDisable(false);
        miSobreNosotros.setDisable(false);
        miVolver.setDisable(false);
        
        //Meotdos de los menús y menúbars
        miObjetivo.setOnAction(this::miObjetivo);
        menuHome.setOnAction(this::menuHome);
        miLugar.setOnAction(this::miLugar);
        miEvento.setOnAction(this::miEvento);
        miEntrenamiento.setOnAction(this::miEntrenamiento);
        menuAyuda.setOnAction(this::menuAyuda);
        menuCR.setOnAction(this::menuCR);
        miDescripcionVentana.setOnAction(this::miDescripcionVentana);
        miManualUsuario.setOnAction(this::miManualUsuario);
        miSobreNosotros.setOnAction(this::miSobreNosotros);
        
    }    
    @FXML
    private void menuHome(ActionEvent event){
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Principal.fxml"));

            Parent root = (Parent) loader.load();
            
            PrincipalController controller = ((PrincipalController) loader.getController());

            //controller.setStage(stage);
            //controller.initStage(root);

            hBoxMenu.getScene().getWindow().hide();
        } catch (IOException e) {
            
        }
    }
    
    
    @FXML
    private void miLugar(ActionEvent event){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Place.fxml"));

            Parent root = (Parent) loader.load();
            
            /*PlaceController controller = ((PlaceController) loader.getController());

            controller.setStage(stage);
            controller.initStage(root);*/

            hBoxMenu.getScene().getWindow().hide();
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miEvento(ActionEvent event){
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Evento.fxml"));

            Parent root = (Parent) loader.load();
            
            /*EventController controller = ((EventController) loader.getController());

            controller.setStage(stage);
            controller.initStage(root);*/

            hBoxMenu.getScene().getWindow().hide();
        } catch (IOException e) {
            
        }
    
    }
    @FXML
    private void miEntrenamiento(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Training.fxml"));

            Parent root = (Parent) loader.load();
            
            /*TrainingController controller = ((TrainingController) loader.getController());

            controller.setStage(stage);
            controller.initStage(root);*/

            hBoxMenu.getScene().getWindow().hide();
        } catch (IOException e) {
            
        }
    }
    @FXML
    private void miObjetivo(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Objective.fxml"));

            Parent root = (Parent) loader.load();
            
            ObjectiveController controller = ((ObjectiveController) loader.getController());

            controller.setStage(stage);
            controller.initStage(root);

            hBoxMenu.getScene().getWindow().hide();
        } catch (IOException e) {
            
        }
    }
    
    @FXML
    private void menuAyuda(ActionEvent event){
        
        
    }
    
    @FXML
    private void miDescripcionVentana(ActionEvent event){
        
    }
    
    @FXML
    private void miManualUsuario(ActionEvent event){
        
    }
    
    @FXML
    private void miSobreNosotros(ActionEvent event){
    
    }
    
    @FXML
    private void menuCR(ActionEvent event){
    
    }
    
}
