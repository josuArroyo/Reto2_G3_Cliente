/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.logic.PlaceManagerFactory;
import grupo3.reto2.model.Lugar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * @author 2dam
 */
public class PlaceController {

    @FXML
    private Stage stage;

    @FXML
    private TextField txtNombreLugar;

    @FXML
    private TextField txtDescLugar;

    @FXML
    private Label lblNombreLugar;

    @FXML
    private Label lblDescLugar;

    @FXML
    private Label lblTiempoReservado;

    @FXML
    private Label lblTipoLugar;

    @FXML
    private Label label;

    @FXML
    private TableView<Lugar> tblvTabla;

    @FXML
    private DatePicker dteTiempoReservado;

    @FXML
    private Button btnCrear;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnInforme;

    @FXML
    private ComboBox cbxTipoLugar;

    @FXML
    private ComboBox<String> cbxFiltroTipoLugar;

    @FXML
    private TableColumn tblcNombre;

    @FXML
    private TableColumn tblcDescripcion;

    @FXML
    private TableColumn tblcTiempo;

    @FXML
    private TableColumn tblcTipoLugar;

    Integer index;
    Lugar lugar = new Lugar();
    int posicion;
    private PlaceManagerFactory placefact = new PlaceManagerFactory();

    private ObservableList<Lugar> placeData;
    private ObservableList patata;
    Alert ventanita = new Alert(Alert.AlertType.ERROR);

    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/PlaceController");

    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        stage.setScene(scene);

        //habilitamos los txt
        txtNombreLugar.setDisable(false);
        txtDescLugar.setDisable(false);

        //habilitamos los botonoes 
        btnCrear.setDisable(false);
        btnEliminar.setDisable(false);
        btnModificar.setDisable(false);
        btnInforme.setDisable(false);
        btnSalir.setDisable(false);

        //habilitamos las combo box
        cbxTipoLugar.setDisable(false);
        cbxFiltroTipoLugar.setDisable(false);

        //habilitamos la tabla
        tblvTabla.setDisable(false);

        //habilitamos los labels 
        lblNombreLugar.setDisable(false);
        lblDescLugar.setDisable(false);
        lblTiempoReservado.setDisable(false);
        lblTipoLugar.setDisable(false);

        //habilitamos el DatePicker
        dteTiempoReservado.setDisable(false);

        //El foco estará puesto en el campo de nombre de usuario (txtNombre).
        txtNombreLugar.requestFocus();

        //El título de la ventana es “Sign In”.
        stage.setTitle("PLACE");

        //La ventana no es redimensionable
        stage.setResizable(false);

        //Metodos de botones
        btnSalir.setOnAction(this::handleExitButtonAction);
        btnCrear.setOnAction(this::handleCrearButtonAction);
        btnInforme.setOnAction(this::handleInformeButtonAction);
        btnEliminar.setOnAction(this::handleEliminarButtonAction);
        btnModificar.setOnAction(this::handleModificarButtonAction);
        tblvTabla.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);
        cbxFiltroTipoLugar.valueProperty().addListener(this::handleFiltradoTipoLugar);
        //cargar combobox
        cbxTipoLugar.getItems().addAll("privado", "publico");
        cbxFiltroTipoLugar.getItems().addAll("privado", "publico", "ninguno");

        tblcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tblcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tblcTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
        tblcTipoLugar.setCellValueFactory(new PropertyValueFactory<>("tipoLugar"));

        placeData = FXCollections.observableArrayList(placefact.getFactory().findAll_XML(new GenericType<List<Lugar>>() {
        }));
        //Set table model.
        tblvTabla.setItems(placeData);

        ///////
        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private ObservableList<Lugar> cargarTodo() {
        ObservableList<Lugar> listaLugar;
        List<Lugar> todosLugares;
        todosLugares = placefact.getFactory().findAll_XML(new GenericType<List<Lugar>>() {
        });

        listaLugar = FXCollections.observableArrayList(todosLugares);
        tblvTabla.setItems(listaLugar);
        tblvTabla.refresh();
        return listaLugar;
    }

    @FXML
    private ObservableList<Lugar> cargarFiltro() {
        ObservableList<Lugar> listaLugar;
        List<Lugar> todosLugares;
        todosLugares = placefact.getFactory().findByType_XML(new GenericType<List<Lugar>>() {
        }, cbxFiltroTipoLugar.getSelectionModel().getSelectedItem());

        listaLugar = FXCollections.observableArrayList(todosLugares);
        tblvTabla.setItems(listaLugar);
        tblvTabla.refresh();
        return listaLugar;
    }

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
    private void handleUsersTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {

        if (newValue != null) {

            Lugar lugar = (Lugar) newValue;
            txtNombreLugar.setText(lugar.getNombre());
            txtDescLugar.setText(lugar.getDescripcion());
            cbxTipoLugar.getSelectionModel().select(lugar.getTipoLugar());

        }

    }

    @FXML
    private void handleCrearButtonAction(ActionEvent event) {

        if (this.txtNombreLugar.getText().isEmpty() || this.txtDescLugar.getText().isEmpty() || this.cbxTipoLugar.getSelectionModel().getSelectedItem().toString().isEmpty() || dteTiempoReservado.getValue() == null) {

            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("nos has introducido datos en uno de los campos");
            Optional<ButtonType> action = ventanita.showAndWait();
            if (action.get() == ButtonType.OK) {
                txtNombreLugar.setText("");
                txtDescLugar.setText("");
                cbxTipoLugar.setItems(placeData);
            }

        }
        if (this.txtNombreLugar.getText().length() > 10 || this.txtDescLugar.getText().length() > 100) {

            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("no has introducido el numero de carracteres correcto");
            Optional<ButtonType> action = ventanita.showAndWait();
        }

        lugar.setNombre(txtNombreLugar.getText());
        lugar.setDescripcion(txtDescLugar.getText());
        lugar.setTipoLugar(cbxTipoLugar.getSelectionModel().getSelectedItem().toString());
        //lugar.setTiempo(dteTiempoReservado.getValue().to);
        placefact.getFactory().create_XML(lugar);
        placeData = FXCollections.observableArrayList(cargarTodo());

    }

    @FXML
    private void handleInformeButtonAction(ActionEvent event) {

        try {
            JasperReport report
                    = JasperCompileManager.compileReport(getClass().getResourceAsStream("/Reto2_G3_Cliente/src/grupo3/reto2/report/PlaceReport.jrxml"));

            JRBeanCollectionDataSource dataItems
                    = new JRBeanCollectionDataSource((Collection<Lugar>) this.tblvTabla.getItems());
            Map<String, Object> parameters = new HashMap<>();
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataItems);
            JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
            jasperViewer.setVisible(true);

        } catch (JRException ex) {

            System.out.println("no funciona");

        }

    }

    @FXML
    private void handleEliminarButtonAction(ActionEvent event) {

        Lugar selectedLugar = (Lugar) tblvTabla.getSelectionModel().getSelectedItem();
        placefact.getFactory().remove(selectedLugar.getIdLugar().toString());
        placeData = FXCollections.observableArrayList(cargarTodo());

    }

    @FXML
    private void handleModificarButtonAction(ActionEvent event) {

        lugar.setIdLugar(tblvTabla.getSelectionModel().getSelectedItem().getIdLugar());
        lugar.setNombre(txtNombreLugar.getText());
        lugar.setDescripcion(txtDescLugar.getText());
        lugar.setTipoLugar(cbxTipoLugar.getSelectionModel().getSelectedItem().toString());
        //lugar.setTiempo(dteTiempoReservado.getValue().to);

        if (this.txtNombreLugar.getText().isEmpty() || this.txtDescLugar.getText().isEmpty() || this.cbxTipoLugar.getSelectionModel().getSelectedItem().toString().isEmpty() || dteTiempoReservado.getValue() == null) {

            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("nos has introducido datos en uno de los campos");
            Optional<ButtonType> action = ventanita.showAndWait();
            if (action.get() == ButtonType.OK) {
                txtNombreLugar.setText("");
                txtDescLugar.setText("");
                cbxTipoLugar.setItems(placeData);
            }

        }
        if (this.txtNombreLugar.getText().length() > 10 || this.txtDescLugar.getText().length() > 100) {

            ventanita.setHeaderText(null);
            ventanita.setTitle("Error");
            ventanita.setContentText("no has introducido el numero de carracteres correcto");
            Optional<ButtonType> action = ventanita.showAndWait();
        }

        placefact.getFactory().edit_XML(lugar);
        placeData = FXCollections.observableArrayList(cargarTodo());

    }

    @FXML
    private void handleFiltradoTipoLugar(ObservableValue observable, Object oldValue, Object newValue) {

        switch (cbxFiltroTipoLugar.getValue()) {
            case ("publico"):
                cargarFiltro();
                break;
            case ("privado"):
                cargarFiltro();
                break;
            case ("ninguno"):
                cargarTodo();
        }

    }

}