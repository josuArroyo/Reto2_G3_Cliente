/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ale
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField txtNombre2;
    @FXML
    private Label lblNombre2;
    @FXML
    private TextField txtNombreComp;
    @FXML
    private Label lblNombreComp;
    @FXML
    private TextField txtEmail;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPasswd2;
    @FXML
    private PasswordField txtPasswd2;
    @FXML
    private Label lblConfirmPasswd;
    @FXML
    private PasswordField txtConfirmPasswd;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Label lblConfirmPasswd1;
    @FXML
    private TextField txtEmail1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
