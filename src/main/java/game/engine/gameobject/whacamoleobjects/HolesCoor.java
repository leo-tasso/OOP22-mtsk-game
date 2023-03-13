package game.engine.gameobject.whacamoleobjects;

import java.util.HashMap;
import java.util.Map;
import api.Point2D;

/**
 * Utility class from which to extract information 
 * about the position of the holes in the playing field.
 */
public class HolesCoor {
    private final Map<Integer, Point2D> holesCoor = new HashMap<>(
        //INSERT VALUES
    );

    /**
     * Getter method for the coordinates of a specific hole.
     * 
     * @param holeNumber n of the hole whose position you want
     * @return The coordinates of the center of the hole
     */
    Point2D get(int holeNumber) {
        return this.holesCoor.get(holeNumber);
    }
}
