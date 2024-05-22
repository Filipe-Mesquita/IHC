package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class   HelloController implements Initializable {
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


    @FXML
    public void playFunc(ActionEvent actionEvent) {
        try {
            // Load the second view from the FXML file
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("play-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) play.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){}
}