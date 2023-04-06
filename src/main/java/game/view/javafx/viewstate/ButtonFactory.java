package game.view.javafx.viewstate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
/**
 * Interface For a button factory.
 */
public interface ButtonFactory {

    /**
     * Method to create the button.
     * 
     * @param stage   the reference {@link Stage} to set the proportions.
     * @param title   the text on the button.
     * @param actions the actions to perform.
     * @return the created button.
     */
    Button create(Stage stage, String title, EventHandler<ActionEvent> actions);

}
