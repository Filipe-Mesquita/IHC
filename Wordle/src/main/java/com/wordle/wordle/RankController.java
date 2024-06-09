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

public class RankController implements Initializable {

    @FXML
    private Button back;

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


    private String u1;
    private String s1;
    private String u2;
    private String s2;
    private String u3;
    private String s3;
    private String u4;
    private String s4;
    private String u5;
    private String s5;
    private String u6;
    private String s6;
    private String u7;
    private String s7;
    private String u8;
    private String s8;
    private String u9;
    private String s9;
    private String u10;
    private String s10;

    public void setU1(String u)
    {
        u1 = u;
    }
    public void setS1(String s)
    {
        s1 = s;
    }
    public void setU2(String u)
    {
        u2 = u;
    }
    public void setS2(String s)
    {
        s2 = s;
    }
    public void setU3(String u)
    {
        u3 = u;
    }
    public void setS3(String s)
    {
        s3 = s;
    }
    public void setU4(String u)
    {
        u4 = u;
    }
    public void setS4(String s)
    {
        s4 = s;
    }
    public void setU5(String u)
    {
        u5 = u;
    }
    public void setS5(String s)
    {
        s5 = s;
    }
    public void setU6(String u)
    {
        u6 = u;
    }
    public void setS6(String s)
    {
        s6 = s;
    }
    public void setU7(String u)
    {
        u7 = u;
    }
    public void setS7(String s)
    {
        s7 = s;
    }
    public void setU8(String u)
    {
        u8 = u;
    }
    public void setS8(String s)
    {
        s8 = s;
    }
    public void setU9(String u)
    {
        u9 = u;
    }
    public void setS9(String s)
    {
        s9 = s;
    }
    public void setU10(String u)
    {
        u10 = u;
    }
    public void setS10(String s)
    {
        s10 = s;
    }

    public void setTable()
    {

        pos1user.setText(u1);
        pos1score.setText(s1);
        pos2user.setText(u2);
        pos2score.setText(s2);
        pos3user.setText(u3);
        pos3score.setText(s3);
        pos4user.setText(u4);
        pos4score.setText(s4);
        pos5user.setText(u5);
        pos5score.setText(s5);
        pos6user.setText(u6);
        pos6score.setText(s6);
        pos7user.setText(u7);
        pos7score.setText(s7);
        pos8user.setText(u8);
        pos8score.setText(s8);
        pos9user.setText(u9);
        pos9score.setText(s9);
        pos10user.setText(u10);
        pos10score.setText(s10);
    }


    private void loadFXML(Button x, String fxmlFile) {
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
    public void backFunc() {
        System.out.println("Voltando para o início."); // Debugging line
        loadFXML(back,"start-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
