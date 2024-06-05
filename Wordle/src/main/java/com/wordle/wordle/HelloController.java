package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    private Button start;
    @FXML
    private Button back;
    @FXML
    private Button ranking;
    @FXML
    private Button exit;
    @FXML
    private TextField User;
    @FXML
    private Label labelLanguage;
    @FXML
    private Label labelDifficulty;

    private String[] values = {"English", "Portuguese", "French"};
    private String[] difficulty = {"1", "2", "3", "4", "5"};
    private int currentIndex = 0;
    private int currentIndex2 = 0;

    @FXML public void exitFunc ()
    {
        Platform.exit();
    }

//Troca de view (start->settings)
    @FXML
    public void startFunc() {
        try {
            // Load the second view from the FXML file
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("settings-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) start.getScene().getWindow();

            // Set the new scene
            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Troca de views (settings->start)
    @FXML
    public void backFunc() {
        try {

            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("start-view.fxml"));
            Parent root = loader.load();


            Stage stage = (Stage) back.getScene().getWindow();


            Scene scene = new Scene(root);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//Controle de Linguagem
    @FXML
    public void handleBotaoL(ActionEvent actionEvent) {

            currentIndex--;
            if(currentIndex < 0) {
             currentIndex= values.length-1;

            }
            updateLabel(labelLanguage);

    }

    @FXML
    public void handleBotaoL2(ActionEvent actionEvent) {
            currentIndex++;
            if(currentIndex > values.length-1) {
              currentIndex=0;
            }
            updateLabel(labelLanguage);

    }
//Controle de dificuldade
    @FXML
    public void handleBotaoD(ActionEvent actionEvent) {

            currentIndex2--;

            if(currentIndex2 < 0) {
            currentIndex2 = difficulty.length-1;
        }


            updateLabel(labelDifficulty);

    }
    @FXML
    public void handleBotaoD2(ActionEvent actionEvent) {

            currentIndex2++;


        if(currentIndex2 > difficulty.length -1 ) {
            currentIndex2 = 0;
        }
            updateLabel(labelDifficulty);

    }
//Atualizar Labels de dificuldade e linguagem
    private void updateLabel(Label x) {
        if (x.equals(labelLanguage)){
        x.setText(values[currentIndex]);
        }

        if (x.equals(labelDifficulty)) {
            x.setText(difficulty[currentIndex2]);
        }
    }








    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}