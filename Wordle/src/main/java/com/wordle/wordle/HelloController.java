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
import java.io.*;
import java.util.ArrayList;

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
    private String username;

    private Player[] ranks = new Player[10];

    private Player newPLayer;



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

    private void loadGameFXML(Button x,String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(fxmlFile));
            Parent root = loader.load();

            if(fxmlFile.equals("game1-view.fxml"))
            {
                game1Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
            }
            if(fxmlFile.equals("game2-view.fxml"))
            {
                game2Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
            }
            if(fxmlFile.equals("game3-view.fxml"))
            {
                game3Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
            }
            if(fxmlFile.equals("game4-view.fxml"))
            {
                game4Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
            }
            if(fxmlFile.equals("game5-view.fxml"))
            {
                game5Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
            }

            Stage currentStage = (Stage) x.getScene().getWindow();
            currentStage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
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

        username = User.getText();
        if (username.length() >= 3) {

           /* int  n;
            for(n = 0; n < ranks.length; n ++)
            {
                if(ranks[n] == null)
                {
                    break;
                }
            }*/

            newPLayer = new  Player(username);


            System.out.println(newPLayer.getUsername());

            if (currentIndex2 == 0) {
                loadGameFXML(play, "game1-view.fxml");
            }


            if (currentIndex2 == 1) {

                loadGameFXML(play, "game2-view.fxml");
            }

            if (currentIndex2 == 2) {

                loadGameFXML(play, "game3-view.fxml");
            }

            if (currentIndex2 == 3) {

                loadGameFXML(play, "game4-view.fxml");
            }

            if (currentIndex2 == 4) {

                loadGameFXML(play, "game5-view.fxml");
            }
        }
        else
        {
            User.requestFocus();
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



    private void saveRanks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ranks.txt"))) {
            for (Player player : ranks) {
                writer.write("Username: " + player.getUsername() + ", Score: " + player.getScore());
                writer.newLine(); // Nova linha após cada jogador
            }
            System.out.println("Informações dos jogadores gravadas com sucesso no arquivo players.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readRanks()
    {
        ArrayList<Player> playersList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("players.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Processamento de cada linha
                String[] parts = line.split(", ");
                String username = parts[0].split(": ")[1];
                int score = Integer.parseInt(parts[1].split(": ")[1]);

                // Criação de um novo objeto Player e adição à lista
                Player player = new Player(username);
                player.setScore(score);
                playersList.add(player);
            }
            System.out.println("Informações dos jogadores lidas com sucesso do arquivo players.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converte ArrayList para Array
        Player[] players = new Player[playersList.size()];
        ranks = playersList.toArray(players);

        // Exibição das informações dos jogadores
        for (Player player : players) {
            System.out.println("Username: " + player.getUsername() + ", Score: " + player.getScore());
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicialização se necessário
    }
}
