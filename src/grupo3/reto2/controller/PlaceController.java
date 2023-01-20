/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

    public void initStage(Parent root) {

        // LOGGER.info("Initializing Place stage");      
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

        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

}
