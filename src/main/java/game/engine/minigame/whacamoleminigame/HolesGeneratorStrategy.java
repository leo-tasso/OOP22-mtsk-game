package game.engine.minigame.whacamoleminigame;

import java.util.List;
import game.engine.gameobject.whacamoleobjects.WamObject;

/**
 * Interface for the burrow placement and creation algorithm.
 */
public interface HolesGeneratorStrategy {

    /**
     * Method responsible for placing and making holes.
     * 
     * @param numHoles the number of dens to creates
     * @return a list containing the holes created
     */
    List<WamObject> generate(int numHoles);
}
