package at.fhtw.highscores;

import at.fhtw.highscores.model.HighscoreEntry;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel {
    // https://en.wikipedia.org/wiki/Observer_pattern
    private List<FocusChangedListener> focusChangedListenerList = new ArrayList<FocusChangedListener>();

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

    public ObservableList<HighscoreEntry> getData(){
        return data;
    }

    public void addListener(FocusChangedListener listener) {
        this.focusChangedListenerList.add(listener);
    }

    public void saveDataToList(){
        data.add(new HighscoreEntry(currentUsername.get(), currentPoints.get()));
        currentUsername.set("");
        currentPoints.set("");
        for (var listener: this.focusChangedListenerList) {
            listener.requestFocusChange("input of username");
        }
    }
}
