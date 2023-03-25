package game.engine.gameobject.hitboxmodel;

import java.util.List;

/**
 * Class to model a null HitBox.
 */
public class NullHitBoxModel implements HitBoxModel {
    /**
     * @return nothing.
     */
    @Override
    public List<Double> getSizes() {
        return List.of(0d);
    }

}
