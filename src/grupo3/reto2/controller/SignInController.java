/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.logic.PlaceManagerFactory;
import grupo3.reto2.logic.UserFactory;
import grupo3.reto2.model.Admin;
import grupo3.reto2.model.Cliente;
import grupo3.reto2.model.Lugar;
import grupo3.reto2.model.User;
import grupo3.reto2.model.UserPrivilege;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author josu
 */
public class SignInController {

    @FXML
    private Stage stage;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPasswd;

    @FXML
    private Label lblError;

    @FXML
    private Label lblNombre;

    @FXML
    private Label lblPasswd;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    // @FXML
    //con esta sentencia en orden le estamos diciendo que tiene que tener minimo un numero una letra minuscula una mayuscula y que no puede tener espacios en blanco
    //private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{3,15}$";
    //@FXML
    // private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    @FXML
    protected static final Logger LOGGER = Logger.getLogger("/controller/SignInController");
    private UserFactory userfact = new UserFactory();

    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("SignIn");
        stage.setScene(scene);
        LOGGER.info("Llamada a los metodos y restricciones del controlador");

        txtNombre.setDisable(false);//false si quiero que este habilitado
        txtPasswd.setDisable(false);

        //El botón login (btnLogin) está habilitado.
        btnLogin.setDisable(false);

        //El botón SignUp (btnSignUp) está habilitado. 
        btnSignUp.setDisable(false);

        //Los label de nombre de usuario (lblNombre) y password (lblPasswd) son visibles.
        lblNombre.setDisable(false);
        lblPasswd.setDisable(false);

        //El foco estará puesto en el campo de nombre de usuario (txtNombre).
        txtNombre.requestFocus();

        btnLogin.setOnAction(this::handleLoginButtonAction);
        btnSignUp.setOnAction(this::handleSignUpButtonAction);

        stage.show();

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void handleLoginButtonAction(ActionEvent event) {

        try {
            // Validar que los TextField nombre de usuario y password estén informados.
            //Si no está informado alguno de los dos campos, saldrá un label de error (lblError). Seguido, saldrá del método del botón.
            if (this.txtNombre.getText().isEmpty() || this.txtPasswd.getText().isEmpty()) {

                throw new Exception("CAMPOS NO INFORMADOS ");
            }
            //Validar que el máximo número de caracteres en el campo de nombre de usuario y password sea de 15
            if (this.txtNombre.getText().length() > 15 || this.txtPasswd.getText().length() > 15) {

                throw new Exception("NUMERO CARACTERES \n INCORRECTOS");
            } else {
                //esto esta bn lo que esta mal es la referencia a la siguiente ventana 
                User user = new User();
                user.setLogin(txtNombre.getText());
                user.setPasswd(txtPasswd.getText());

                
                
                
                List<User> usersiden;
                //List<User> userprivl;
                usersiden = userfact.getFactory().findUsersByLogin_XML(new GenericType<List<User>>(){}, txtNombre.getText(), txtPasswd.getText());
                //userprivl = userfact.getFactory().findUsersByPrivilege_XML(new GenericType<List<User>>(){}, user.getPrivilege().toString());
                Admin admin = new Admin();         
                Cliente client = new Cliente();
                
                if(user.getLogin().equals("Manoloxxx") && user.getPasswd().equals("adbc*1234")){
                    user.setPrivilege(UserPrivilege.ADMIN);    
                    admin.setId(usersiden.get(0).getId());
                    admin.setPrivilege(user.getPrivilege());
                }else{
                    user.setPrivilege(UserPrivilege.CLIENT);
                    client.setId(usersiden.get(0).getId());
                    client.setPrivilege(user.getPrivilege());
                }
                
              
                //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/grupo3/reto2/view/Principal.fxml"));

                Parent root = (Parent) loader.load();

                PrincipalController princiController = ((PrincipalController) loader.getController());
                princiController.setUser(user);
                
                princiController.initiStage(root, user);
                
              
            }

        } catch (Exception e) {

            lblError.setVisible(true);
            lblError.setText(e.getMessage());
            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void handleSignUpButtonAction(ActionEvent event) {

        try {
            
            //cargar el fxml de la ventana de sign up utilizando un cargador no estatico
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/grupo3/reto2/view/SignUp.fxml"));

            Parent root = (Parent) loader.load();

            SignUpController signUpController = ((SignUpController) loader.getController());

            signUpController.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
