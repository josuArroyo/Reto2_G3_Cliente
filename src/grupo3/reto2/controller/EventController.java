/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.entities.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ale
 */
public class EventController extends GenericController {

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnInforme;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSubscribir;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextField tfTipoEvento;

    @FXML
    private TextField tfPremio;

    @FXML
    private TextField tfIdLugar;

    @FXML
    private ComboBox cbFiltroTipoEvento;

    @FXML
    private ComboBox cbFiltroIdEvento;

    @FXML
    private TableView tvTablaEvento;

    @FXML
    private TableColumn tcIdEvento;

    @FXML
    private TableColumn tcDescripcion;

    @FXML
    private TableColumn tcFecha;

    @FXML
    private TableColumn tcPremio;

    @FXML
    private TableColumn tcTipoEvento;

    @FXML
    private TableColumn tcIdLugar;

    private ObservableList<Evento> eventsData;

    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Gestión de eventos");
        stage.setResizable(false);
        stage.setOnShowing(this::vistaVentana);

        tfDescripcion.textProperty().addListener(this::cambioDeTexto);
        dpFecha.promptTextProperty().addListener(this::cambioDeTexto);
        tfTipoEvento.textProperty().addListener(this::cambioDeTexto);
        tfPremio.textProperty().addListener(this::cambioDeTexto);
        tfIdLugar.textProperty().addListener(this::cambioDeTexto);
        btnModificar.setOnAction(this::modificar);
        btnSalir.setOnAction(this::salir);
        btnSubscribir.setOnAction(this::subscribirse);
        btnCrear.setOnAction(this::crear);
        btnBorrar.setOnAction(this::borrar);
        btnInforme.setOnAction(this::informe);

        HBox hboxMenu = (HBox) root.getChildrenUnmodifiable().get(0);
        MenuBar menuBar = (MenuBar) hboxMenu.getChildren().get(0);
        Menu menuHelp = menuBar.getMenus().get(1);
        menuHelp.showingProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue) {
                menuHelp.getItems().get(0).fire();
            }
        });

        tcIdEvento.setCellFactory(new PropertyValueFactory<>("Id del evento"));
        tcDescripcion.setCellFactory(new PropertyValueFactory<>("Descripcion"));
        tcFecha.setCellFactory(new PropertyValueFactory<>("Fecha"));
        tcPremio.setCellFactory(new PropertyValueFactory<>("Premio"));
        tcTipoEvento.setCellFactory(new PropertyValueFactory<>("Tipo de evento"));
        tcIdLugar.setCellFactory(new PropertyValueFactory<>("Id del lugar"));

        ObservableList<Evento> tiposDeEvento = FXCollections.observableArrayList();
        cbFiltroTipoEvento.setItems(tiposDeEvento);
        ObservableList<Evento> idsEventos = FXCollections.observableArrayList();
        cbFiltroIdEvento.setItems(idsEventos);
        eventsData = FXCollections.observableArrayList();

        tvTablaEvento.setItems(eventsData);
        stage.show();
    }
    
    public void setStage (Stage stage) {
        this.stage = stage;
    }

    private void vistaVentana(WindowEvent event) {

        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnBorrar.setDisable(true);
        btnSubscribir.setDisable(true);

        tfDescripcion.setPromptText("De que trata el evento...");
        dpFecha.setPromptText("Fecha de realización del evento...");
        tfTipoEvento.setPromptText("Que de evento va a ser...");
        tfPremio.setPromptText("Cuál es el premio al ganador del evento...");
        tfIdLugar.setPromptText("Id del lugar en el que se realizará el evento...");
    }

    private void cambioDeTexto(ObservableValue observable, String oldValue, String newValue) {
        try {
            String fecha;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fecha = sdf.format(sdf.parse(this.dpFecha.getValue().toString()));
            if (tfDescripcion.getText().trim().isEmpty() || tfTipoEvento.getText().trim().isEmpty() || fecha.isEmpty() || tfPremio.getText().trim().isEmpty() || tfIdLugar.getText().trim().isEmpty()) {
                btnCrear.setDisable(true);
                btnModificar.setDisable(true);
                btnBorrar.setDisable(true);
            } else {
                btnCrear.setDisable(false);
                btnModificar.setDisable(false);
                btnBorrar.setDisable(false);
            }
        } catch (ParseException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modificar(ActionEvent event) {

    }

    @FXML
    private void salir(ActionEvent event) {

    }

    @FXML
    private void subscribirse(ActionEvent event) {

    }

    private void crear(ActionEvent event) {

    }

    private void borrar(ActionEvent event) {

    }

    private void informe(ActionEvent event) {

    }
}
