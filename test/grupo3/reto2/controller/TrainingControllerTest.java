/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import grupo3.reto2.model.Entrenamiento;
import grupo3.reto2.model.Objetivo;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.matcher.control.TextInputControlMatchers;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;



/**
 *
 * @author poker
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrainingControllerTest extends ApplicationTest{
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
    private Pane paneAll;

    @FXML
    private Button btnCrear, btnModificar, btnEliminar, btnInforme, btnCerrar, btnAyuda, btnLogin;

    @FXML
    private Label lblDscript, lblDuracion, lblFecha, lblIntensidad, lblRepeticiones, lblObjetivo;

    @FXML
    private TableView<Entrenamiento> table;
    
     @FXML
    private TableColumn tcDescrip, tcDuracion, tcDate, tcIntensidad, tcRepet, tcObjetivo;
     
     @FXML
     private TextField txtNombre, txtPasswd;
     
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }
    
    @Before
    public void getFields() {
        btnCrear = lookup("#btnCrear").query();
        btnModificar = lookup("#btnModificar").query();
        btnCerrar = lookup("#btnCerrar").query();
        btnEliminar = lookup("#btnEliminar").query();
        btnFilter = lookup("#btnFiltrar").query();
        btnInforme = lookup("#btnInforme").query();
        btnAyuda = lookup("#btnAyuda").query();
        btnLogin = lookup("#btnLogin").query();
        
        Node menuNavegar = lookup("#menuNavegar").query();
        Node miEntrenamiento = lookup("#miEntrenamiento").query();
        
        table = lookup("#TableEntrenamiento").query();
        
        txtFilter = lookup("#txtFilter").query();
        descripArea = lookup("#descripArea").query();
        durCombo = lookup("#durCombo").query();
        intCombo = lookup("#intCombo").query();
        repCombo = lookup("#repCombo").query();
        objCombo = lookup("#objCombo").query();
        filterCombo = lookup("#filterCombo").query();
        fechDate = lookup("#fechDate").query();
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();
        
        
        lblDscript = lookup("#lblDescript").query();
        lblDuracion = lookup("#lblDuracion").query();
        lblFecha = lookup("#lblFecha").query();
        lblIntensidad = lookup("#lblIntensidad").query();
        lblRepeticiones = lookup("#lblRepeticiones").query();
        lblObjetivo = lookup("#lblObjetivo").query();
        paneAll = lookup("#paneAll").query();
     
    }
    
    
    @Test
    public void test1_InicioVentana(){
       //Inicio sesión
        clickOn(txtNombre);
        write("Manoloxxx");
        clickOn(txtPasswd);
        write("abcd*1234");
        clickOn(btnLogin);
        
        //Accedo a mi ventana desde la ventana principal
        clickOn("#menuNavegar");
        clickOn("#miEntrenamiento");
        
        verifyThat("#paneAll", isVisible());

    }
    
    @Test
    public void test2_CrearEntrenamiento(){
        clickOn(descripArea);
        write("Entrenamiento de resistencia");
        
        clickOn(durCombo);
        type(KeyCode.DOWN);
        clickOn("30");
        
        clickOn(fechDate);
        write("3/02/2023");
        
        clickOn(intCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        clickOn(repCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        clickOn(objCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        clickOn(btnCrear);
        //clickOn("Aceptar");
        
        table.getSelectionModel().select(2);
        assertEquals(descripArea.getText(), "Entrenamiento de resistencia");
        
    }
    
    
   
    
}
