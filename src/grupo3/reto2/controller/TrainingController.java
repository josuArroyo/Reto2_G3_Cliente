/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import static grupo3.reto2.controller.TrainingController.LOGGER;
import grupo3.reto2.logic.TrainingFactory;
import grupo3.reto2.logic.*;
import grupo3.reto2.logic.TrainingRESTfulClient;
import grupo3.reto2.logic.ObjectiveRESTfulclient;
import grupo3.reto2.model.Entrenamiento;
import grupo3.reto2.model.Objetivo;
import grupo3.reto2.model.User;
import java.io.IOException;
import java.net.URL;
import javafx.beans.value.ObservableValue;
import java.util.List;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.Date;
import java.text.SimpleDateFormat;
import javafx.scene.control.TableCell;
import java.time.ZoneId;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;

/**
 * This is the controller for the training window
 * @author Jessica
 */
public class TrainingController {

    @FXML
    private Stage stage;
    private TrainingInterface tInter = new TrainingRESTfulClient();
    private ObjectiveManager oInter = new ObjectiveRESTfulclient();
    Entrenamiento entrena = null;
    //Invocamos a la factoria
    TrainingFactory fact = new TrainingFactory();
    private User user;
    Objetivo objetivo = new Objetivo();

    //Declaramos los campos que utilizaremos en esta ventana
    @FXML
    private TextArea descripArea;

    @FXML
    private DatePicker fechDate;

    @FXML
    private ComboBox<Integer> durCombo;
    @FXML
    private ComboBox<Integer> intCombo;
    @FXML
    private ComboBox<Integer> repCombo;
    @FXML
    private ComboBox<Objetivo> objCombo;
    @FXML
    private ComboBox<String> filterCombo;

    @FXML
    private TextField txtFilter;

    @FXML
    private Button btnFilter;

    @FXML
    private Pane paneAdmin;

    @FXML
    private Button btnCrear, btnModificar, btnEliminar, btnInforme, btnCerrar, btnAyuda;

    @FXML
    private Label lblDscript, lblDuracion, lblFecha, lblIntensidad, lblRepeticiones, lblObjetivo;

    @FXML
    private TableView<Entrenamiento> table;

    @FXML
    ObservableList<Entrenamiento> listEntrena;
    ObservableList<Objetivo> listObjetivos;
    private int posicionEntrenamiento;

    @FXML
    private TableColumn tcDescrip, tcDuracion, tcDate, tcIntensidad, tcRepet, tcObjetivo;

    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/TrainingController");

    
    /**
     * Method to initialize the window
     * @param root the root of the window
     */
    
    @FXML
    public void initStage(Parent root) {
        LOGGER.info("Initializing Training stage");
        Scene scene = new Scene(root);
        //Entrenamiento entrenamiento = new Entrenamiento;
        

//        El (paneAdmin) estar?? habilitado cuando se inicie sesi??n 
//        como administrador, si se inicia como usuario estar?? deshabilitado. 
        
        List<User> usersiden;
        
        
        /*if (user.getPrivilege() == user.getPrivilege().CLIENT) {
            paneAdmin.setDisable(true);
            paneAdmin.setVisible(false);
        } else  {
            paneAdmin.setDisable(false);
            paneAdmin.setVisible(true);
        }*/
//        Los label que hay en el panel (paneAdmin) de descripci??n del entrenamiento, 
//        duraci??n del entrenamiento, fecha del periodo, intensidad, repeticiones y 
//        objetivo estar??n visibles si se inicia como administrador, si se inicia como usuario no.
//        En el panel principal (paneAll) la TableView (table) estar?? visible siempre. 
//        Si se inicia como administrador podr?? ejecutar el CRUD completo, 
//        si es como usuario solo ser?? visible y podr?? filtrar los entrenamientos.
        durCombo.getItems().addAll(30, 45, 60, 75, 90);
        intCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        repCombo.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        objCombo.getItems().addAll(cargarObjetivos());

        table.setDisable(false);

        tcDescrip.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcDuracion.setCellValueFactory(new PropertyValueFactory<>("duracion"));
        tcDate.setCellValueFactory(new PropertyValueFactory<>("fechaPeriod"));
        tcDate.setCellFactory(column -> {
            TableCell<Entrenamiento, Date> cell = new TableCell<Entrenamiento, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        if (item != null) {
                            setText(format.format(item));
                        }

                    }
                }
            };

            return cell;
        });

        tcIntensidad.setCellValueFactory(new PropertyValueFactory<>("intensidad"));
        tcRepet.setCellValueFactory(new PropertyValueFactory<>("repeticion"));
        tcObjetivo.setCellValueFactory(new PropertyValueFactory<>("objetivo"));

        listEntrena = FXCollections.observableArrayList(cargarTodos());
        table.setItems(listEntrena);
        table.getSelectionModel().selectedItemProperty().addListener(this::handleTrainingTableSelectionChanged);
        //table.getColumns().addAll(tcDescrip, tcDuracion, tcDate, tcIntensidad, tcRepet, tcObjetivo);

        btnCrear.setOnAction(this::handleCrearButtonAction);
        btnModificar.setOnAction(this::handleModificarButtonAction);
        btnEliminar.setOnAction(this::handleEliminarButtonAction);
        // table.getSelectionModel().selectedItemProperty().addListener(this::handleTrainingTableSelectionChanged);

        //El filtrado es mediante un ComboBox est?? visible y habilitado siempre 
        filterCombo.setDisable(false);
        //Los tipos de filtro ser??n: Todo, duraci??n, intensidad y objetivo.
        filterCombo.getItems().addAll("Todos", "Duraci??n", "Intensidad", "Objetivo");

        txtFilter.setDisable(false);
        btnFilter.setDisable(false);
        btnFilter.setOnAction(this::handleActionFilterSearch);

        //El bot??n informe est?? habilitado y visible.
        btnInforme.setDisable(false);
        btnInforme.setOnAction(this::handleButtonInformeAction);
        
        //El boton de ayuda estara habilitado
        btnAyuda.setDisable(false);
        btnAyuda.setOnAction(this::handleAyudaButtonAction);

        //El bot??n cerrar est?? habilitado y visible. 
        btnCerrar.setDisable(false);
        btnCerrar.setOnAction(this::handleCerrarButtonAction);

        //La ventana no es redimensionable.
        Stage stage = new Stage();
        stage.setResizable(false);

        //La ventana es una ventana modal.
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);

        //El foco inicialmente estar?? en el campo de nombre de usuario.
        descripArea.requestFocus();

        //El t??tulo de la ventana es ???Training???.
        stage.setTitle("Training");

        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    
    
      /**
     * Method to load all trainings
     * @param 
     */
    
    private ObservableList<Entrenamiento> cargarTodos() {
        ObservableList<Entrenamiento> listEntrena;
        List<Entrenamiento> todosEntrenas;
        todosEntrenas = tInter.findAll_XML(new GenericType<List<Entrenamiento>>() {
        });
        listEntrena = FXCollections.observableArrayList(todosEntrenas);
        table.setItems(listEntrena);
        table.refresh();
        return listEntrena;

    }

    /**
     * Method to load all objectives
     * @param 
     */
    
    private ObservableList<Objetivo> cargarObjetivos() {
        ObservableList<Objetivo> listObjetivos;
        List<Objetivo> todosObjetivos;
        todosObjetivos = oInter.findAll_XML(new GenericType<List<Objetivo>>() {
        });
        listObjetivos = FXCollections.observableArrayList(todosObjetivos);
        table.setItems(listEntrena);
        table.refresh();
        return listObjetivos;

    }

    /**
     * Method to filter by training duration
     * @param 
     */
    
    private ObservableList<Entrenamiento> cargarDuracion() {
        ObservableList<Entrenamiento> filtroEntrenamiento;
        List<Entrenamiento> duracionFiltro;
        duracionFiltro = tInter.findDuracion_XML(new GenericType<List<Entrenamiento>>() {
        }, txtFilter.getText());

        filtroEntrenamiento = FXCollections.observableArrayList(duracionFiltro);
        table.setItems(filtroEntrenamiento);
        table.refresh();
        return filtroEntrenamiento;
    }

    /**
     * Method to filter by training intensity
     * @param 
     */
    private ObservableList<Entrenamiento> cargarIntensidad() {
        ObservableList<Entrenamiento> filtroEntrenamiento;
        List<Entrenamiento> intensidadFiltro;
        intensidadFiltro = fact.getFactory().findIntensidad_XML(new GenericType<List<Entrenamiento>>() {
        }, txtFilter.getText());

        filtroEntrenamiento = FXCollections.observableArrayList(intensidadFiltro);
        table.setItems(filtroEntrenamiento);
        table.refresh();
        return filtroEntrenamiento;
    }

     /**
     * Method to filter by objective id
     * @param 
     */
    private ObservableList<Entrenamiento> cargarFiltroObjetivo() {
        ObservableList<Entrenamiento> filtroObjetivo;
        List<Entrenamiento> objetivoFiltro;
        objetivoFiltro = fact.getFactory().findObjetivo_XML(new GenericType<List<Entrenamiento>>() {
        }, txtFilter.getText());

        filtroObjetivo = FXCollections.observableArrayList(objetivoFiltro);
        table.setItems(filtroObjetivo);
        table.refresh();
        return filtroObjetivo;
    }
    
    /**
     * 'Crear' button method with validations
     * @param event
     */

    @FXML
    private void handleCrearButtonAction(ActionEvent event){
        entrena = new Entrenamiento();

        //Validar que los campos descripci??n, duraci??n, intensidad y repeticiones y objetivo est??n informados.
        //Validar que los ComboBox est??n seleccionados
        try {
            //Si no est??n informados alguno de los campos saldr?? un mensaje de error.
            if (this.descripArea.getText().isEmpty() || this.durCombo.getSelectionModel().isEmpty()
                    || this.intCombo.getSelectionModel().isEmpty() || this.repCombo.getSelectionModel().isEmpty()
                    || this.objCombo.getSelectionModel().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }
            //Validar que el DatePicker est?? informado.
            if (this.fechDate.getValue() == null) {
                throw new Exception("LA FECHA NO HA SIDO SELECCIONADO");
            }

            //Validar que el m??ximo n??mero de caracteres en el campo de descripci??n de entrenamiento sea de 100 caracteres.      
            if (this.descripArea.getText().length() > 100) {
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");

            } else {
                try {
                    // tInter.create_XML(entrena);
                    entrena.setDescripcion(descripArea.getText());
                    entrena.setDuracion(durCombo.getSelectionModel().getSelectedItem());
                    entrena.setFechaPeriod(Date.from(fechDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    entrena.setIntensidad(intCombo.getSelectionModel().getSelectedItem());
                    entrena.setRepeticion(repCombo.getSelectionModel().getSelectedItem());
                    entrena.setObjetivo(objCombo.getSelectionModel().getSelectedItem());
                    fact.getFactory().create_XML(entrena);
                    listEntrena.add(entrena);
                    // listEntrena = cargarTodos();

                    throw new Exception("ENTRENAMIENTO CORRECTO");

                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();

                }

            }

//        En caso de que todos los datos introducidos sean v??lidos y cumplan los requisitos mencionados anteriormente, 
//        se llama al m??todo create_XML de la interfaz pas??ndole un objeto (Entrenamiento) con los valores
//Seguido, saldr?? del m??todo del bot??n.
            //En caso de error salta la excepci??n con su mensaje correspondiente
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();

        }

    }

    /**
     * method for when we select an element of the table the
     * data is displayed in the administrator panel
     * @param event
     */
    @FXML
    private void handleTrainingTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {
            Entrenamiento entrena = (Entrenamiento) newValue;

            descripArea.setText(entrena.getDescripcion());
            durCombo.setValue(entrena.getDuracion());
            fechDate.setValue(entrena.getFechaPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            intCombo.setValue(entrena.getIntensidad());
            repCombo.setValue(entrena.getRepeticion());
            objCombo.setValue(entrena.getObjetivo());

        }
    }

    /**
     * 'Modificar' button method with validations
     * When selecting the training in the table it 
     * will appear in the admin panel and when changing 
     * something and clicking on modify it will update us
     * @param event
     */
    
    @FXML
    private void handleModificarButtonAction(ActionEvent event)  {

        try {
            //Si no est??n informados alguno de los campos saldr?? un mensaje de error.
            if (this.descripArea.getText().isEmpty() || this.durCombo.getSelectionModel().isEmpty()
                    || this.intCombo.getSelectionModel().isEmpty() || this.repCombo.getSelectionModel().isEmpty()
                    || this.objCombo.getSelectionModel().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }
            //Validar que el DatePicker est?? informado.
            if (this.fechDate.getValue() == null) {
                throw new Exception("LA FECHA NO HA SIDO SELECCIONADO");
            }

            //Validar que el m??ximo n??mero de caracteres en el campo de descripci??n de entrenamiento sea de 100 caracteres.      
            if (this.descripArea.getText().length() > 100) {
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");

            } else {
                try {
                    Entrenamiento entrena = new Entrenamiento();

                    Entrenamiento selected = table.getSelectionModel().getSelectedItem();
                    posicionEntrenamiento = selected.getIdEntrenamiento();
                    entrena.setIdEntrenamiento(posicionEntrenamiento);
                    entrena.setDescripcion(descripArea.getText());
                    entrena.setDuracion(durCombo.getSelectionModel().getSelectedItem());
                    entrena.setFechaPeriod(Date.from(fechDate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    entrena.setIntensidad(intCombo.getSelectionModel().getSelectedItem());
                    entrena.setRepeticion(repCombo.getSelectionModel().getSelectedItem());
                    entrena.setObjetivo(objCombo.getSelectionModel().getSelectedItem());

                    tInter.edit_XML(entrena);
                    listEntrena = cargarTodos();

                    throw new Exception("ENTRENAMIENTO MODIFICADO CORRECTAMENTE");

                } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();

                }

            }
        }catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    /**
     * 'Elimiinar' button method 
     * when selecting the training in the table it will 
     * appear in the admin panel and when changing something 
     * and clicking on delete it will be deleted
     * @param event
     */
    
    @FXML
    private void handleEliminarButtonAction(ActionEvent event) {
        Entrenamiento selectedEntrena = (Entrenamiento) table.getSelectionModel().getSelectedItem();

        try {
            try {
                tInter.remove(selectedEntrena.getIdEntrenamiento());

                //Integer id = entrena.getIdEntrenamiento();
                //entrena.getIdEntrenamiento().compareTo(posicionEntrenamiento);
                table.getItems().remove(selectedEntrena);

                throw new Exception("EL ENTRENAMIENTO SE HA ELIMINADO CORRECTAMENTE");

            } catch (Exception e) {
                new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();

            }

        } catch (Exception e) {
            //throw new Exception("EL ENTRENAMIENTO NO SE HA PODIDO ELIMINAR ");
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            //throw new Exception("EL ENTRENAMIENTO NO SE HA PODIDO ELIMINAR ");
        }

    }

    /**
     * 'Filter' comboBox method to filter by each selected option
     * @param event
     */
    
    @FXML
    private void handleActionFilterSearch(ActionEvent event) {

        //Object newValue = new Object();
        switch (filterCombo.getValue().toString()) {
            case ("Todos"):
                cargarTodos();
                break;
            case ("Duraci??n"):
                cargarDuracion();
                break;
            case ("Intensidad"):
                cargarIntensidad();
                break;
            case ("Objetivo"):
                cargarFiltroObjetivo();

        }

    }
    
    /**
     * 'Informe' button method to display the table report
     * @param event
     */
    
    @FXML
    private void handleButtonInformeAction(ActionEvent event) {
        try {
            //LOGGER.info("Beginning printing action...");
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/grupo3/reto2/report/TrainingReport.jrxml"));

            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Entrenamiento>) this.table.getItems());

            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);

            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

        } catch (JRException ex) {
            //If there is an error show message and
            //log it.
            System.out.println("Error");
        }

    }

    @FXML
    private void handleAyudaButtonAction(ActionEvent event) {

        
       try {
            Stage mainStage = new Stage();
            URL viewLink = getClass().getResource("/grupo3/reto2/view/HelpTraining.fxml");
            // initialition loader
            FXMLLoader loader = new FXMLLoader(viewLink);
            //make the root with the loader
            Parent root = (Parent) loader.load();
            //Get the controller
            HelpTrainingController mainStageController = ((HelpTrainingController) loader.getController());
            //set the stage
            mainStageController.setStage(mainStage);
            //start the stage
            mainStageController.initStage(root);
            this.stage.close();
        } catch (IOException ex) {
            Logger.getLogger(TrainingController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
         
        /*
        try {
            LOGGER.info("Loading help view...");
            //Load node graph from fxml file
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/grupo3/reto2/view/Help.fxml"));
            Parent root = (Parent) loader.load();
            HelpController helpController = ((HelpController) loader.getController());
            //Initializes and shows help stage
            helpController.initStage(root);
        } catch (Exception ex) {
            //If there is an error show message and
            //log it.
         
            LOGGER.log(Level.SEVERE,
                    "UI GestionUsuariosController: Error loading help window: {0}",
                    ex.getMessage());
        }
*/
        
        
    }
    
    /**
     *'Cerrar' button method. It redirects us to the login window again. The
     * current window is closed without saving the data if we have not pressed 'crear/modificar'
     *@param event
     */
    @FXML
    private void handleCerrarButtonAction(ActionEvent event) {
        try {

            //El bot??n est?? habilitado
            btnCerrar.setDisable(false);
            //Con esto vamos a crear una ventana de confirmaci??n al pulsar el bot??n de salir
            Alert ventanita = new Alert(Alert.AlertType.CONFIRMATION);
            ventanita.setHeaderText(null);
            ventanita.setTitle("Advertencia");
            ventanita.setContentText("??Deseas Salir?");
            //Con este Optional<ButtonType> creamos botones de Ok y cancelar
            Optional<ButtonType> action = ventanita.showAndWait();
            //Si le da a OK el programa dejar?? de existir, se cierra por completo
            if (action.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                //Si le da a cancelar la ventana emergente se cerrar?? pero la ventana principal se mantiene
                ventanita.close();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage() + ButtonType.OK).showAndWait();
        }
    }
}
