package com.wordle.wordle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class game5Controller implements Initializable {
    @FXML
    private Label wordLabel;
    private String lang;
    private String dif;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setDif(String dif)
    {
        this.dif = dif;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Inicializando controlador.");

        String filePath = "";
        if(lang.equals("English")) {
            filePath = Paths.get("C:/Users/migue/Aulas-Ubi/IHC/TrabalhoFinal/IHC/Wordle/src/main/java/com/wordle/wordle/wordsEN.txt").toAbsolutePath().toString();
        }
        if(lang.equals("Portuguese")) {
            filePath = Paths.get("C:/Users/migue/Aulas-Ubi/IHC/TrabalhoFinal/IHC/Wordle/src/main/java/com/wordle/wordle/wordsPT.txt").toAbsolutePath().toString();
        }
        if(lang.equals("French")) {
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
                if (line.length() == Integer.parseInt(dif)+2) {
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
}
