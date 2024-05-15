module com.wordle.wordle {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.wordle.wordle to javafx.fxml;
    exports com.wordle.wordle;
}