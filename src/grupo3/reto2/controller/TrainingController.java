/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import static grupo3.reto2.controller.TrainingController.LOGGER;
import grupo3.reto2.logic.TrainingFactory;
import grupo3.reto2.logic.TrainingInterface;
import grupo3.reto2.logic.TrainingRESTfulClient;
import grupo3.reto2.model.Entrenamiento;
import grupo3.reto2.model.User;
import grupo3.reto2.model.UserPrivilege;
import static grupo3.reto2.model.UserPrivilege.ADMIN;
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
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Jessica
 */
public class TrainingController {

    @FXML
    private Stage stage;
    private TrainingInterface tInter = new TrainingRESTfulClient();
    Entrenamiento entrena = new Entrenamiento();

    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextArea descripArea;

    @FXML
    private DatePicker fechdate;

    @FXML
    private ComboBox<Integer> durCombo;
    @FXML
    private ComboBox<Integer> intCombo;
    @FXML
    private ComboBox<Integer> repCombo;
    @FXML
    private ComboBox objCombo;
    @FXML

   private ComboBox filterCombo ;

    @FXML
    private Pane paneAdmin;

    @FXML
    private Button btnCrear, btnModificar, btnEliminar, btnInforme, btnCerrar;

    @FXML
    private Label lblDscript, lblDuracion, lblFecha, lblIntensidad, lblRepeticiones, lblObjetivo;

    @FXML
    private TableView<Entrenamiento> table;

    @FXML
    ObservableList<Entrenamiento> listEntrena;
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
//        if (user.getPrivilege() == user.getPrivilege().ADMIN) {
//            paneAdmin.setDisable(false);
//            paneAdmin.setVisible(false);
//        } else {
//            paneAdmin.setDisable(true);
//            paneAdmin.setVisible(true);
//        }
//        Los label que hay en el panel (paneAdmin) de descripción del entrenamiento, 
//        duración del entrenamiento, fecha del periodo, intensidad, repeticiones y 
//        objetivo estarán visibles si se inicia como administrador, si se inicia como usuario no.
//        En el panel principal (paneAll) la TableView (table) estará visible siempre. 
//        Si se inicia como administrador podrá ejecutar el CRUD completo, 
//        si es como usuario solo será visible y podrá filtrar los entrenamientos.
        durCombo.getItems().addAll(30, 45, 60, 75, 90);
        intCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        repCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        objCombo.getItems().addAll();
        
        table.setDisable(false);

        tcDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("fechaPeriod"));
        tcIntensidad.setCellValueFactory(new PropertyValueFactory<>("intensidad"));
        tcRepet.setCellValueFactory(new PropertyValueFactory<>("repeticion"));
        tcObjetivo.setCellValueFactory(new PropertyValueFactory<>("objetivo"));

        listEntrena = FXCollections.observableArrayList(cargarTodos());
        table.setItems(listEntrena);
        //table.getColumns().addAll(tcDescrip, tcDuracion, tcDate, tcIntensidad, tcRepet, tcObjetivo);

        btnEliminar.setOnAction(this::handleEliminarButtonAction);
        // table.getSelectionModel().selectedItemProperty().addListener(this::handleTrainingTableSelectionChanged);

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
        TrainingFactory fact = new TrainingFactory();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private ObservableList<Entrenamiento> cargarTodos() {
        ObservableList<Entrenamiento> listEntrena;
        List<Entrenamiento> todosEntrenas;
        todosEntrenas = tInter.findAll_XML(new GenericType<List<Entrenamiento>>() {
        });
        listEntrena = FXCollections.observableArrayList(todosEntrenas);
        table.setItems(listEntrena);
        return listEntrena;

    }

    @FXML
    private void handleCrearButtonAction(ActionEvent event) {

        entrena.setDescripcion(descripArea.getText());
        entrena.setDuracion(durCombo.getSelectionModel().getSelectedItem());
        //entrenamiento.setFechaPeriod(fechdate.getValue());
        entrena.setIntensidad(intCombo.getSelectionModel().getSelectedItem());
        entrena.setRepeticion(repCombo.getSelectionModel().getSelectedItem());
       // entrena.setObjetivo(objCombo.getSelectionModel().getSelectedItem());

        //Validar que los campos descripción, duración, intensidad y repeticiones y objetivo están informados.
        //Validar que los ComboBox estén seleccionados
        try {
            //Si no están informados alguno de los campos saldrá un mensaje de error.
            if (this.descripArea.getText().isEmpty() || this.durCombo.getSelectionModel().isEmpty()
                    || this.intCombo.getSelectionModel().isEmpty() || this.repCombo.getSelectionModel().isEmpty()
                    || this.objCombo.getSelectionModel().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }

            //Validar que el máximo número de caracteres en el campo de descripción de entrenamiento sea de 100 caracteres.      
            if (this.descripArea.getText().length() > 15) {
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");
            }

            //Validar que el DatePicker esté informado.
            if (this.fechdate.getValue() == null) {
                throw new Exception("LA FECHA NO HA SIDO SELECCIONADO");
            }

            tInter.create_XML(entrena);
            table.setItems(listEntrena);

            //Seguido, saldrá del método del botón.
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();

        }

    }

//    @FXML
//    private void handleTrainingTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {
//        if (newValue != null) {
//            Entrenamiento entrenamiento = (Entrenamiento) newValue;
//            descripArea.setText(entrenamiento.getDescripcion());
//            durCombo.getSelectionModel().select(entrenamiento.getDuracion());
//            fechdate.getValue(entrenamiento.getFechaPeriod());
//            intCombo.getSelectionModel().select(entrenamiento.getDuracion());
//            repCombo.getSelectionModel().select(entrenamiento.getDuracion());
//            objCombo.getSelectionModel().select(entrenamiento.getDuracion());
//            
//        }
//    }
    @FXML
    private void handleModificarButtonAction(ActionEvent event) {

    }

    @FXML
    private void handleEliminarButtonAction(ActionEvent event) {
        Integer id = entrena.getIdEntrenamiento();
        entrena.getIdEntrenamiento().compareTo(posicionEntrenamiento);
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
