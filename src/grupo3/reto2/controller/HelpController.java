/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupo3.reto2.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author josu
 */
public class HelpController {

    @FXML
    private Stage stage = new Stage();
    @FXML
    private WebView webHelp;
    
    private WebEngine engine = new WebEngine();

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    public void initStage(Parent root) {

        //init the scene with the root you got from singInController
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::shoWindow);

        this.stage.show();

    }

    @FXML
    private void shoWindow(WindowEvent event) {
        WebEngine webEngine = webHelp.getEngine();
        webEngine.load(getClass().getResource("/grupo3/reto2/report/help.html").toExternalForm());
    }

}
