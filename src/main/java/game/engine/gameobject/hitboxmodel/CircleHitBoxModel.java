package game.engine.gameobject.hitboxmodel;

import java.util.List;

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
    public List<Double> getSizes() {
        return List.of(size);
    }
}
