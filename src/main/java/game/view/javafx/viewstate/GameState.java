package game.view.javafx.viewstate;

import java.util.List;

import game.controlling.Input;
import game.engine.gameobject.GameObject;
/**
 * Interface to model a {@link ViewState} relative to the gameplay.
 */
public interface GameState extends ViewState {
/**
 * Method to refresh the view.
 * @param objectList the new list of object to display.
 */
    void refresh(List<List<GameObject>> objectList);
/**
 * Method to get the {@link Input} created by the view.
 * @return the {@link Input} created by the view.
 */
    Input getInput();
}
