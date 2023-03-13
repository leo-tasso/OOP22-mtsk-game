package game.gameobject.whacamoleobjects;

import game.gameobject.AspectModel;

/**
 * Being that one thing that unites all the objects 
 * of the Whac-a-mole game is the fact that they have 
 * to change appearance when they are hit, I use this 
 * class as an interface for specific AspectModels.
 */
public abstract class WamAspectModel implements AspectModel {
    private boolean beenHit = false;

    /**
     * Reverses the current appearance of the GameObject 
     * (I don't need to specify anything since 
     * there are only two of them per GameObject).
     */
    public void hit() {
        this.beenHit = true;
    }
}
