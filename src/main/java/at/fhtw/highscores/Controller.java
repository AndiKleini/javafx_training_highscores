package at.fhtw.highscores;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final MainViewModel viewModel = new MainViewModel();

    // references used to setup data-binding
    public TableView tableView;
    public TableColumn usernameColumn;
    public TableColumn pointsColumn;
    public TextField usernameTextField;
    public TextField pointsTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewModel.addListener(new FocusChangedListener() {
            @Override
            public void requestFocusChange(String name) {
                usernameTextField.requestFocus();
            }
        });

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));

        tableView.setItems(viewModel.getData());

        usernameTextField.textProperty().bindBidirectional(viewModel.getCurrentUsername());
        pointsTextField.textProperty().bindBidirectional(viewModel.getCurrentPoints());
    }

    public void saveAction(ActionEvent actionEvent) {
        viewModel.saveDataToList();
        // don't put "requestFocus" logic in here!
        // you would split up the logic of your view on different places
    }
}
