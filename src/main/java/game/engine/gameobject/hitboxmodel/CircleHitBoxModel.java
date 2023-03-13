package game.engine.gameobject.hitboxmodel;

import java.util.Optional;

/**
 * HitBoxModel to model a cicular boudary.
 */
public class CircleHitBoxModel implements HitBoxModel {

    private final double size;

    /**
     * Constructor for the model.
     * 
     * @param size the radius of the circle.
     */
    public CircleHitBoxModel(final double size) {
        this.size = size;
    }

    /**
     * @return the radius of the circle.
     */
    @Override
    public double getSize() {
        return size;
    }

    /**
     * Not applicable.
     */
    @Override
    public Optional<Double> getSize1() {
        return Optional.empty();
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
