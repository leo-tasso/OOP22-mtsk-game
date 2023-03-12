package game.engine.gameobject.hitboxmodel;

import java.util.Optional;

/**
 * Class to model a null HitBox.
 */
public class NullHitBoxModel implements HitBoxModel {
    /**
     * @return nothing.
     */
    @Override
    public double getSize() {
        return 0;
    }

    /**
     * @return nothing.
     */
    @Override
    public Optional<Double> getSize1() {
        return Optional.empty();
    }

    /**
     * @return nothing.
     */
    @Override
    public Optional<Double> getSize2() {
        return Optional.empty();
    }

    /**
     * @return nothing.
     */
    @Override
    public Optional<Double> getSize3() {
        return Optional.empty();
    }

}
