package game.engine.gameobject.hitboxmodel;

import java.util.Optional;

/**
 * Class to model a rectangle hitbox.
 */
public class RectangleHitBoxModel implements HitBoxModel {

    private final double size;
    private final Optional<Double> size1;

    /**
     * Constructor for the hitbox.
     * 
     * @param side1 the with of the rectangle.
     * @param side2 the height of the rectangle.
     */
    public RectangleHitBoxModel(final double side1, final double side2) {
        this.size = side1;
        this.size1 = Optional.of(side2);

    }

    /**
     * @return the with of the rectangle.
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * @return the height of the rectangle.
     */
    @Override
    public Optional<Double> getSize1() {
        return size1;
    }

    /**
     * Not applicable.
     */
    @Override
    public Optional<Double> getSize2() {
        return Optional.empty();
    }

    /**
     * Not applicable.
     */
    @Override
    public Optional<Double> getSize3() {
        return Optional.empty();
    }
}
