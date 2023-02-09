package game.gameobject;

import game.view.Drawings;

/**
 * The interface to update the aspect properties of an object.
 */
public interface AspectModel {

    /**
     * The method to update the drawing of the object.
     * 
     * @param object The object to draw.
     * @param d      The drawing instructions.
     */
    void update(GameObject object, Drawings d);

}
