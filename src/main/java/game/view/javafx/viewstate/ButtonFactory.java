package game.view.javafx.viewstate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Factory to create javaFx buttons with the same style.
 */
public class ButtonFactory {
    private static final int ASPECT_WIDTH = 16;
    private static final int ASPECT_HEIGHT = 9;
    private final int fontSize;

    /**
     * Constructor for the factory.
     * 
     * @param fontSize the button text size.
     */
    public ButtonFactory(final int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Method to create the button.
     * 
     * @param stage   the reference {@link Stage} to set the proportions.
     * @param title   the text on the button.
     * @param actions the actions to perform.
     * @return the created button.
     */
    public Button create(final Stage stage, final String title, final EventHandler<ActionEvent> actions) {
        final Button b = new Button(title);
        b.setOnAction(actions);
        b.prefWidthProperty().bind(stage.widthProperty().divide(ASPECT_HEIGHT));
        b.prefHeightProperty().bind(stage.heightProperty().divide(ASPECT_WIDTH));
        b.setFont(Font.font("futura", fontSize));
        b.setStyle("-fx-background-color: black; -fx-background-radius: 20px; -fx-text-fill: white;");
        b.setOnMouseEntered(e -> b.setStyle("-fx-background-color: black;-fx-text-fill: white;"));
        b.setOnMouseExited(e -> b
                .setStyle("-fx-background-color: black; -fx-background-radius: 20px; -fx-text-fill: white;"));
        b.setOnMousePressed(e -> b.setStyle("-fx-background-color: #3D3D3D; -fx-text-fill: white;"));
        b.setOnMouseReleased(e -> b.setStyle("-fx-background-color: black; -fx-text-fill: white;"));
        return b;
    }
}
