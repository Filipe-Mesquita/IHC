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
    private String user;
    public Player jogador;

    public  void setUser(String user)
    {
        this.user = user;
    }
    public void setWord(String word)
    {
        this.word = word;
    }
    public void setScore(int socre){this.score = socre;}

    public void setLabel()
    {
        palavra.setText(word);
        num.setText(Integer.toString(score));

        jogador = new Player(user);
        jogador.setScore(score);

    }



    private void loadFXML(Button x,String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloController.class.getResource(fxmlFile));
            Parent root = loader.load();

            HelloController newController = loader.getController();

            for(int i = 0; i < newController.ranks.length; i++)
            {
                if(newController.ranks[i] != null) {
                    if (newController.ranks[i].getScore() < score) {

                        /*int n = 0;
                        for(int h = 0; h < newController.ranks.length; h++)
                        {
                            if(newController.ranks[h] != null) {
                                n++;
                            }else
                            {
                                break;
                            }
                        }

                        do{
                            newController.ranks[n] = newController.ranks[n - 1];
                            n--;
                        }while (n > i);

                        newController.ranks[i] = jogador;*/

                        // Array de tamanho 10
                        int size = newController.ranks.length;

                        // Encontrar a posição para inserir o novo elemento
                        int position = 0;
                        while (position < size && score < newController.ranks[position].getScore()) {
                            position++;
                        }

                        // Se o novo elemento é menor que todos os elementos existentes, não é necessário inseri-lo
                        if (position == size) {
                            break;
                        }

                        // Deslocar elementos para a direita a partir da posição encontrada
                        for (int j = size - 1; j > position; j--) {
                            newController.ranks[j] = newController.ranks[j - 1];
                        }

                        // Inserir o novo elemento na posição correta
                        newController.ranks[position] = jogador;
                        break;
                    }
                }
                else
                {
                    newController.ranks[i] = jogador;
                    break;
                }
            }

            newController.saveRanks();

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
