package game.view;

import game.gameobject.GameObject;

/**
 * The interface to update the way an object is drawn.
 * 
 */
public interface Drawings {

    /**
     * A method that draws a circle relative to the object.
     * 
     * @param object
     */
    void drawCircle(GameObject object);
}
