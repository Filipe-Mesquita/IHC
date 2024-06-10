package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class endController implements Initializable {

    @FXML
    private Label palavra;
    @FXML
    private Label num;
    @FXML
    private Button menu;
    private String word;
    private int score;

    public void setWord(String word)
    {
        this.word = word;
    }
    public void setScore(int socre){this.score = socre;}

    public void setLabel()
    {
        palavra.setText(word);
        num.setText(Integer.toString(score));
    }



    private void loadFXML(Button x,String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) x.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void menuFunc() {
        System.out.println("Voltando para o menu.");
        loadFXML(menu,"start-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
