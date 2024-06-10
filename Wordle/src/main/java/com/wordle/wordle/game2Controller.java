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
    private Button bQ, bW, bE, bR, bT, bY, bU, bI, bO, bP, bA, bS, bD, bF, bG, bH, bJ, bK, bL, bENTER, bZ, bX, bC, bV, bB, bN, bM, bDELETE;
    @FXML
    private Label l11, l12, l13, l14, l21, l22, l23, l24, l31, l32, l33, l34, l41, l42, l43, l44, l51, l52, l53, l54;
    @FXML
    private Label wordLabel;
    @FXML
    private Button back;
    private String lang;
    private int currentRow = 1;
    private int currentColumn = 1;
    private char[] targetWord;
    private String user;
    private int score;

    public void setLang(String lang) {
        this.lang = lang;
        initializeComponents();
    }
    public  void setUser(String user)
    {
        this.user = user;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        initializeKeyboard();
    }

    private void initializeComponents() {
        String filePath = "";
        if (lang.equals("English")) {
            filePath = Paths.get("./Wordle/src/main/java/com/wordle/wordle/wordsEN.txt").toAbsolutePath().toString();
        } else if (lang.equals("Portuguese")) {
            filePath = Paths.get("./Wordle/src/main/java/com/wordle/wordle/wordsPT.txt").toAbsolutePath().toString();
        } else if (lang.equals("French")) {
            filePath = Paths.get("./Wordle/src/main/java/com/wordle/wordle/wordsFR.txt").toAbsolutePath().toString();
        }

        targetWord = findRandomLetterWord(filePath);
        if (targetWord != null && targetWord.length == 4) {
            StringBuilder wordBuilder = new StringBuilder();
            for (int i = 0; i < targetWord.length; i++) {
                wordBuilder.append(targetWord[i]).append(' ');
                System.out.println(targetWord[i]);
            }
            wordLabel.setText(wordBuilder.toString().trim());
        } else {
            wordLabel.setText("No word found");
        }
    }

    private char[] findRandomLetterWord(String filePath) {
        List<String> fourLetterWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() == 4) {
                    fourLetterWords.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fourLetterWords.isEmpty()) {
            return null;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(fourLetterWords.size());
        String randomWord = fourLetterWords.get(randomIndex);
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
        System.out.println("Delete input");
        Label currentLabel = getCurrentLabel();
        if (currentLabel != null) {
            if (!currentLabel.getText().isEmpty()) {
                currentLabel.setText("");
                System.out.println(currentColumn);
            } else {
                moveToPreviousLabel();
                currentLabel = getCurrentLabel();
                if (currentLabel != null) {
                    currentLabel.setText("");
                    System.out.println(currentColumn);
                }
            }
        }
    }

    private void handleEnterInput() {
        if (isRowFullyFilled()) {
            checkLettersInWord();
            if (checkWord() || score == 0) {
                wordLabel.setText("Correct! You won!");
                System.out.println("Está certo!");

                try {
                    FXMLLoader loader = new FXMLLoader(HelloController.class.getResource("end-view.fxml"));
                    Parent root = loader.load();

                    endController newController = loader.getController();
                    newController.setWord(new String(targetWord));
                    newController.setScore(score * 4);
                    newController.setUser(user);
                    newController.setLabel();


                    Stage stage = (Stage) bENTER.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                moveToNextRow();
            }
        }
    }

    private boolean isRowFullyFilled() {
        switch (currentRow) {
            case 1:
                return !l11.getText().isEmpty() && !l12.getText().isEmpty() && !l13.getText().isEmpty() && !l14.getText().isEmpty();
            case 2:
                return !l21.getText().isEmpty() && !l22.getText().isEmpty() && !l23.getText().isEmpty() && !l24.getText().isEmpty();
            case 3:
                return !l31.getText().isEmpty() && !l32.getText().isEmpty() && !l33.getText().isEmpty() && !l34.getText().isEmpty();
            case 4:
                return !l41.getText().isEmpty() && !l42.getText().isEmpty() && !l43.getText().isEmpty() && !l44.getText().isEmpty();
            case 5:
                return !l51.getText().isEmpty() && !l52.getText().isEmpty() && !l53.getText().isEmpty() && !l54.getText().isEmpty();
            default:
                return false;
        }
    }

    private Label getCurrentLabel() {
        switch (currentRow) {
            case 1:
                switch (currentColumn) {
                    case 1:
                        return l11;
                    case 2:
                        return l12;
                    case 3:
                        return l13;
                    case 4:
                        return l14;
                }
            case 2:
                switch (currentColumn) {
                    case 1:
                        return l21;
                    case 2:
                        return l22;
                    case 3:
                        return l23;
                    case 4:
                        return l24;
                }
            case 3:
                switch (currentColumn) {
                    case 1:
                        return l31;
                    case 2:
                        return l32;
                    case 3:
                        return l33;
                    case 4:
                        return l34;
                }
            case 4:
                switch (currentColumn) {
                    case 1:
                        return l41;
                    case 2:
                        return l42;
                    case 3:
                        return l43;
                    case 4:
                        return l44;
                }
            case 5:
                switch (currentColumn) {
                    case 1:
                        return l51;
                    case 2:
                        return l52;
                    case 3:
                        return l53;
                    case 4:
                        return l54;
                }
            default:
                return null;
        }
    }

    private void moveToNextLabel() {
        if (currentColumn < 4) {
            currentColumn++;
        }
    }

    private void moveToPreviousLabel() {
        if (currentColumn > 1) {
            currentColumn--;
        }
    }

    private void moveToNextRow() {
        if (currentRow < 5) {
            currentRow++;
            currentColumn = 1;
        }
    }

    private boolean checkWord() {
        StringBuilder inputWord = new StringBuilder();
        switch (currentRow) {
            case 1:
                inputWord.append(l11.getText()).append(l12.getText()).append(l13.getText()).append(l14.getText());

                break;
            case 2:
                inputWord.append(l21.getText()).append(l22.getText()).append(l23.getText()).append(l24.getText());
                break;
            case 3:
                inputWord.append(l31.getText()).append(l32.getText()).append(l33.getText()).append(l34.getText());
                break;
            case 4:
                inputWord.append(l41.getText()).append(l42.getText()).append(l43.getText()).append(l44.getText());
                break;
            case 5:
                inputWord.append(l51.getText()).append(l52.getText()).append(l53.getText()).append(l54.getText());
                break;
        }
        return inputWord.toString().equals(new String(targetWord));
    }

    private void checkLettersInWord() {
        StringBuilder inputWord = new StringBuilder();
        List<Label> currentLabels = getCurrentLabels();
        switch (currentRow) {
            case 1:
                inputWord.append(l11.getText()).append(l12.getText()).append(l13.getText()).append(l14.getText());
                score = 50;
                break;
            case 2:
                inputWord.append(l21.getText()).append(l22.getText()).append(l23.getText()).append(l24.getText());
                score = 40;
                break;
            case 3:
                inputWord.append(l31.getText()).append(l32.getText()).append(l33.getText()).append(l34.getText());
                score = 30;
                break;
            case 4:
                inputWord.append(l41.getText()).append(l42.getText()).append(l43.getText()).append(l44.getText());
                score = 20;
                break;
            case 5:
                inputWord.append(l51.getText()).append(l52.getText()).append(l53.getText()).append(l54.getText());
                if(inputWord.toString().equals(new String(targetWord)))
                {
                    score = 10;
                }
                else
                {
                    score = 0;
                }
                break;
        }
        String input = inputWord.toString();
        for (int i = 0; i < input.length(); i++) {
            char inputChar = input.charAt(i);
            boolean isCorrect = false;
            boolean isPresent = false;
            for (int j = 0; j < targetWord.length; j++) {
                if (inputChar == targetWord[j]) {
                    isPresent = true;
                    if (i == j) {
                        isCorrect = true;
                    }
                    break;
                }
            }
            if (isCorrect) {
                currentLabels.get(i).setStyle("-fx-background-color: green;-fx-border-color: #e8e400; -fx-border-radius: 10px;-fx-background-radius:10px"); // Correct letter in the correct position
            } else if (isPresent) {
                currentLabels.get(i).setStyle("-fx-background-color: yellow;-fx-border-color: #e8e400; -fx-border-radius: 10px;-fx-background-radius:10px"); // Correct letter in the wrong position
            }
        }
    }

    private List<Label> getCurrentLabels() {
        List<Label> labels = new ArrayList<>();
        switch (currentRow) {
            case 1:
                labels.add(l11);
                labels.add(l12);
                labels.add(l13);
                labels.add(l14);
                break;
            case 2:
                labels.add(l21);
                labels.add(l22);
                labels.add(l23);
                labels.add(l24);
                break;
            case 3:
                labels.add(l31);
                labels.add(l32);
                labels.add(l33);
                labels.add(l34);
                break;
            case 4:
                labels.add(l41);
                labels.add(l42);
                labels.add(l43);
                labels.add(l44);
                break;
            case 5:
                labels.add(l51);
                labels.add(l52);
                labels.add(l53);
                labels.add(l54);
                break;
        }
        return labels;
    }

    public void funcBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("settings-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
