package at.fhtw.highscores.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HighscoreEntry {

    private final StringProperty username;
    private final StringProperty points;

    public HighscoreEntry(String username, String points){
        this.username = new SimpleStringProperty(username);
        this.points = new SimpleStringProperty(points);
    }

    public HighscoreEntry(StringProperty username, StringProperty points) {
        this.username = username;
        this.points = points;
    }

    public String getPoints() {
        return points.get();
    }

    public void setPoints(String points){
        this.points.set(points);
    }

    public StringProperty pointsProperty() {
        return points;
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username){
        this.username.set(username);
    }

    public StringProperty usernameProperty() {
        return username;
    }
}
