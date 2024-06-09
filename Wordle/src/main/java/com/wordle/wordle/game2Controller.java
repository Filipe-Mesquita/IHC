package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class game2Controller implements Initializable {
    @FXML
    public Button bQ, bW, bE, bR, bT, bY, bU, bI, bO, bP, bA, bS, bD, bF, bG, bH, bJ, bK, bL, bENTER, bZ, bX, bC, bV, bB, bN, bM, bDELETE;
    @FXML
    public Label l11, l12, l13, l14, l21, l22, l23, l24, l31, l32, l33, l34, l41, l42, l43, l44, l51, l52, l53, l54;

    @FXML
    private Label wordLabel;
    @FXML
    private Button back;
    private String lang;

    public void setLang(String lang) {
        this.lang = lang;
        initializeComponents();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
    }

    private void initializeComponents() {
        System.out.println("LANG: " + lang);

        String filePath = "";
        if (lang.equals("English")) {
            filePath = Paths.get("C:/Users/migue/Aulas-Ubi/IHC/TrabalhoFinal/IHC/Wordle/src/main/java/com/wordle/wordle/wordsEN.txt").toAbsolutePath().toString();
        }
        if (lang.equals("Portuguese")) {
            filePath = Paths.get("C:/Users/migue/Aulas-Ubi/IHC/TrabalhoFinal/IHC/Wordle/src/main/java/com/wordle/wordle/wordsPT.txt").toAbsolutePath().toString();
        }
        if (lang.equals("French")) {
            filePath = Paths.get("C:/Users/migue/Aulas-Ubi/IHC/TrabalhoFinal/IHC/Wordle/src/main/java/com/wordle/wordle/wordsFR.txt").toAbsolutePath().toString();
        }

        char[] threeLetterWord = findRandomLetterWord(filePath);
        if (threeLetterWord != null && threeLetterWord.length == 4) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 0; i < threeLetterWord.length; i++) {
                wordBuilder.append(threeLetterWord).append(' ');

                System.out.println(threeLetterWord[i]);
            }
            wordLabel.setText(wordBuilder.toString().trim());
            System.out.println(); // Move to the next line in the console
        } else {
            wordLabel.setText("No word found");
        }
    }


    private char[] findRandomLetterWord(String filePath) {
        List<String> threeLetterWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 4) {
                    threeLetterWords.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (threeLetterWords.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(threeLetterWords.size());
        String randomWord = threeLetterWords.get(randomIndex);

        return randomWord.toCharArray();
    }





    public void funcBack(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("settings-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
