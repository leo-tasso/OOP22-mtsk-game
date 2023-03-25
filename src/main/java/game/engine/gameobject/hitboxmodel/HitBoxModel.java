package game.engine.gameobject.hitboxmodel;

import java.util.List;

/**
 * Interface to model an hitboxmodel, the component that dictates the boudary
 * for the collisions of the object.
 */
public interface HitBoxModel {
    /**
     * To get the sizes of the object's hitbox.
     * 
     * @return the list of sizes of the hitbox.
     */
    List<Double> getSizes();
}
