package at.fhtw.highscores;

import at.fhtw.highscores.model.HighscoreEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainViewModel {
    // https://en.wikipedia.org/wiki/Observer_pattern
    private List<FocusChangedListener> focusChangedListenerList = new ArrayList<FocusChangedListener>();

    public boolean getIsUsernameNotValid() {
        return isUsernameNotValid.get();
    }

    public boolean isIsPointsNotValid() {
        return isPointsNotValid.get();
    }

    public BooleanProperty getIsPointsNotValidProperty() {
        return isPointsNotValid;
    }

    public void setIsPointsNotValid(boolean isPointsNotValid) {
        this.isPointsNotValid.set(isPointsNotValid);
    }

    private final BooleanProperty isUsernameNotValid = new SimpleBooleanProperty(false);
    private final BooleanProperty isPointsNotValid = new SimpleBooleanProperty(false);
    private final StringProperty currentUsername = new SimpleStringProperty("");
    private final StringProperty currentPoints = new SimpleStringProperty("");
    private final ObservableList<HighscoreEntry> data =
            FXCollections.observableArrayList(
                    new HighscoreEntry("daniel", "infinite"),
                    new HighscoreEntry("not daniel", "few")
            );

    public StringProperty getCurrentUsername() {
        return currentUsername;
    }

    public StringProperty getCurrentPoints(){
        return currentPoints;
    }

    public BooleanProperty getUsernameIsNotValid() { return this.isUsernameNotValid; }

    public ObservableList<HighscoreEntry> getData(){
        return data;
    }

    public void addListener(FocusChangedListener listener) {
        this.focusChangedListenerList.add(listener);
    }

    public void saveDataToList(){

        isUsernameNotValid.set(this.currentUsername == null || this.currentUsername.get() == "");
        Pattern pattern = Pattern.compile("\\d*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(this.currentPoints.get());
        isPointsNotValid.set(!matcher.matches());
        if (isPointsNotValid.get() || isUsernameNotValid.get()) {
           return;
        }

        data.add(new HighscoreEntry(currentUsername.get(), currentPoints.get()));
        currentUsername.set("");
        currentPoints.set("");
        for (var listener: this.focusChangedListenerList) {
            listener.requestFocusChange("input of username");
        }
    }
}
