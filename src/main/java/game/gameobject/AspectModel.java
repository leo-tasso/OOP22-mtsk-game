package game.gameobject;

import game.view.Drawings;

/**
 * The interface in charge of drawing each GameObject.
 */
public interface AspectModel {

    /**
     * The method that performs the re-drawing of an object 
     * (every "frame"), which happens regardless of the 
     * inputs received, since objects move by themselves,
     * following the strategy contained in the Drawings parameter.
     * 
     * @param object The object to draw.
     * @param d      The drawing instructions.
     */
    void update(GameObject object, Drawings d);

}
