/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import grupo3.reto2.model.Entrenamiento;
import grupo3.reto2.model.Objetivo;
import java.util.List;
import static org.junit.Assert.*;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;

/**
 *
 * @author poker
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TrainingControllerTest extends ApplicationTest {
private Entrenamiento entrenamiento;
    
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
    private Label lblDscritp, lblDuracion, lblFecha, lblIntensidad, lblRepeticiones, lblObjetivo;

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
        btnFilter = lookup("#btnFilter").query();
        btnInforme = lookup("#btnInforme").query();
        btnAyuda = lookup("#btnAyuda").query();
        btnLogin = lookup("#btnLogin").query();

        Node menuNavegar = lookup("#menuNavegar").query();
        Node miEntrenamiento = lookup("#miEntrenamiento").query();

        table = lookup("#table").query();

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

        lblDscritp = lookup("#lblDescript").query();
        lblDuracion = lookup("#lblDuracion").query();
        lblFecha = lookup("#lblFecha").query();
        lblIntensidad = lookup("#lblIntensidad").query();
        lblRepeticiones = lookup("#lblRepeticiones").query();
        lblObjetivo = lookup("#lblObjetivo").query();
        paneAll = lookup("#paneAll").query();

    }

    @Test
    public void test1_InicioVentana() {
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
        
        Integer intensidad = intCombo.getValue();
        clickOn(descripArea);
        write("Entrenamiento de fuerza");
        
        clickOn(durCombo);
        type(KeyCode.DOWN);
        clickOn("30");
        
        clickOn(fechDate);
        write("03/02/2023");
        
        clickOn(intCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        clickOn(repCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        clickOn(objCombo);
        type(KeyCode.DOWN);
        clickOn("1");
        
        verifyThat("#btnCrear", isVisible());
        verifyThat("#btnEliminar", isVisible());
        verifyThat("#btnModificar", isVisible());
        
        clickOn(btnCrear);
        clickOn("Aceptar");
        
        
        table.getSelectionModel().select(4);
        List<Entrenamiento> entrenamiento = table.getItems();
        assertNotEquals("el entrenamiento se ha creado", entrenamiento.stream().filter(e->e.getIntensidad().equals(intensidad)).count(),4);
        //assertEquals(descripArea.getText(), "Entrenamiento de fuerza");
        
    }
    
    
    @Test
    public void test3_ModificarEntrenamiento(){
        Integer intensidad = intCombo.getValue();
        clickOn(table);
        table.getSelectionModel().select(5);        
        clickOn(descripArea);
        descripArea.clear();
        write("aaaaaaaaaaaa");
        verifyThat(btnCrear, isVisible());
        verifyThat(btnModificar, isVisible());
        verifyThat(btnEliminar, isVisible());
        clickOn(btnModificar);
        clickOn("Aceptar");
        table.getSelectionModel().select(4);
        List<Entrenamiento> entrenamiento = table.getItems();
        assertNotEquals("el entrenamiento se ha modificado", entrenamiento.stream().filter(e->e.getIntensidad().equals(intensidad)).count(),5);

    }
    
    
    @Test
    public void test4_borrarEntrenamiento() {
        Integer intensidad = intCombo.getValue();
        table.getSelectionModel().select(4);
        verifyThat(btnCrear, isVisible());
        verifyThat(btnModificar, isVisible());
        verifyThat(btnEliminar, isVisible());
        clickOn(btnEliminar);
        clickOn("Aceptar");
        table.getSelectionModel().select(4);
        List<Entrenamiento> entrenamiento = table.getItems();
        assertNotEquals("el entrenamiento se ha borrado", entrenamiento.stream().filter(e->e.getIntensidad().equals(intensidad)).count(),4);

    }

   @Test
    public void test5_FiltroTodos(){
        
        
        clickOn(filterCombo);
        type(KeyCode.DOWN);
        clickOn("Todos");
        clickOn(btnFilter);
        verifyThat(btnFilter, isEnabled());
        verifyThat(txtFilter, isEnabled());
        table.getSelectionModel().select(0);
         
        table.getSelectionModel().select(0);
        assertEquals(descripArea.getText(), "Saltar a la comba"); 
        
 
   }
    
   /*@Test
    public void test5_FiltroDuracion(){
        
        
        
        clickOn(filterCombo);
        type(KeyCode.DOWN);
        clickOn("Duracion");
        verifyThat(btnFilter, isEnabled());
        verifyThat(txtFilter, isEnabled());
        clickOn(txtFilter);
        write("30");
        clickOn(btnFilter);
        table.getSelectionModel().select(0);
        assertEquals(durCombo.toString(), "1"); 
    
    }
    
    @Test
    public void test6_FiltroIntensidad(){
        clickOn(filterCombo);
        type(KeyCode.DOWN);
        clickOn("Intensidad");
        write("5");
        clickOn(btnFilter);
        table.getSelectionModel().select(0);
        assertEquals(intCombo.toString(), "5"); 
    }
    
    @Test
    public void test7_FiltroObjetivo(){
        clickOn(filterCombo);
        type(KeyCode.DOWN);
        clickOn("Objetivo");
        write("1");
        clickOn(btnFilter);
        table.getSelectionModel().select(0);
        assertEquals(objCombo.toString(), "1"); 
    }*/
}


