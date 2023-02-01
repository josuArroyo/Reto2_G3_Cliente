/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import grupo3.reto2.model.Objetivo;
import java.util.concurrent.TimeoutException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

/**
 *
 * @author Diego
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ObjectiveControllerTest extends ApplicationTest {
    
    
    private Button btnCrear, btnModifi, btnSalir, btnDelete, btnFiltrar, btnInform, btnhelp, btnLogin;
      
    private TableView<Objetivo> TableObjetivo;
     
    private TableColumn colObjetivo, colDescriObje, colValParam, colDescriParam, colAdmin;
 
    private TextField txtClaveObjet, txtValorParam, txtFiltrarParam, txtDescriParam, txtDescriObjeti, txtNombre, txtPasswd;

    private Label lblObjetivo, lblDescri, lblDescripParam, lblValorParam, lblFiltr;

    private ComboBox cbxFiltr;
    
    private Menu menuNavegar;
    
    private MenuItem miObjetivo;
    
    @BeforeClass
    public static void openWindow() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Aplication.class);
    }
    @Before
    public void getFields() {
        
        btnCrear = lookup("#btnCrear").query();
        btnModifi = lookup("#btnModifi").query();
        btnSalir = lookup("#btnSalir").query();
        btnDelete = lookup("#btnDelete").query();
        btnFiltrar = lookup("#btnFiltrar").query();
        btnInform = lookup("#btnInform").query();
        btnhelp = lookup("#btnhelp").query();
        btnLogin = lookup("#btnLogin").query();
        
        Node menuNavegar = lookup("#menuNavegar").nth(1).query();
        Node miObjetivo = lookup("#miObjetivo").nth(1).query();
        
        TableObjetivo = lookup("#TableObjetivo").query();
        
        Node colObjetivo = lookup("#colObjetivo").nth(1).query();
        Node colDescriObje = lookup("#colObjetivo").nth(1).query();
        Node colValParam = lookup("#colObjetivo").nth(1).query();
        Node colDescriParam = lookup("#colObjetivo").nth(1).query();
        Node colAdmin = lookup("#colObjetivo").nth(1).query();
        
        txtClaveObjet = lookup("#txtClaveObjet").query();
        txtValorParam = lookup("#txtValorParam").query();
        txtFiltrarParam = lookup("#txtFiltrarParam").query();
        txtDescriParam = lookup("#txtDescriParam").query();
        txtDescriObjeti = lookup("#txtDescriParam").query();
        txtNombre = lookup("#txtNombre").query();
        txtPasswd = lookup("#txtPasswd").query();
        
        lblObjetivo = lookup("#lblObjetivo").query();
        lblDescri = lookup("#lblDescri").query();
        lblDescripParam = lookup("#lblDescripParam").query();
        lblValorParam = lookup("#lblValorParam").query();
        lblFiltr = lookup("#lblFiltr").query();
        cbxFiltr = lookup("#cbxFiltr").query();

    }
    
    @Test
    public void test1_InicioVentana(){
        //Inicio sesión
        clickOn(txtNombre);
        write("ManoloGains");
        clickOn(txtPasswd);
        write("abcd*1234");
        clickOn(btnLogin);
        
        //Accedo a mi ventana desde la ventana principal
        clickOn("#menuNavegar");
        clickOn("#miObjetivo");
        
        verifyThat(txtClaveObjet, isVisible());
        verifyThat(txtValorParam, isVisible());
        verifyThat(txtFiltrarParam, isVisible());
        verifyThat(txtDescriParam, isVisible());
        verifyThat(txtDescriObjeti, isVisible());
        verifyThat(cbxFiltr, isVisible());
        verifyThat(btnCrear, isVisible());
        verifyThat(btnModifi, isVisible());
        verifyThat(btnSalir, isVisible());
        verifyThat(btnDelete, isVisible());
        verifyThat(btnFiltrar, isVisible());
        verifyThat(btnInform, isVisible());
        verifyThat(btnhelp, isVisible());
        verifyThat(TableObjetivo, isVisible());
        
    }
    
}
