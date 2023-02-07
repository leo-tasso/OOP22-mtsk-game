package api;

import game.gameobject.GameObject;

/**
 * Interface for the input model of an object.
 */
public interface InputModel {
    /**
     * Method to update the object according the inputs.
     * 
     * @param obj the objects
     * @param c   the inputs
     */
     void update(GameObject obj, Input c);
}
