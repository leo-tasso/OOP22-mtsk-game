package game.view.javafx;

import java.util.Optional;

import game.Controller;
import game.view.javafx.viewstate.GameState;

/**
 * Interface to model a javaFX coordinator, with mehtods to dialog with
 * ViewStates.
 */
public interface JavaFxViewCoordinator {
    /**
     * Method to set the {@link gameState} of the coordinator.
     * 
     * @param gameState the {@link gameState} to set
     */
    void setGameState(Optional<GameState> gameState);

    /**
     * Method to get the {@link Controller} of the coordinator.
     * 
     * @return {@link Controller} of the coordinator.
     */
    Controller getController();

    /**
     * To toggle between full screen and windowed.
     */
    void toggleFullScreen();

    /**
     * Checks if game is set to fullscreen.
     * 
     * @return if game is set to fullscreen.
     */
    boolean isFullScreen();
}
