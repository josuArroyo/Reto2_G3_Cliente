/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import static grupo3.reto2.controller.TrainingController.LOGGER;
import grupo3.reto2.logic.TrainingFactory;
import grupo3.reto2.logic.TrainingInterface;
//import grupo3.reto2.logic.TrainingRESTfulClient;
import grupo3.reto2.model.Entrenamiento;
import grupo3.reto2.model.User;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author 2dam
 */
public class TrainingController {
    
    @FXML
    private Stage stage;
//    private TrainingInterface tInter = new TrainingRESTfulClient();
     Entrenamiento entrena = new Entrenamiento();
    
    
    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextArea descripArea;
    
    @FXML
    private DatePicker fechdate;
    
    @FXML
    private ComboBox durCombo, intCombo, repCombo, objCombo, filterCombo;
 
    @FXML
    private Pane paneAdmin;
    
    @FXML
    private Button btnCrear, btnModificar, btnEliminar, btnInforme, btnCerrar;

    @FXML
    private Label lblDscript, lblDuracion, lblFecha, lblIntensidad, lblRepeticiones, lblObjetivo;
    
    @FXML
    private TableView <Entrenamiento> table;
    
    @FXML
    ObservableList <Entrenamiento> listEntrena;
    private int posicionEntrenamiento;
    
    @FXML
    private TableColumn tcDescrip, tcDuracion, tcDate, tcIntensidad, tcRepet, tcObjetivo;
   
    
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/TrainingController");
    
    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Training stage");
        Scene scene = new Scene(root);
        //Entrenamiento entrenamiento = new Entrenamiento;
        User user = new User();
       
        
//        El (paneAdmin) estará habilitado cuando se inicie sesión 
//        como administrador, si se inicia como usuario estará deshabilitado. 
         

//        Los label que hay en el panel (paneAdmin) de descripción del entrenamiento, 
//        duración del entrenamiento, fecha del periodo, intensidad, repeticiones y 
//        objetivo estarán visibles si se inicia como administrador, si se inicia como usuario no.
        
        
//        En el panel principal (paneAll) la TableView (table) estará visible siempre. 
//        Si se inicia como administrador podrá ejecutar el CRUD completo, 
//        si es como usuario solo será visible y podrá filtrar los entrenamientos.
          table.setDisable(false);
          
          ObservableList <Entrenamiento> listEntrena = FXCollections.observableArrayList();
          table.setItems(listEntrena);
          
          tcDescrip.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
          tcDuracion.setCellValueFactory(new PropertyValueFactory<>("Duracion"));
          tcDate.setCellValueFactory(new PropertyValueFactory<>("Fecha de entrenamiento"));
          tcIntensidad.setCellValueFactory(new PropertyValueFactory<>("Intensidad"));
          tcRepet.setCellValueFactory(new PropertyValueFactory<>("Repeticion"));
          tcObjetivo.setCellValueFactory(new PropertyValueFactory<>("Objetivo"));
          
          
        
        //El filtrado es mediante un ComboBox está visible y habilitado siempre 
        filterCombo.setDisable(false);
        //filterCombo.setOnAction(this::handleAction);
        
        //El botón informe está habilitado y visible.
        btnInforme.setDisable(false);
       // btnInforme.setOnAction(this::handleButtonInformeAction);

        //El botón cerrar está habilitado y visible. 
        btnCerrar.setDisable(false);
        btnCerrar.setOnAction(this::handleCerrarButtonAction);
        
        
        //La ventana no es redimensionable.
        Stage stage = new Stage();
        stage.setResizable(false);

        //La ventana es una ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        //El foco inicialmente estará en el campo de nombre de usuario.
        descripArea.requestFocus();

        //El título de la ventana es “Training”.
        stage.setTitle("Training");

        stage.show();
        
        //Invocamos a la factoria
//        TrainingFactory fact = new TrainingFactory();
//        
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
//    private ObservableList <Entrenamiento> cargarTodos() {
//       ObservableList <Entrenamiento> listEntrena;
//        List<Entrenamiento> todosEntrenas;
//        todosEntrenas = tInter.findAll_XML(new GenericType <List<Entrenamiento>> () {}); 
//        listEntrena = FXCollections.observableArrayList(tInter.findAll_XML(GenericType <Entrenamiento>));
//        listEntrena.addAll(todosEntrenas);
//        table.setItems(listEntrena);
//       // return listEntrena;
//    
//   }
    
    @FXML
    private void handleCrearButtonAction(ActionEvent event) {
       
    }
    
     @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        
    }
    
     @FXML
    private void handleEliminarButtonAction(ActionEvent event) {
        listEntrena.remove(posicionEntrenamiento);
    }
    
    
    
     @FXML
    private void handleCerrarButtonAction(ActionEvent event) {
        try {

            //El botón está habilitado
            btnCerrar.setDisable(false);
            //Con esto vamos a crear una ventana de confirmación al pulsar el botón de salir
            Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("¿Deseas Salir?");
            //Con este Optional<ButtonType> creamos botones de Ok y cancelar
            Optional<ButtonType> action = ventanita.showAndWait();
            //Si le da a OK el programa dejará de existir, se cierra por completo
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
