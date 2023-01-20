/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *
 * @author Diego
 */
public class ObjectiveController{
    
    @FXML
    private Stage stage;
    
    @FXML
    private Button btnCrear;
    
    @FXML
    private Button btnModifi;
    
    @FXML
    private Button btnSalir;
    
    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnFiltra;
    
    @FXML
    private Button btnInform;
    
    @FXML
    private TableColumn colObjetivo;
    
    @FXML
    private TableColumn colDescriObj;
    
    @FXML
    private TableColumn colValParam;
    
    @FXML
    private TableColumn colDescriParam;
    
    @FXML
    private TableColumn colAdmin;
    
    @FXML
    private TextField txtDescriObjeti;
    
    @FXML 
    private TextField txtClaveObjet;
    
    @FXML
    private TextField txtValorParam;
    
    @FXML 
    private TextField txtFiltrarParam;
    
    @FXML 
    private TextField txtDescriParam;
    
    @FXML
    private Label lblObjetivo;
    
    @FXML
    private Label lblDescri;
    
    @FXML
    private Label lblDescripParam;
    
    @FXML
    private Label lblValorParam;
    
    @FXML
    private Label lblFiltr;
    
    @FXML
    private ComboBox cbxFiltr;
     
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
    }
    
    @FXML
    public void initStage(Parent root) {
        // TODO
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Objetivos");

        stage.show();
    } 
    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
    
}
