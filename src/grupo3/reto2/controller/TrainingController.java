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





/**
 *
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

    Objetivo objetivo = new Objetivo();

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
    private ComboBox<Objetivo> objCombo;
    @FXML
    private ComboBox <String> filterCombo;
    
    @FXML
    private TextField txtFilter;
   
    @FXML
    private Button btnFilter;

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
    ObservableList<Objetivo> listObjetivos;
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

        //El filtrado es mediante un ComboBox está visible y habilitado siempre 
        filterCombo.setDisable(false);
        //Los tipos de filtro serán: Todo, duración, intensidad y objetivo.
        filterCombo.getItems().addAll("Todos", "Duración", "Intensidad", "Objetivo");
       
        txtFilter.setDisable(false);
        btnFilter.setDisable(false);
        btnFilter.setOnAction(this::handleActionFilterSearch);

        

        //El botón informe está habilitado y visible.
        btnInforme.setDisable(false);
        btnInforme.setOnAction(this::handleButtonInformeAction);

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
        table.refresh();
        return listEntrena;

    }

    private ObservableList<Objetivo> cargarObjetivos() {
        ObservableList<Objetivo> listObjetivos;
        List<Objetivo> todosObjetivos;
        todosObjetivos = oInter.findAll_XML(new GenericType<List<Objetivo>>() {
        });
        listObjetivos = FXCollections.observableArrayList(todosObjetivos);
        return listObjetivos;

    }
    
    
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

    @FXML
    private void handleCrearButtonAction(ActionEvent event) {
        entrena = new Entrenamiento();

        entrena.setDescripcion(descripArea.getText());
        entrena.setDuracion(durCombo.getSelectionModel().getSelectedItem());
        //entrena.setFechaPeriod(fechdate.getValue());
        //entrena.setFechaPeriod(Date.from(fechdate.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        entrena.setIntensidad(intCombo.getSelectionModel().getSelectedItem());
        entrena.setRepeticion(repCombo.getSelectionModel().getSelectedItem());
        entrena.setObjetivo(objCombo.getSelectionModel().getSelectedItem());

        //Validar que los campos descripción, duración, intensidad y repeticiones y objetivo están informados.
        //Validar que los ComboBox estén seleccionados
        try {
            //Si no están informados alguno de los campos saldrá un mensaje de error.
            if (this.descripArea.getText().isEmpty() || this.durCombo.getSelectionModel().isEmpty()
                    || this.intCombo.getSelectionModel().isEmpty() || this.repCombo.getSelectionModel().isEmpty()
                    || this.objCombo.getSelectionModel().isEmpty()) {
                throw new Exception("CAMPOS NO INFORMADOS");
            }
        //Validar que el DatePicker esté informado.
        //if (this.fechdate.getValue() == null) {
        //        throw new Exception("LA FECHA NO HA SIDO SELECCIONADO");
//            }
            //Validar que el máximo número de caracteres en el campo de descripción de entrenamiento sea de 100 caracteres.      
            if (this.descripArea.getText().length() > 100) {
                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");

                } else {
                    try {
                    // tInter.create_XML(entrena);
                        fact.getFactory().create_XML(entrena);
                        listEntrena.add(entrena);
                       // listEntrena = cargarTodos();

                        throw new Exception("ENTRENAMIENTO CORRECTO");

                    } catch (Exception e) {
                    new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();

                }

            }

            
//        En caso de que todos los datos introducidos sean válidos y cumplan los requisitos mencionados anteriormente, 
//        se llama al método create_XML de la interfaz pasándole un objeto (Entrenamiento) con los valores
            
//Seguido, saldrá del método del botón.
            //En caso de error salta la excepción con su mensaje correspondiente
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();

        }

    }

    @FXML
    private void handleTrainingTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {
            Entrenamiento entrena = (Entrenamiento) newValue;

            descripArea.setText(entrena.getDescripcion());
            durCombo.setValue(entrena.getDuracion());
            fechdate.setValue(entrena.getFechaPeriod().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            intCombo.setValue(entrena.getIntensidad());
            repCombo.setValue(entrena.getRepeticion());
            objCombo.setValue(entrena.getObjetivo());

        }
    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {
        Entrenamiento entrena = new Entrenamiento();

        Entrenamiento selected = table.getSelectionModel().getSelectedItem();
        posicionEntrenamiento = selected.getIdEntrenamiento();
        entrena.setIdEntrenamiento(posicionEntrenamiento);
        
        entrena.setDescripcion(descripArea.getText());
        entrena.setDuracion(durCombo.getSelectionModel().getSelectedItem());
        //entrenamiento.setFechaPeriod(fechdate.getValue());
        entrena.setIntensidad(intCombo.getSelectionModel().getSelectedItem());
        entrena.setRepeticion(repCombo.getSelectionModel().getSelectedItem());
        entrena.setObjetivo(objCombo.getSelectionModel().getSelectedItem());

        tInter.edit_XML(entrena);
        listEntrena = cargarTodos();

    }

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
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
            //throw new Exception("EL ENTRENAMIENTO NO SE HA PODIDO ELIMINAR ");
        }

    }
    
    @FXML
    private void handleActionFilterSearch(ActionEvent event){
        
         //Object newValue = new Object();
        
        
            switch(filterCombo.getValue().toString()){
                case("Todos"):
                    cargarTodos();
                break;
                case("Duración"):
                    cargarDuracion();
                break;
                case("Intensidad"):
                    cargarIntensidad();
                break;
                case("Objetivo"):
                    cargarObjetivos();
                
            }
    
   }
    
    
 
    
    @FXML
    private void handleButtonInformeAction(ActionEvent event) {
         try {
            //LOGGER.info("Beginning printing action...");
            JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/grupo3/reto2/report/TrainingReport.jrxml"));
             
            JRBeanCollectionDataSource dataItems = new JRBeanCollectionDataSource((Collection<Entrenamiento>)this.table.getItems());
           
            Map<String,Object> parameters=new HashMap<>();
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setVisible(true);
           
        } catch (JRException ex) {
            //If there is an error show message and
            //log it.
             System.out.println("Error");
        }

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
