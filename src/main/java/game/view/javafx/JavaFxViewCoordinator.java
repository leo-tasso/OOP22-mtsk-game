package game.view.javafx;

import java.util.Optional;

import game.LeaderBoard;
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
     * Method to start the game from the coordinator.
     */
    void gameStarter();

    /**
     * Method to get the stats.
     * 
     * @return the saved stats.
     */
    LeaderBoard getStats();

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
