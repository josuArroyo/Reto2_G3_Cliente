/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.logic.ObjectiveManagerFactory;
import grupo3.reto2.model.Admin;
import grupo3.reto2.model.Lugar;
import grupo3.reto2.model.Objetivo;
import grupo3.reto2.model.User;
import grupo3.reto2.model.UserPrivilege;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;



/**
 *
 * @author Diego
 */
public class ObjectiveController{
    
    @FXML
    private Stage stage;
    
    private static final Logger LOGGER = Logger.getLogger("/controller/ObjectiveController");
    
    private final ObjectiveManagerFactory factoryObj = new ObjectiveManagerFactory();
    
    private Objetivo objetivo = new Objetivo();
    
    private User user = new User();
    
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
    private TableView<Objetivo> TableObjetivo;
    
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
    
    
    private String filtroParam;
    
    private String findID;
    
    private Integer ID;
    

    
    private ObservableList<Objetivo> objectiveData;
    
    
     
    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("¿Deseas Salir?");
            Optional<ButtonType> action = ventanita.showAndWait();
            if (action.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                ventanita.close();
            }
    }
    
    @FXML
    private void handleCreateButtonAction(ActionEvent event){
         if(txtDescriObjeti.getText().length() > 100 || txtValorParam.getText().length() > 20){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Demasiados carácteres"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita.close();
            }
        }else if (txtDescriObjeti.getText().trim().isEmpty() || txtDescriParam.getText().trim().isEmpty() || txtValorParam.getText().trim().isEmpty()){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Campos vacios"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita.close();
            }
        }else{
            objetivo.setDescriParam(txtDescriParam.getText());
            objetivo.setDescripcion(txtDescriParam.getText());
            objetivo.setValorParam(txtValorParam.getText());
            factoryObj.getFactory().create_XML(objetivo);
            objectiveData = FXCollections.observableArrayList(cargarTodo());
        }
    }
    
    @FXML
    private void handleModifyButtonAction(ActionEvent event){
        
         if(txtDescriObjeti.getText().length() > 100 || txtValorParam.getText().length() > 20){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Demasiados carácteres"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita.close();
            }
        }else if (txtDescriObjeti.getText().trim().isEmpty() || txtDescriParam.getText().trim().isEmpty() || txtValorParam.getText().trim().isEmpty()){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Campos vacios"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita.close();
            }
        }else{
              objetivo.setIdObjetivo(TableObjetivo.getSelectionModel().getSelectedItem().getIdObjetivo());
              objetivo.setDescriParam(txtDescriParam.getText());
              objetivo.setDescripcion(txtDescriObjeti.getText());
              objetivo.setValorParam(txtValorParam.getText());
              factoryObj.getFactory().edit_XML(objetivo);
              objectiveData = FXCollections.observableArrayList(cargarTodo());  
        }
        
    }
    
    @FXML
    private void handleDeleteButtonAction(ActionEvent event){
        
        Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Borrador");
            ventanita.setContentText("¿Quieres borrar?"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                if(txtDescriObjeti.getText().length() > 100 || txtValorParam.getText().length() > 20){
            Alert ventanita2 = new Alert(Alert.AlertType.ERROR);
            ventanita2.setHeaderText(null);
            ventanita2.setTitle("Error");
            ventanita2.setContentText("Demasiados carácteres"); 
            Optional<ButtonType> action2 = ventanita2.showAndWait();           
            if (action2.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita2.close();
            }
        }else if (txtDescriObjeti.getText().trim().isEmpty() || txtDescriParam.getText().trim().isEmpty() || txtValorParam.getText().trim().isEmpty()){
            Alert ventanita3 = new Alert(Alert.AlertType.ERROR);
            ventanita3.setHeaderText(null);
            ventanita3.setTitle("Error");
            ventanita3.setContentText("Campos vacios"); 
            Optional<ButtonType> action3 = ventanita3.showAndWait();           
            if (action3.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita3.close();
            }
        }else{
                Objetivo selectedObjective = TableObjetivo.getSelectionModel().getSelectedItem();
                factoryObj.getFactory().remove(selectedObjective.getIdObjetivo().toString());
                objectiveData = FXCollections.observableArrayList(cargarTodo());
                
            }
                
            }else{
                ventanita.close();
            }
        
        /**/
        
    }
    
    @FXML
    private void handleSearchButton(ActionEvent event){
        Object newValue = new Object();
        if(txtFiltrarParam.getText().length() > 50){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Demasiados carácteres"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtDescriObjeti.setText("");
                txtClaveObjet.setText("");
                txtDescriParam.setText("");
                txtValorParam.setText("");
                ventanita.close();
            }
        }else if(txtFiltrarParam.getText().isEmpty()){
            Alert ventanita = new Alert(Alert.AlertType.ERROR);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("Campos vacios"); 
            Optional<ButtonType> action = ventanita.showAndWait();           
            if (action.get() == ButtonType.OK) {
                txtFiltrarParam.setText("");
                ventanita.close();
            }
        }else{
            switch(cbxFiltr.getValue().toString()){
                case("Por valor de parámetro") :
                    cargarFiltro1();
                break;
                case("Por clave Objetivo") :
                    cargarFiltro2();
                break;
            }
        }
    }
       
    @FXML
    private ObservableList<Objetivo> cargarTodo(){
        ObservableList<Objetivo> listObjetivo;
        List<Objetivo> todosObjetivos;
        todosObjetivos = factoryObj.getFactory().findAll_XML(new GenericType<List<Objetivo>>(){});
        
        listObjetivo = FXCollections.observableArrayList(todosObjetivos);
        TableObjetivo.setItems(listObjetivo);
        TableObjetivo.refresh();
        return listObjetivo;
    }
    
    @FXML
    private ObservableList<Objetivo> cargarFiltro1(){
        ObservableList<Objetivo> listObjetivo;
        List<Objetivo> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(factoryObj.getFactory().findByValue_XML(new GenericType<List<Objetivo>>(){}, txtFiltrarParam.getText()));
        
        listObjetivo = FXCollections.observableArrayList(FiltradoParam);
        TableObjetivo.setItems(listObjetivo);
        TableObjetivo.refresh();
        return listObjetivo;
    }
    
    @FXML
    private ObservableList<Objetivo> cargarFiltro2(){
        ID = Integer.parseInt(txtFiltrarParam.getText());
        ObservableList<Objetivo> listObjetivo;
        List<Objetivo> FiltradoParam;
        FiltradoParam = FXCollections.observableArrayList(factoryObj.getFactory().find_XML(Objetivo.class, txtFiltrarParam.getText()));
        
        listObjetivo = FXCollections.observableArrayList(FiltradoParam);
        TableObjetivo.setItems(listObjetivo);
        TableObjetivo.refresh();
        return listObjetivo;
    }
    
    @FXML
    private void cambioTexto(ObservableValue observable, Object oldValue, Object newValue){
        if(txtDescriObjeti.getText().trim().isEmpty() ||  txtDescriParam.getText().trim().isEmpty() || txtValorParam.getText().trim().isEmpty()){
            btnCrear.setDisable(true);
            btnDelete.setDisable(true);
            btnModifi.setDisable(true);
        }else{
            btnCrear.setDisable(false);
            btnDelete.setDisable(false);
            btnModifi.setDisable(false);
        }
    }
      
    @FXML
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue){
        if (newValue != null) {
            Objetivo objetiv = (Objetivo) newValue;
            txtClaveObjet.setText(objetiv.getIdObjetivo().toString());
            txtDescriObjeti.setText(objetiv.getDescripcion());
            txtDescriParam.setText(objetiv.getDescriParam());
            txtValorParam.setText(objetiv.getValorParam());
        }
    }
    
    @FXML
    private void handleInformButton(ActionEvent event){
        try {
            //este metodo sirve para sacar un report con los datos que hay en la tabla de la ventana 
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/grupo3/reto2/report/ObjectiveReport.jrxml"));
            JRBeanCollectionDataSource dataItems;
            dataItems = new JRBeanCollectionDataSource((Collection<Objetivo>) this.TableObjetivo.getItems());
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

        } catch (JRException ex) {

            Logger.getLogger(ObjectiveController.class.getName()).log(Level.SEVERE, null, ex);

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
        txtClaveObjet.setDisable(true);
        txtDescriObjeti.setDisable(false);
        txtDescriParam.setDisable(false);
        txtFiltrarParam.setDisable(false);
        txtValorParam.setDisable(false);
        
        //Activación de botones
        txtClaveObjet.textProperty().addListener(this::cambioTexto);
        txtDescriObjeti.textProperty().addListener(this::cambioTexto);
        txtValorParam.textProperty().addListener(this::cambioTexto);
        txtDescriParam.textProperty().addListener(this::cambioTexto);
        
        //Añadimos los datos de la fila seleccionada en los campos de texto
        TableObjetivo.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);
        
        //Los botones asociados al crud están deshabilitados
        btnCrear.setDisable(true);
        btnDelete.setDisable(true);
        btnModifi.setDisable(true);
        
        //Los otros botones están habilitados
        btnInform.setDisable(false);
        btnSalir.setDisable(false);
        
        
        
        
        //El combobox está habilitado y tiene su método
        cbxFiltr.setDisable(false);
        cbxFiltr.getSelectionModel().getSelectedItem();
        
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
        btnCrear.setOnAction(this::handleCreateButtonAction);
        btnModifi.setOnAction(this::handleModifyButtonAction);
        btnDelete.setOnAction(this::handleDeleteButtonAction);
        btnFiltrar.setOnAction(this::handleSearchButton);
        btnInform.setOnAction(this::handleInformButton);
        
        cbxFiltr.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent event) {
                if(null == cbxFiltr.getValue().toString()){
            txtFiltrarParam.setDisable(true);
            btnFiltrar.setVisible(false);
        }else switch (cbxFiltr.getValue().toString()) {
            case "Por valor de parámetro":
                txtFiltrarParam.setDisable(false);
                txtFiltrarParam.setVisible(true);
                btnFiltrar.setVisible(true);
                btnFiltrar.setDisable(false);
                break;
            case "Por clave Objetivo":
                txtFiltrarParam.setDisable(false);
                txtFiltrarParam.setVisible(true);
                btnFiltrar.setVisible(true);
                btnFiltrar.setDisable(false);               
                break;
            default:
                txtFiltrarParam.setDisable(true);
                txtFiltrarParam.setVisible(false);
                btnFiltrar.setVisible(false);
                btnFiltrar.setDisable(true);
                cargarTodo();
                break;
        }
            }
        
        });
        
        //Establecemos las factorias para los valores de celda
        try{
            colObjetivo.setCellValueFactory(new PropertyValueFactory<>("idObjetivo"));
            colDescriParam.setCellValueFactory(new PropertyValueFactory<>("descriParam"));
            colDescriObje.setCellValueFactory(new PropertyValueFactory<>("descripcion")); 
            colValParam.setCellValueFactory(new PropertyValueFactory<>("valorParam"));
            colAdmin.setCellValueFactory(new PropertyValueFactory<>("admin"));
            
        //Cargamos los datos en la tabla
            objectiveData = FXCollections.observableArrayList(factoryObj.getFactory().findAll_XML(new GenericType<List<Objetivo>>(){}));
            TableObjetivo.setItems(objectiveData);
           
            stage.show();
         }catch(Exception e){
            LOGGER.info("Error a la hora de cargar los datos");
        }
       
    } 
    public void setStage(Stage stage) {
        this.stage = stage;
    }
   
    
}
