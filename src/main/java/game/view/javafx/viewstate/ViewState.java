package game.view.javafx.viewstate;

import game.view.javafx.JavaFxViewCoordinator;
import javafx.stage.Stage;

/**
 * Interface to model the a state of the view (e.g. menu, stats, gameover).
 */
public interface ViewState {
    /**
     * To show to screen the state.
     * 
     * @param jView the view coordinator.
     * @param stage the stage to display into.
     */
    void display(JavaFxViewCoordinator jView, Stage stage);
}
