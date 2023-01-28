/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.entities.Evento;
import grupo3.reto2.entities.Lugar;
import grupo3.reto2.entities.User;
import grupo3.reto2.logic.EventManagerFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Ale
 */
public class EventController extends GenericController {

    private EventManagerFactory emf = new EventManagerFactory();

    private Evento e = new Evento();

    private User user = new User();

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Button btnInforme;

    @FXML
    private Button btnCerrar;

    @FXML
    private Button btnSubscribir;

    @FXML
    private Button btnBuscar;

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
    private TextField tfFiltro;

    @FXML
    private ComboBox cbFiltro;

    @FXML
    private TableView<Evento> tvTablaEvento;

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

        LOGGER.info("Iniciando método init stage");
        Scene scene = new Scene(root);
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.setTitle("Gestión de eventos");
        stage.setResizable(false);
        stage.setOnShowing(this::vistaVentana);

        tfDescripcion.textProperty().addListener(this::cambioDeTexto);
        btnModificar.setOnAction(this::modificar);
        dpFecha.promptTextProperty().addListener(this::cambioDeTexto);
        tfTipoEvento.textProperty().addListener(this::cambioDeTexto);
        tfPremio.textProperty().addListener(this::cambioDeTexto);
        tfIdLugar.textProperty().addListener(this::cambioDeTexto);

        btnCerrar.setOnAction(this::salir);
        btnCrear.setOnAction(this::crear);
        btnBuscar.setOnAction(this::buscar);
        /*
        btnSubscribir.setOnAction(this::subscribirse);
         */
        btnBorrar.setOnAction(this::borrar);
        btnInforme.setOnAction(this::informe);

        tvTablaEvento.getSelectionModel().selectedItemProperty().addListener(this::handleTableSelection);
        tcIdEvento.setCellValueFactory(new PropertyValueFactory<>("idEvento"));
        tcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcPremio.setCellValueFactory(new PropertyValueFactory<>("premio"));
        tcTipoEvento.setCellValueFactory(new PropertyValueFactory<>("tipoEvento"));
        tcIdLugar.setCellValueFactory(new PropertyValueFactory<>("lugar"));

        eventsData = FXCollections.observableArrayList(emf.getFactory().viewEvents_XML(new GenericType<List<Evento>>() {
        }));

        tvTablaEvento.setItems(eventsData);

        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void vistaVentana(WindowEvent event) {
        LOGGER.info("Iniciando método vista ventana");
        btnCrear.setDisable(true);
        btnModificar.setDisable(true);
        btnBorrar.setDisable(true);
        btnSubscribir.setDisable(true);

        tfDescripcion.setPromptText("De que trata el evento...");
        dpFecha.setValue(LocalDate.of(2023, Month.JANUARY, 1));
        tfTipoEvento.setPromptText("Que de evento va a ser...");
        tfPremio.setPromptText("Cuál es el premio al ganador del evento...");
        tfIdLugar.setPromptText("Id del lugar en el que se realizará el evento...");
        cbFiltro.getItems().addAll(
                "Por tipo de evento",
                "Por id de evento"
        );
    }

    @FXML
    private void cambioDeTexto(ObservableValue observable, String oldValue, String newValue) {
        String fecha = dpFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (tfDescripcion.getText().trim().isEmpty() || tfTipoEvento.getText().trim().isEmpty() || fecha.isEmpty() || tfPremio.getText().trim().isEmpty() || tfIdLugar.getText().trim().isEmpty()) {
            btnCrear.setDisable(true);
            btnModificar.setDisable(true);
            btnBorrar.setDisable(true);
        } else {
            btnCrear.setDisable(false);
            btnModificar.setDisable(false);
            btnBorrar.setDisable(false);

        }
    }

    @FXML
    private void handleTableSelection(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {
            try {
                Evento e = (Evento) newValue;
                tfDescripcion.setText(e.getDescripcion());
                tfTipoEvento.setText(e.getTipoEvento());
                tfPremio.setText(e.getPremio());
                tfIdLugar.setText(e.getLugar().toString());
                String fecha = dpFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                date = e.getFecha();
                //pruebas peligrosas
            } catch (ParseException ex) {
                Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /*   
    @FXML
    private void subscribirse(ActionEvent event) {

    }
     */
    @FXML
    private void modificar(ActionEvent event) {
        try {
            Lugar l = new Lugar();
            int lug = Integer.parseInt(tfIdLugar.getText());
            l.setIdLugar(lug);

            String fecha = dpFecha.getValue().toString();
            Date fech;
            fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);

            e.setIdEvento(tvTablaEvento.getSelectionModel().getSelectedItem().getIdEvento());
            e.setDescripcion(tfDescripcion.getText());
            e.setFecha(fech);
            e.setLugar(l);
            e.setPremio(tfPremio.getText());
            e.setTipoEvento(tfTipoEvento.getText());

            emf.getFactory().modifyEvent_XML(e);
            eventsData = FXCollections.observableArrayList(cargarDatos());
        } catch (ParseException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert ventanaConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        ventanaConfirmacion.setHeaderText(null);
        ventanaConfirmacion.setTitle("Advertencia");
        ventanaConfirmacion.setContentText("¿Deseas salir?");
        Optional<ButtonType> accion = ventanaConfirmacion.showAndWait();

        if (accion.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            ventanaConfirmacion.close();
        }
    }

    private void crear(ActionEvent event) {

        try {
            Lugar l = new Lugar();
            int lug = Integer.parseInt(tfIdLugar.getText());
            l.setIdLugar(lug);

            String fecha = dpFecha.getValue().toString();
            Date fech;
            fech = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            e.setDescripcion(tfDescripcion.getText());
            e.setFecha(fech);
            e.setLugar(l);
            e.setPremio(tfPremio.getText());
            e.setTipoEvento(tfTipoEvento.getText());

            emf.getFactory().createEvent_XML(e);
            eventsData = FXCollections.observableArrayList(cargarDatos());
        } catch (ParseException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private ObservableList<Evento> cargarDatos() {
        ObservableList<Evento> listEvento;
        List<Evento> todosEventos;

        todosEventos = emf.getFactory().viewEvents_XML(new GenericType<List<Evento>>() {});
        listEvento = FXCollections.observableArrayList(todosEventos);
        tvTablaEvento.setItems(listEvento);
        tvTablaEvento.refresh();
        return listEvento;
    }

    private void buscar(ActionEvent event) {
        Object newValue = new Object();
        switch (cbFiltro.getValue().toString()) {
            case ("Por tipo de evento") :
                emf.getFactory().findEventByType_XML(new GenericType<List<Evento>>() {}, tfFiltro.getText());
                eventsData = FXCollections.observableArrayList(cargarFiltroTipoEvento());
                break;
            
            case ("Por id de evento") :
                emf.getFactory().findEventByEventId_XML(new GenericType<List<Evento>>() {}, tfFiltro.getText());
                eventsData = FXCollections.observableArrayList(cargarFiltroIdEvento());
                break;
        }
    }

    @FXML
    private ObservableList<Evento> cargarFiltroIdEvento() {
        ObservableList<Evento> listEvento;
        List<Evento> todosEventos;

        todosEventos = emf.getFactory().findEventByEventId_XML(new GenericType<List<Evento>>() {
        }, tfFiltro.getText());
        listEvento = FXCollections.observableArrayList(todosEventos);
        tvTablaEvento.setItems(listEvento);
        tvTablaEvento.refresh();
        return listEvento;
    }

    @FXML
    private ObservableList<Evento> cargarFiltroTipoEvento() {
        ObservableList<Evento> listEvento;
        List<Evento> todosEventos;

        todosEventos = emf.getFactory().findEventByType_XML(new GenericType<List<Evento>>() {
        }, tfFiltro.getText());
        listEvento = FXCollections.observableArrayList(todosEventos);
        tvTablaEvento.setItems(listEvento);
        tvTablaEvento.refresh();
        return listEvento;
    }

    private void borrar(ActionEvent event) {
        Evento es = tvTablaEvento.getSelectionModel().getSelectedItem();
        emf.getFactory().deleteEvent(es.getIdEvento().toString());
        eventsData = FXCollections.observableArrayList(cargarDatos());
    }

    private void informe(ActionEvent event) {

    }
}
