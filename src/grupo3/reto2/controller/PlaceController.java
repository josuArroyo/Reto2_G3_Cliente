/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.logic.PlaceManager;
import grupo3.reto2.logic.PlaceManagerFactory;
import grupo3.reto2.model.Lugar;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;

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
    private TableView tblvTabla;

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
    private ComboBox cbxFiltroTipoLugar;

    @FXML
    private TableColumn tblcNombre;

    @FXML
    private TableColumn tblcDescripcion;

    @FXML
    private TableColumn tblcTiempo;

    @FXML
    private TableColumn tblcTipoLugar;

    Integer index;

    private PlaceManagerFactory placefact = new PlaceManagerFactory();

    private ObservableList<Lugar> placeData;

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
        tblvTabla.getSelectionModel().selectedItemProperty().addListener(this::handleUsersTableSelectionChanged);

        try {
            tblcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            tblcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            tblcTiempo.setCellValueFactory(new PropertyValueFactory<>("tiempo"));
            tblcTipoLugar.setCellValueFactory(new PropertyValueFactory<>("tipoLugar"));

            placeData = FXCollections.observableArrayList(placefact.getFactory().findAll_XML(new GenericType<List<Lugar>>() {
            }));
            //Set table model.
            tblvTabla.setItems(placeData);
        } catch (Exception e) {

        }

        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
            //pruebas peligrosas
           
        }

    }

    @FXML
    private void handleCrearButtonAction(ActionEvent event) {

    }

}
