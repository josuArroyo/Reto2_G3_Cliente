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
public class HelpEventController {


    Stage stage = new Stage();
    @FXML
    private WebView webView;

    public void initStage(Parent root) {

        //init the scene with the root you got from singInController
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnShowing(this::shoWindow);
        stage.setTitle("Manual de ventana de eventos");

        stage.show();

    }

    @FXML
    private void shoWindow(WindowEvent event) {
        WebEngine webEngine = webView.getEngine();
        webEngine.load(getClass().getResource("/grupo3/reto2/view/Eventohelp.html").toExternalForm());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


}