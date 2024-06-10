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

    public Player[] ranks  = new Player[10];




    //Ranking Variables
    @FXML
    private Label pos1user;
    @FXML
    private Label pos1score;
    @FXML
    private Label pos2user;
    @FXML
    private Label pos2score;
    @FXML
    private Label pos3user;
    @FXML
    private Label pos3score;
    @FXML
    private Label pos4user;
    @FXML
    private Label pos4score;
    @FXML
    private Label pos5user;
    @FXML
    private Label pos5score;
    @FXML
    private Label pos6user;
    @FXML
    private Label pos6score;
    @FXML
    private Label pos7user;
    @FXML
    private Label pos7score;
    @FXML
    private Label pos8user;
    @FXML
    private Label pos8score;
    @FXML
    private Label pos9user;
    @FXML
    private Label pos9score;
    @FXML
    private Label pos10user;
    @FXML
    private Label pos10score;





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

    private void loadRankFXML(Button x, String fxmlFile)
    {
        try {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(fxmlFile));
            Parent root = loader.load();

            RankController newController = loader.getController();
            if(ranks[0] != null) {
                newController.setU1(ranks[0].getUsername());
                newController.setS1(Integer.toString(ranks[0].getScore()));
            }
            if(ranks[1] != null) {
                newController.setU2(ranks[1].getUsername());
                newController.setS2(Integer.toString(ranks[1].getScore()));
            }
            if(ranks[2] != null) {
                newController.setU3(ranks[2].getUsername());
                newController.setS3(Integer.toString(ranks[2].getScore()));
            }
            if(ranks[3] != null) {
                newController.setU4(ranks[3].getUsername());
                newController.setS4(Integer.toString(ranks[3].getScore()));
            }
            if(ranks[4] != null) {
                newController.setU5(ranks[4].getUsername());
                newController.setS5(Integer.toString(ranks[4].getScore()));
            }
            if(ranks[5] != null) {
                newController.setU6(ranks[5].getUsername());
                newController.setS6(Integer.toString(ranks[5].getScore()));
            }
            if(ranks[6] != null) {
                newController.setU7(ranks[6].getUsername());
                newController.setS7(Integer.toString(ranks[6].getScore()));
            }
            if(ranks[7] != null) {
                newController.setU8(ranks[7].getUsername());
                newController.setS8(Integer.toString(ranks[7].getScore()));
            }
            if(ranks[8] != null) {
                newController.setU9(ranks[8].getUsername());
                newController.setS9(Integer.toString(ranks[8].getScore()));
            }
            if(ranks[9] != null) {
                newController.setU10(ranks[9].getUsername());
                newController.setS10(Integer.toString(ranks[9].getScore()));
            }

            newController.setTable();


            Stage currentStage = (Stage) x.getScene().getWindow();
            currentStage.close();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

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
                newController.setUser((username));
            }
            if(fxmlFile.equals("game2-view.fxml"))
            {
                game2Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
                newController.setUser((username));
            }
            if(fxmlFile.equals("game3-view.fxml"))
            {
                game3Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
                newController.setUser((username));
            }
            if(fxmlFile.equals("game4-view.fxml"))
            {
                game4Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
                newController.setUser((username));
            }
            if(fxmlFile.equals("game5-view.fxml"))
            {
                game5Controller newController = loader.getController();
                newController.setLang(values[currentIndex]);
                newController.setUser((username));
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
    public void rankFunc()
    {
        System.out.println("Ir pros ranks "); // Debugging line
        loadRankFXML(start,"ranking-view.fxml");

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

            System.out.println("vai entrar no for");

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



    public void saveRanks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:/UBI/IHCtf/IHC/Wordle/src/main/java/com/wordle/wordle/ranks.txt"))) {
            for (Player player : ranks) {
                if (player != null) {
                    writer.write("Username: " + player.getUsername() + ", Score: " + player.getScore());
                }
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

        try (BufferedReader reader = new BufferedReader(new FileReader("C:/UBI/IHCtf/IHC/Wordle/src/main/java/com/wordle/wordle/ranks.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Verifica se a linha está no formato esperado
                if (line.startsWith("Username: ") && line.contains(", Score: ")) {
                    // Processamento de cada linha
                    String[] parts = line.split(", ");
                    String username = parts[0].split(": ")[1];
                    int score = Integer.parseInt(parts[1].split(": ")[1]);

                    // Criação de um novo objeto Player e adição à lista
                    Player player = new Player(username);
                    player.setScore(score);
                    playersList.add(player);
                }
            }
            System.out.println("Informações dos jogadores lidas com sucesso do arquivo players.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Converte ArrayList para Array
        ranks = playersList.toArray(ranks);

        //Print da rank list
        /*for(Player jogador: ranks)
        {
            System.out.println(jogador.getUsername() + " " + jogador.getScore());
        }*/
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        readRanks();
    }
}