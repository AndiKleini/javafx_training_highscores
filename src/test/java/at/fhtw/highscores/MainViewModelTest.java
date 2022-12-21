package at.fhtw.highscores;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.ButtonMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.impl.NodeQueryImpl;
import org.testfx.util.NodeQueryUtils;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)

class MainViewModelTest {

    private Parent root = null;
    /**
     * Will be called with {@code @Before} semantics, i. e. before each test method.
     *
     * @param stage - Will be injected by the test runner.
     */
    @Start
    private void start(Stage primaryStage) throws Exception{
        root = Main.showStage(primaryStage);
    }

    @Test
    void saveDataToList_shouldAddData() {
        // Arrange
        MainViewModel mainViewModel = new MainViewModel();
        var lastDataCount = mainViewModel.getData().size();
        final String expectedName = "Susi Sorglos";
        mainViewModel.getCurrentUsername().set( expectedName );
        final String expectedPoints = "100";
        mainViewModel.getCurrentPoints().set( expectedPoints );
        // Act
        mainViewModel.saveDataToList(); // simulate button click
        var expectedDataCount = lastDataCount + 1;
        var currentDataCount = mainViewModel.getData().size();
        assertEquals(expectedDataCount, currentDataCount, "A new item should be added!");
        String currentLastName = mainViewModel.getData().get(expectedDataCount - 1).getUsername();
        String currentLastPoints = mainViewModel.getData().get(expectedDataCount - 1).getPoints();
        assertEquals(expectedName, currentLastName, "The name should be "+expectedName);
        assertEquals(expectedPoints, currentLastPoints, "The points should be "+expectedPoints);
    }

    @Test
    void should_contain_usernameColumn_header_with_text() {
        NodeQueryUtils.bySelector("#usernameColumn").apply(root).forEach(
                node ->  {
                    if (node instanceof TableColumnHeader)
                        assertEquals("Username", ((TableColumnHeader) node).getTableColumn().getText());
                }
        );
    }

    @Test
    void should_contain_usernameColumn_first_row_with_text() {
        NodeQueryUtils.bySelector("#usernameColumn").apply(root).forEach(
                node ->  {
                    if (node instanceof TableCell &&
                            ((TableCell) node).getIndex() == 0)
                        assertEquals("daniel", ((TableCell) node).getText());
                }
        );
    }

    @Test
    void should_contain_pointsColumn_first_row_with_text() {
        NodeQueryUtils.bySelector("#pointsColumn").apply(root).forEach(
                node ->  {
                    if (node instanceof TableCell &&
                            ((TableCell) node).getIndex() == 0)
                        assertEquals("infinite", ((TableCell) node).getText());
                }
        );
    }


    @Test
    void should_contain_new_row_with_entered_text(FxRobot robot) {
        final String username = "Edin";
        final String points = "666";
        new NodeQueryImpl().from(root).lookup("#usernameTextField").queryTextInputControl().setText(username);
        new NodeQueryImpl().from(root).lookup("#pointsTextField").queryTextInputControl().setText(points);
        robot.clickOn(new NodeQueryImpl().from(root).lookup(ButtonMatchers.isDefaultButton()).queryButton());
        NodeQueryUtils.bySelector("#usernameColumn").apply(root).forEach(
                node ->  {
                    if (node instanceof TableCell &&
                            ((TableCell) node).getIndex() == 2)
                        assertEquals(username, ((TableCell) node).getText());
                }
        );
        NodeQueryUtils.bySelector("#pointsColumn").apply(root).forEach(
                node ->  {
                    if (node instanceof TableCell &&
                            ((TableCell) node).getIndex() == 2)
                        assertEquals(points, ((TableCell) node).getText());
                }
        );

    }

}