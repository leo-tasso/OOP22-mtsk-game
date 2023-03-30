package game.view.javafx.viewstate;

import java.util.Optional;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatsState implements ViewState {
    private static final int BUTTON_SPACING = 20;
    private VBox root;

    public StatsState(final JavaFxViewCoordinator view, final Stage stage) {
        view.setGameState(Optional.empty());
        final Button exit = new ButtonFactory(BUTTON_SPACING)
                .create(stage, "Back to Main Menu", e -> Platform.runLater(() -> new ViewStateMenu(view, stage).display(view, stage)));
        root = new VBox(BUTTON_SPACING, exit);
        root.setAlignment(Pos.CENTER);
    }

    @Override
    public void display(JavaFxViewCoordinator jView, Stage stage) {
        stage.getScene().setRoot(root);
    }
}
