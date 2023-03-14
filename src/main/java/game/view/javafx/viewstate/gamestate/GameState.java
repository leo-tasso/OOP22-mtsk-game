package game.view.javafx.viewstate.gamestate;

import java.util.List;

import game.controlling.Input;
import game.engine.gameobject.GameObject;
import game.view.javafx.viewstate.ViewState;
import javafx.scene.Scene;

/**
 * Interface to model a {@link ViewState} relative to the gameplay.
 */
public interface GameState extends ViewState {
    /**
     * Method to refresh the view.
     * 
     * @param objectList the new list of object to display.
     * @param scene      the scene to update.
     */
    void refresh(List<List<GameObject>> objectList, Scene scene);

    /**
     * Method to get the {@link Input} created by the view.
     * 
     * @return the {@link Input} created by the view.
     */
    Input getInput();
}
