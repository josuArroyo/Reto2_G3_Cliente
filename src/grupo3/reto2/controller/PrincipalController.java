/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import grupo3.reto2.model.Admin;
import grupo3.reto2.model.Cliente;
import grupo3.reto2.model.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class PrincipalController {
    Stage stage = new Stage();

    private User user;

    

    public void initiStage(Parent root, User user) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //La ventana es modal
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Principal");
        stage.show();
        System.out.println(user.getId());
        System.out.println(user.getPrivilege());
        
    }  

    public void setUser(User user) {
        this.user = user;
    }
    

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
