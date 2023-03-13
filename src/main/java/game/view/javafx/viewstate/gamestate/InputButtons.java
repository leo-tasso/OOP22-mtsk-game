package game.view.javafx.viewstate.gamestate;

import game.controlling.Input;
import javafx.scene.Scene;

/**
 * Class to manage the input buttons to attach to the view.
 */
public interface InputButtons {
    /**
     * To actuate the attachment to a specific scene.
     * 
     * @param scene the {@link Scene} that will be attached.
     * @param input the {@link Input} that will be edited.
     */
    void attach(Scene scene, Input input);

}
