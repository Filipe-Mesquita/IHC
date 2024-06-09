package com.wordle.wordle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.io.BufferedReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.List;

public class game1Controller implements Initializable {

    @FXML
    public Button bQ, bW, bE, bR, bT, bY, bU, bI, bO, bP, bA, bS, bD, bF, bG, bH, bJ, bK, bL, bENTER, bZ, bX, bC, bV, bB, bN, bM, bDELETE;
    @FXML
    public Label l11, l12, l13, l21, l22, l23, l31, l32, l33, l41, l42, l43, l51, l52, l53;
    @FXML
    private Label wordLabel;
    @FXML
    private Button back;
    @FXML
    private String lang;

    private int currentRow = 1;
    private int currentColumn = 1;
    private char[] targetWord;
    private boolean rowChanged = false;

    public void setLang(String lang) {
        this.lang = lang;
        initializeComponents();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        initializeKeyboard();
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

        targetWord = findRandomLetterWord(filePath);
        if (targetWord != null && targetWord.length == 3) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 0; i < targetWord.length; i++) {
                wordBuilder.append(targetWord[i]).append(' ');

                System.out.println(targetWord[i]);
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
                if (line.length() == 3) {
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

    private void initializeKeyboard() {
        // Assign action listeners to each button to handle letter input and actions
        bQ.setOnAction(e -> handleLetterInput('Q'));
        bW.setOnAction(e -> handleLetterInput('W'));
        bE.setOnAction(e -> handleLetterInput('E'));
        bR.setOnAction(e -> handleLetterInput('R'));
        bT.setOnAction(e -> handleLetterInput('T'));
        bY.setOnAction(e -> handleLetterInput('Y'));
        bU.setOnAction(e -> handleLetterInput('U'));
        bI.setOnAction(e -> handleLetterInput('I'));
        bO.setOnAction(e -> handleLetterInput('O'));
        bP.setOnAction(e -> handleLetterInput('P'));
        bA.setOnAction(e -> handleLetterInput('A'));
        bS.setOnAction(e -> handleLetterInput('S'));
        bD.setOnAction(e -> handleLetterInput('D'));
        bF.setOnAction(e -> handleLetterInput('F'));
        bG.setOnAction(e -> handleLetterInput('G'));
        bH.setOnAction(e -> handleLetterInput('H'));
        bJ.setOnAction(e -> handleLetterInput('J'));
        bK.setOnAction(e -> handleLetterInput('K'));
        bL.setOnAction(e -> handleLetterInput('L'));
        bZ.setOnAction(e -> handleLetterInput('Z'));
        bX.setOnAction(e -> handleLetterInput('X'));
        bC.setOnAction(e -> handleLetterInput('C'));
        bV.setOnAction(e -> handleLetterInput('V'));
        bB.setOnAction(e -> handleLetterInput('B'));
        bN.setOnAction(e -> handleLetterInput('N'));
        bM.setOnAction(e -> handleLetterInput('M'));
        bDELETE.setOnAction(e -> handleDeleteInput());
        bENTER.setOnAction(e -> handleEnterInput());
    }

    private void handleLetterInput(char letter) {
        Label currentLabel = getCurrentLabel();
        if (currentLabel != null) {
            currentLabel.setText(String.valueOf(letter));
            moveToNextLabel();
        }
    }

    private void handleDeleteInput() {
        Label currentLabel = getCurrentLabel();
        if (currentLabel != null) {
            currentLabel.setText("");
            moveToPreviousLabel();
        }
    }

    private void handleEnterInput() {
        if (isRowFullyFilled()) {
            if (checkWord()) {
                wordLabel.setText("Correct! You won!");
            } else {
                moveToNextRow();
            }
        }
    }

    private boolean isRowFullyFilled() {
        switch (currentRow) {
            case 1:
                return !l11.getText().isEmpty() && !l12.getText().isEmpty() && !l13.getText().isEmpty();
            case 2:
                return !l21.getText().isEmpty() && !l22.getText().isEmpty() && !l23.getText().isEmpty();
            case 3:
                return !l31.getText().isEmpty() && !l32.getText().isEmpty() && !l33.getText().isEmpty();
            case 4:
                return !l41.getText().isEmpty() && !l42.getText().isEmpty() && !l43.getText().isEmpty();
            case 5:
                return !l51.getText().isEmpty() && !l52.getText().isEmpty() && !l53.getText().isEmpty();
            default:
                return false;
        }
    }

    private Label getCurrentLabel() {
        switch (currentRow) {
            case 1:
                switch (currentColumn) {
                    case 1: return l11;
                    case 2: return l12;
                    case 3: return l13;
                }
            case 2:
                switch (currentColumn) {
                    case 1: return l21;
                    case 2: return l22;
                    case 3: return l23;
                }
            case 3:
                switch (currentColumn) {
                    case 1: return l31;
                    case 2: return l32;
                    case 3: return l33;
                }
            case 4:
                switch (currentColumn) {
                    case 1: return l41;
                    case 2: return l42;
                    case 3: return l43;
                }
            case 5:
                switch (currentColumn) {
                    case 1: return l51;
                    case 2: return l52;
                    case 3: return l53;
                }
            default:
                return null;
        }
    }

    private void moveToNextLabel() {
        if (currentColumn < 3) {
            currentColumn++;
        }
    }

    private void moveToPreviousLabel() {
        if (currentColumn > 1 && !rowChanged) {
            currentColumn--;
        } else if (currentColumn == 1 && currentRow > 1 && !rowChanged) {
            currentRow--;
            currentColumn = 3; // Moving to the last column of the previous row
        }
    }

    private void moveToNextRow() {
        if (!rowChanged && currentRow < 5) {
            currentRow++;
            currentColumn = 1;
            rowChanged = true;
        }
    }

    private boolean checkWord() {
        StringBuilder inputWord = new StringBuilder();
        switch (currentRow) {
            case 1:
                inputWord.append(l11.getText()).append(l12.getText()).append(l13.getText());
                break;
            case 2:
                inputWord.append(l21.getText()).append(l22.getText()).append(l23.getText());
                break;
            case 3:
                inputWord.append(l31.getText()).append(l32.getText()).append(l33.getText());
                break;
            case 4:
                inputWord.append(l41.getText()).append(l42.getText()).append(l43.getText());
                break;
            case 5:
                inputWord.append(l51.getText()).append(l52.getText()).append(l53.getText());
                break;
        }
        return inputWord.toString().equals(new String(targetWord));
    }

    public void funcBack() {
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

