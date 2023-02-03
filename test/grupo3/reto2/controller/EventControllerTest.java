/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import org.junit.Test;
import static org.junit.Assert.*;
import grupo3.reto2.model.Evento;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.ListViewMatchers.isEmpty;
import org.testfx.matcher.control.TextInputControlMatchers;

/**
 *
 * @author Ale
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventControllerTest extends ApplicationTest {

    private Button btnLogin, btnCrear, btnModificar, btnBorrar, btnInforme, btnCerrar, btnSubscribir, btnBuscar, btnhelp;

    private TableView<Evento> tvTablaEvento;

    private TableColumn tcIdEvento, tcDescripcion, tcFecha, tcPremio, tcTipoEvento, tcIdLugar;

    private TextField txtNombre, txtPasswd, tfDescripcion, tfTipoEvento, tfPremio, tfIdLugar, tfFiltro;

    private DatePicker dpFecha;

    private Label lblDescripcion, lblFecha, lblTipoEvento, lblPremio, lblIdLugar, lblfiltro;

    private ComboBox cbFiltro;

    private Menu menuNavegar;

    private MenuItem miEvento;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }

    @Before
    public void getFields() {

        btnCrear = lookup("#btnCrear").query();
        btnModificar = lookup("#btnModifiar").query();
        btnCerrar = lookup("#btnCerrar").query();
        btnBorrar = lookup("#btnBorrar").query();
        btnBuscar = lookup("#btnBuscar").query();
        btnInforme = lookup("#btnInforme").query();
        btnhelp = lookup("#btnhelp").query();
        btnSubscribir = lookup("#btnSubscribir").query();
        btnLogin = lookup("#btnLogin").query();

        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node miEvento = lookup("#miEvento").nth(1).query();

        tvTablaEvento = lookup("#tvTablaEvento").query();

        Node tcIdEvento = lookup("#tcIdEvento").nth(1).query();
        Node tcDescripcion = lookup("#tcDescripcion").nth(1).query();
        Node tcFecha = lookup("#tcFecha").nth(1).query();
        Node tcPremio = lookup("#tcPremio").nth(1).query();
        Node tcTipoEvento = lookup("#tcTipoEvento").nth(1).query();
        Node tcIdLugar = lookup("#tcIdLugar").nth(1).query();

        tfDescripcion = lookup("#tfDescripcion").query();
        tfTipoEvento = lookup("#tfTipoEvento").query();
        tfPremio = lookup("#tfPremio").query();
        tfIdLugar = lookup("#tfIdLugar").query();
        tfFiltro = lookup("#tfFiltro").query();
        dpFecha = lookup("#dpFecha").query();
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();

        lblDescripcion = lookup("#lblDescripcion").query();
        lblFecha = lookup("#lblFecha").query();
        lblTipoEvento = lookup("#lblTipoEvento").query();
        lblPremio = lookup("#lblPremio").query();
        lblIdLugar = lookup("#lblIdLugar").query();
        lblfiltro = lookup("#lblfiltro").query();
        cbFiltro = lookup("#cbFiltro").query();

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
        clickOn("#miEvento");

    }

    @Test
    public void test2_Crear() {
        clickOn(tfDescripcion);
        write("Competicion de remo");
        clickOn(dpFecha);
        eraseText(10);
        write("07/03/2023");
        clickOn(tfTipoEvento);
        write("remo");
        clickOn(tfPremio);
        write("ascenso");
        clickOn(tfIdLugar);
        write("1");
        verifyThat("#btnCrear", isVisible());
        verifyThat("#btnBorrar", isVisible());
        verifyThat("#btnModificar", isVisible());

        clickOn("#btnCrear");
        tvTablaEvento.getSelectionModel().select(4);

    }

    @Test
    public void test3_Modificar() {
        clickOn("ascenso");
        verifyThat("#btnCrear", isVisible());
        verifyThat("#btnBorrar", isVisible());
        verifyThat("#btnModificar", isVisible());
        clickOn(dpFecha);
        eraseText(10);
        write("06/03/2023");

        clickOn("#btnModificar");

        tvTablaEvento.getSelectionModel().select(4);
    }

    @Test
    public void test4_Borrar() {
        clickOn("Competicion de remo");
        verifyThat("#btnCrear", isVisible());
        verifyThat("#btnBorrar", isVisible());
        verifyThat("#btnModificar", isVisible());

        clickOn("#btnBorrar");        

        tvTablaEvento.getSelectionModel().select(4);
    }
    
    @Test
    public void test5_FalloCrear() {
        clickOn(tfDescripcion);
        write("Competicion de remo");
        clickOn(dpFecha);
        eraseText(10);
        write("07/03/2023");
        clickOn(tfTipoEvento);
        write("1212");
        clickOn(tfPremio);
        write("ascenso");
        clickOn(tfIdLugar);
        write("Flexiones");
        verifyThat("#btnCrear", isVisible());
        verifyThat("#btnBorrar", isVisible());
        verifyThat("#btnModificar", isVisible());

        clickOn("#btnCrear");

        clickOn("Aceptar");
        tvTablaEvento.getSelectionModel().select(4);
    }
    
    @Test
    public void test6_FiltroId() {
        clickOn(cbFiltro);
        type(KeyCode.DOWN);
        clickOn("Filtrar por Id de evento");
        verifyThat("#btnBuscar", isVisible());
        verifyThat("#tfFiltro", isVisible());
        clickOn("#tfFiltro");
        write("2");
        clickOn("#btnBuscar");
        tvTablaEvento.getSelectionModel().select(0);
        assertEquals(tfDescripcion.getText(), "Partido entre el Arenas y el Plentzia");

        clickOn("#tfFiltro");
        tfFiltro.clear();
        write("Alitas");
        clickOn("#btnBuscar");
        
        clickOn("Aceptar");
        verifyThat("#tfFiltro", isEmpty());
    }
    
    @Test
    public void test7_FiltroTipo() {
        clickOn(cbFiltro);
        type(KeyCode.DOWN);
        clickOn("Filtrar por tipo de evento");
        verifyThat("#btnBuscar", isVisible());
        verifyThat("#tfFiltro", isVisible());
        clickOn("#tfFiltro");
        write("BalonMano");
        clickOn("#btnBuscar");
        tvTablaEvento.getSelectionModel().select(0);
        assertEquals(tfDescripcion.getText(), "Partido entre el Arenas y el Leioa");

        clickOn("#tfFiltro");
        tfFiltro.clear();
        write("12");
        clickOn("#btnBuscar");
        
        clickOn("Aceptar");
        verifyThat("#tfFiltro", isEmpty());
    }
}
