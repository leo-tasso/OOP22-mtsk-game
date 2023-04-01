package game.view.javafx;

import java.sql.Timestamp;
import java.util.Map;
import java.util.Optional;

import game.view.View;
import game.view.javafx.viewstate.gamestate.GameState;
/**
 * Interface to model a javaFX coordinator, with mehtods to dialog with
 * ViewStates.
 */
public interface JavaFxViewCoordinator extends View {
    /**
     * Method to set the {@link GameState} of the coordinator.
     * 
     * @param gameState the {@link GameState} to set
     */
    void setGameState(Optional<GameState> gameState);

    /**
     * Method to start the game from the coordinator.
     */
    void gameStarter();

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

    /**
     * Method to get the save data.
     * 
     * @return the save data.
     */
    Map<Timestamp, Long> getStats();
}
