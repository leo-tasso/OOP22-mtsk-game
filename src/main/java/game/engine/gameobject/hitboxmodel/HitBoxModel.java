package game.engine.gameobject.hitboxmodel;

import java.util.Optional;

/**
 * Interface to model an hitboxmodel, the component that dictates the boudary
 * for the collisions of the object.
 */
public interface HitBoxModel {
    /**
     * To get the first size of the object.
     * 
     * @return the first size of the object.
     */
    double getSize();

    /**
     * To get the second size of the object.
     * 
     * @return an Optional of the second size of the object if exists.
     */
    Optional<Double> getSize1();

    /**
     * To get the third size of the object.
     * 
     * @return an Optional of the third size of the object if exists.
     */
    Optional<Double> getSize2();

    /**
     * To get the fourht size of the object.
     * 
     * @return an Optional of the fourht size of the object if exists.
     */
    Optional<Double> getSize3();
}
