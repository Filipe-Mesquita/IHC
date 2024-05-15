package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Button play;
    @FXML
    private Button ranking;
    @FXML
    private Button exit;

    @FXML public void exitFunc (ActionEvent actionEvent)
    {
        Platform.exit();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb){}
}