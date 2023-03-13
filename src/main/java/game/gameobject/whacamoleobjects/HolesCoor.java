package game.gameobject.whacamoleobjects;

import api.Point2D;

/**
 * Interface that models a utility class to request 
 * the positions of holes in the playing field.
 */
public interface HolesCoor {

    /**
     * Getter method for the coordinates of a specific hole.
     * 
     * @param holeNumber n of the hole whose position you want
     * @return The coordinates of the center of the hole
     */
    Point2D get(int holeNumber);
}
