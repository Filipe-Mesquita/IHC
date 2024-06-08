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
import javafx.scene.layout.GridPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Label madeBy;
    @FXML
    private Button start;
    @FXML
    private Button back;
    @FXML
    private Button play;
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
    @FXML
    private GridPane dynamicGrid;  // Referência ao GridPane

    private final String[] values = {"English", "Portuguese", "French"};
    private final String[] difficulty = {"1", "2", "3", "4", "5"};
    private int currentIndex = 0;
    private int currentIndex2 = 0;



    @FXML public void exitFunc() {
        Platform.exit();
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
    public void startFunc() {
        System.out.println("Ir pros settings "); // Debugging line
        loadFXML(start,"Settings-view.fxml");
    }

    @FXML
    public void backFunc() {
        System.out.println("Voltando para o início."); // Debugging line
        loadFXML(back,"start-view.fxml");
    }

    @FXML
    public void playFunc() {

        if(currentIndex2 == 0){

            loadFXML(play, "game1-view.fxml");
        }


        if(currentIndex2 == 1){

            loadFXML(play,"game2-view.fxml");
        }

        if(currentIndex2 == 2){

            loadFXML(play,"game3-view.fxml");
        }

        if(currentIndex2 == 3){

            loadFXML(play,"game4-view.fxml");
        }

        if(currentIndex2 == 4){

            loadFXML(play,"game5-view.fxml");
        }



    }





    @FXML
    public void handleBotaoL(ActionEvent actionEvent) {
        currentIndex--;
        if (currentIndex < 0) {
            currentIndex = values.length - 1;
        }
        System.out.println("Idioma selecionado: " + values[currentIndex]); // Debugging line
        updateLabel(labelLanguage);
    }

    @FXML
    public void handleBotaoL2(ActionEvent actionEvent) {
        currentIndex++;
        if (currentIndex > values.length - 1) {
            currentIndex = 0;
        }
        System.out.println("Idioma selecionado: " + values[currentIndex]); // Debugging line
        updateLabel(labelLanguage);
    }

    @FXML
    public void handleBotaoD(ActionEvent actionEvent) {
        currentIndex2--;
        if (currentIndex2 < 0) {
            currentIndex2 = difficulty.length - 1;
        }
        System.out.println("Dificuldade selecionada: " + difficulty[currentIndex2]); // Debugging line
        updateLabel(labelDifficulty);
       // generateGrid(); // Adicionado para chamar a geração da grade quando a dificuldade é alterada
    }

    @FXML
    public void handleBotaoD2(ActionEvent actionEvent) {
        currentIndex2++;
        if (currentIndex2 > difficulty.length - 1) {
            currentIndex2 = 0;
        }
        System.out.println("Dificuldade selecionada: " + difficulty[currentIndex2]); // Debugging line
        updateLabel(labelDifficulty);
        //generateGrid(); // Adicionado para chamar a geração da grade quando a dificuldade é alterada
    }




    private void updateLabel(Label x) {
        if (x.equals(labelLanguage)) {
            x.setText(values[currentIndex]);
        } else if (x.equals(labelDifficulty)) {
            x.setText(difficulty[currentIndex2]);
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicialização se necessário
    }
}
