package game.engine.gameobject.hitboxmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to model a rectangle hitbox.
 */
public class RectangleHitBoxModel implements HitBoxModel {

    private final List<Double> sizes;

    /**
     * Constructor for the hitbox.
     * 
     * @param side1 the with of the rectangle.
     * @param side2 the height of the rectangle.
     */
    public RectangleHitBoxModel(final double side1, final double side2) {
        sizes = List.of(side1, side2);
    }

    /**
     * @return the sizes of the rectangle.
     */
    @Override
    public List<Double> getSizes() {
        return new ArrayList<>(sizes);
    }

}
