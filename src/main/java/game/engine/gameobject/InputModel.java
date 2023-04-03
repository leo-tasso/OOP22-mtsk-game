package game.engine.gameobject;

import game.controlling.Input;

/**
 * Interface that allows to apply the changes
 * requested by the input on the object.
 */
public interface InputModel {

    /**
     * Method to update the state of an object according the inputs.
     * 
     * @param obj the objects
     * @param c   the inputs
     * @param elapsedTime the elapsed time from last frame
     */
    void update(GameObject obj, Input c, long elapsedTime);
}
