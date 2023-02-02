/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.Aplication;
import grupo3.reto2.model.Objetivo;
import java.util.List;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.testfx.api.FxToolkit;
import static org.junit.Assert.assertEquals;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
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
    
    private Pane panelObjective;
    
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
        
        panelObjective = lookup("#panelObjective").query();
        
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
        txtDescriObjeti = lookup("#txtDescriObjeti").query();
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
        write("Manoloxxx");
        clickOn(txtPasswd);
        write("abcd*1234");
        clickOn(btnLogin);
        
        //Accedo a mi ventana desde la ventana principal
        clickOn("#menuNavegar");
        clickOn("#miObjetivo");
        
        //Comprobar que la ventana se abre
        verifyThat("#panelObjective", isVisible());
       
    }
    @Test
    public void test2_CrearObjetivo(){
    
        //Escribo los valores para crear el objetivo
        clickOn(txtValorParam);
        write("60Kg");
        
        clickOn(txtDescriParam);
        write("Desarrollar musculatura");
        
        clickOn(txtDescriObjeti);
        write("Quiero levantar mucho peso");
        
        //Verifico que los botones están habilitados
        verifyThat(btnCrear, isEnabled());
        verifyThat(btnModifi, isEnabled());
        verifyThat(btnDelete, isEnabled());
        
        clickOn(btnCrear);
        
        TableObjetivo.getSelectionModel().select(1);
        assertEquals(txtDescriParam.getText(), "Desarrollar musculatura");
    }
    
    @Test
    public void test3_ModificarObjetivo(){
        TableObjetivo.getSelectionModel().select(2);
        verifyThat(btnCrear, isEnabled());
        verifyThat(btnModifi, isEnabled());
        verifyThat(btnDelete, isEnabled());
        clickOn(txtValorParam);
        eraseText(4);
        write("70kg");
        clickOn(btnModifi);
        TableObjetivo.getSelectionModel().select(2);
        assertEquals(txtValorParam.getText(), "70kg");  
    }
    
    @Test
    public void test4_BorrarObjetivo(){
        
        TableObjetivo.getSelectionModel().select(2);
        verifyThat(btnCrear, isEnabled());
        verifyThat(btnModifi, isEnabled());
        verifyThat(btnDelete, isEnabled());
        clickOn(btnDelete);
        clickOn("Aceptar");
        TableObjetivo.getSelectionModel().select(2);
        assertEquals(txtValorParam.getText(), "");
           
    }
    
    @Test
    public void test5_Filtro1(){
        
        
        clickOn(cbxFiltr);
        type(KeyCode.DOWN);
        clickOn("Por valor de parámetro");
        verifyThat(btnFiltrar, isEnabled());
        verifyThat(txtFiltrarParam, isEnabled());
        clickOn(txtFiltrarParam);
        write("10km");
        clickOn(btnFiltrar);
        TableObjetivo.getSelectionModel().select(0);
        assertEquals(txtValorParam.getText(), "10km"); 
    
    }
    
    @Test
    public void test6_Filtro2(){
        
        clickOn(cbxFiltr);
        type(KeyCode.DOWN);
        clickOn("Por valor de parámetro");
        verifyThat(btnFiltrar, isEnabled());
        verifyThat(txtFiltrarParam, isEnabled());
        clickOn(txtFiltrarParam);
        eraseText(4);
        
        clickOn(cbxFiltr);
        type(KeyCode.DOWN);
        clickOn("Por clave Objetivo");
        verifyThat(btnFiltrar, isEnabled());
        verifyThat(txtFiltrarParam, isEnabled());
        clickOn(txtFiltrarParam);
        write("1");
        clickOn(btnFiltrar);
        TableObjetivo.getSelectionModel().select(0);
        assertEquals(txtClaveObjet.getText(), "1"); 
    
    }
    
    @Test
    public void test7_Filtro3(){
        clickOn(cbxFiltr);
        type(KeyCode.DOWN);
        clickOn("Todos los objetivos");
        TableObjetivo.getSelectionModel().select(0);
        assertEquals(txtClaveObjet.getText(), "1"); 
    }
}
