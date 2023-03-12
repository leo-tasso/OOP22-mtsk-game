package game.gameobject.whacamoleobjects;

import game.controlling.InputModel;
import game.gameobject.AspectModel;
import game.gameobject.PhysicsModel;
import game.minigame.whacamoleminigame.Level;

/**
 * Class that models the bomb character in the Whac-a-Mole minigame.
 */
public class Bomb extends WamObject {

    /**
     * Simple constructor aimed at initializing Bomb fields.
     * 
     * @param appearanceTime the time at which it is scheduled to appear
     * @param currentLevel   the level to which the object belongs
     * @param holeNumber     the identifier of the hole assigned to the bomb
     * @param physicsModel   the physics model of the WamObject
     * @param aspectModel    the aspect model of the bomb 
     * @param inputModel     the input model of the WamObject
     */
    public Bomb(long appearanceTime, 
                Level currentLevel, 
                int holeNumber,
                PhysicsModel physicsModel, 
                AspectModel aspectModel, 
                InputModel inputModel) {
        super(appearanceTime, currentLevel, holeNumber, physicsModel, aspectModel, inputModel);
    }
}
