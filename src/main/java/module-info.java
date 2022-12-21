module highscores {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens at.fhtw.highscores to javafx.graphics, javafx.fxml;
    exports at.fhtw.highscores;
    exports at.fhtw.highscores.model;
}
