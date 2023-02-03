/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import grupo3.reto2.model.Lugar;
import java.util.List;
import java.util.concurrent.TimeoutException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.api.FxAssert.verifyThat;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author josu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PlaceControllerTest extends ApplicationTest {

    // private TableView table = table = lookup("#tbViewLugar").query();
    private TableView<Lugar> tblvTabla;
    private Lugar lugar;

    private Button btnCrear, btnModificar, btnSalir, btnEliminar, btnInforme, btnAyuda, btnLogin;

    private TableColumn tblcNombre, tblcDescripcion, tblcTiempo, tblcTipoLugar;

    private TextField txtNombreLugar, txtDescLugar, txtNombre, txtPasswd;

    private ComboBox cbxTipoLugar, cbxFiltroTipoLugar;

    private Menu menuNavegar;

    private MenuItem miLugar;

    private Pane paneLugar;

    private DatePicker dteTiempoReservado;

    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }

    @Before
    public void getFields() {

        btnCrear = lookup("#btnCrear").query();
        btnSalir = lookup("#btnSalir").query();
        btnEliminar = lookup("#btnEliminar").query();
        btnAyuda = lookup("#btnAyuda").query();
        btnInforme = lookup("#btnInforme").query();
        btnLogin = lookup("#btnLogin").query();
        btnModificar = lookup("#btnModificar").query();
        tblvTabla = lookup("#tblvTabla").query();
        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node miLugar = lookup("#miLugar").nth(1).query();
        dteTiempoReservado = lookup("#dteTiempoReservado").query();
        //tblvTabla = lookup("#tblvTabla").query();
/*
        Node tblcNombre = lookup("#colObjetivo").nth(1).query();
        Node colDescriObje = lookup("#colObjetivo").nth(1).query();
        Node colValParam = lookup("#colObjetivo").nth(1).query();
        Node colDescriParam = lookup("#colObjetivo").nth(1).query();
        Node colAdmin = lookup("#colObjetivo").nth(1).query();
         */
        txtNombre = lookup("#txtNombre").query();
        txtDescLugar = lookup("#txtDescLugar").query();
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();
        txtNombreLugar = lookup("#txtNombreLugar").query();

        cbxTipoLugar = lookup("#cbxTipoLugar").query();
        cbxFiltroTipoLugar = lookup("#cbxFiltroTipoLugar").query();
        paneLugar = lookup("#paneLugar").query();
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
        clickOn("#miLugar");
        verifyThat("#paneLugar", isVisible());
    }

    
    @Test
    public void test2_CrearLugar() {
        
        String NombreLugar= txtNombreLugar.getText();    
        clickOn(txtNombreLugar);
        write("polideportivodeCastro");
        clickOn(txtDescLugar);
        write("el polideportivo publico de castro");
        
        clickOn(cbxTipoLugar);
        type(KeyCode.DOWN);
        clickOn("Publico");
        
        
        clickOn(dteTiempoReservado);
        write("3/02/2023");
        clickOn(btnCrear);
        clickOn("Aceptar");
        

         List<Lugar> lugar= tblvTabla.getItems();
        assertNotEquals("el lugar se ha creado", lugar.stream().filter(l->l.getNombre().equals(NombreLugar)).count(),4);      
    }
        
        @Test
    public void test3_ModificarLugar() {
        String NombreLugar= txtNombreLugar.getText();
        tblvTabla.getSelectionModel().select(5);
        clickOn(txtNombreLugar);
        txtNombreLugar.clear();
        clickOn(txtNombreLugar);
        write("polideportivo de Castro modificado");
        clickOn(btnModificar);
        tblvTabla.getSelectionModel().select(5);
         List<Lugar> lugar= tblvTabla.getItems();
         assertNotEquals("el lugar se ha modificado", lugar.stream().filter(l->l.getNombre().equals(NombreLugar)).count(),5);
      
       
    }
    

    @Test
    public void test4_BorrarLugar(){
         String NombreLugar= txtNombreLugar.getText();
        tblvTabla.getSelectionModel().select(5);
        clickOn(btnEliminar);
        clickOn("Aceptar");
        tblvTabla.getSelectionModel().select(5);
         List<Lugar> lugar= tblvTabla.getItems();
        assertNotEquals("el lugar se ha borrado", lugar.stream().filter(l->l.getNombre().equals(NombreLugar)).count(),4);

    }
    
    
    @Test
    public void test5_filtroLugarPublico() {
        clickOn(cbxFiltroTipoLugar);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        clickOn("publico");

        tblvTabla.getSelectionModel().select(0);
        assertEquals(txtNombreLugar.getText(), "Polideportivo de Erandio");

    }

    
    @Test
    public void test6_filtroLugarPrivado() {
        clickOn(cbxFiltroTipoLugar);
        type(KeyCode.UP);
        clickOn("privado");

        tblvTabla.getSelectionModel().select(0);
        assertEquals(txtNombreLugar.getText(), "pep");

    }
        
      
}

