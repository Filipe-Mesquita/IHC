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

public class game3Controller implements Initializable {
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

        String threeLetterWord = findRandomLetterWord(filePath);
        if (threeLetterWord != null) {
            wordLabel.setText(threeLetterWord);
            System.out.println(wordLabel);
        } else {
            wordLabel.setText("No word found");
        }
    }

    private String findRandomLetterWord(String filePath) {
        List<String> threeLetterWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 5) {
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
        return threeLetterWords.get(randomIndex);
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
