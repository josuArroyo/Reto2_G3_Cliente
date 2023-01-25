/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.logica.ObjectiveManagerFactory;
import grupo3.reto2.model.Objetivo;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.ws.rs.core.GenericType;



/**
 *
 * @author Diego
 */
public class ObjectiveController{
    
    @FXML
    private Stage stage;
    
    private final ObjectiveManagerFactory factoryObj = new ObjectiveManagerFactory();
    
    @FXML
    private Button btnCrear;
    
    @FXML
    private Button btnModifi;
    
    @FXML
    private Button btnSalir;
    
    @FXML
    private Button btnDelete;
    
    @FXML
    private Button btnFiltrar;
    
    @FXML
    private Button btnInform;
    
    @FXML
    private TableView TableObjetivo;
    
    @FXML
    private TableColumn colObjetivo;
    
    @FXML
    private TableColumn colDescriObje;
    
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
    
    private ObservableList<Objetivo> objectiveData;
     
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
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
    }
    
    @FXML
    private void handleCBoxOptionAction(ActionEvent e){
        if(null == cbxFiltr.getValue().toString()){
            txtFiltrarParam.setDisable(true);
            btnFiltrar.setVisible(false);
        }else switch (cbxFiltr.getValue().toString()) {
            case "Por valor de parámetro":
                txtFiltrarParam.setDisable(false);
                btnFiltrar.setVisible(true);
                break;
            case "Por clave Objetivo":
                txtFiltrarParam.setDisable(false);
                btnFiltrar.setVisible(true);
                break;
            default:
                txtFiltrarParam.setDisable(true);
                btnFiltrar.setVisible(false);
                break;
        }
    }
    
     
    
    @FXML
    public void initStage(Parent root) {
        // TODO
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        //La ventana es modal
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Objetivos");
        
        //Los siguientes campos són visibles
        lblDescri.setVisible(true);
        lblDescripParam.setVisible(true);
        lblFiltr.setVisible(true);
        lblObjetivo.setVisible(true);
        lblValorParam.setVisible(true);
        
        //Los campos de textos están habilitados
        txtClaveObjet.setDisable(false);
        txtDescriObjeti.setDisable(false);
        txtDescriParam.setDisable(false);
        txtFiltrarParam.setDisable(false);
        txtValorParam.setDisable(false);
        
        //Los botones asociados al crud están deshabilitados
        btnCrear.setDisable(true);
        btnDelete.setDisable(true);
        btnModifi.setDisable(true);
        
        //Los otros botones están habilitados
        btnInform.setDisable(false);
        btnSalir.setDisable(false);
        
        //El combobox está habilitado
        cbxFiltr.setDisable(false);
        
        //Elementos relacionados con el comboBox
        txtFiltrarParam.setDisable(true);
        txtFiltrarParam.setVisible(false);
        btnFiltrar.setDisable(true);
        btnFiltrar.setVisible(false);
        
        //Propiedades el combobox
        cbxFiltr.getItems().addAll(
                "Por valor de parámetro",
                "Por clave Objetivo",
                "Todos los objetivos"
        );
        
        
        //El foco está en el campo txtDescriParam
        txtDescriParam.requestFocus();
        
        //Metodos de botones
        btnSalir.setOnAction(this::handleExitButtonAction);
        //cbxFiltr.setOnAction(this::handleCBoxOptionAction);
        
        //Establecemos las factorias para los valores de celda
        
        colObjetivo.setCellValueFactory(new PropertyValueFactory<>("idObjetivo"));
        colDescriParam.setCellValueFactory(new PropertyValueFactory<>("descriParam"));
        colDescriObje.setCellValueFactory(new PropertyValueFactory<>("descripcion")); 
        colValParam.setCellValueFactory(new PropertyValueFactory<>("valorParam"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
        
        //Cargamos los datos en la tabla
        objectiveData = FXCollections.observableArrayList(factoryObj.getFactory().findAll_XML(new GenericType<List<Objetivo>>(){}));
        TableObjetivo.setItems(objectiveData);
        stage.show();
    } 
    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
    
}
