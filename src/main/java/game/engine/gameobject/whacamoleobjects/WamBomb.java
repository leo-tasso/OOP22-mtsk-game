package game.engine.gameobject.whacamoleobjects;

import api.Point2D;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.InputModel;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.whacamoleminigame.Level;

/**
 * Class that models the bomb character in the Whac-a-Mole minigame.
 */
public class WamBomb extends WamObject {

    /**
     * Simple constructor aimed at initializing Bomb fields.
     * 
     * @param startCoor      the initial coordinates
     * @param appearanceTime the time at which it is scheduled to appear
     * @param currentLevel   the level to which the object belongs
     * @param holeNumber     the identifier of the hole assigned to the bomb
     * @param physicsModel   the physics model of the WamObject
     * @param aspectModel    the aspect model of the bomb 
     * @param inputModel     the input model of the WamObject
     */
    public WamBomb(final Point2D startCoor,
                final long appearanceTime, 
                final Level currentLevel, 
                final int holeNumber,
                final PhysicsModel physicsModel, 
                final AspectModel aspectModel, 
                final InputModel inputModel) {
        super(startCoor, appearanceTime, currentLevel, holeNumber, physicsModel, aspectModel, inputModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.getStatus().equals(Status.HIT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStillInUse() {
        return !this.getStatus().equals(Status.MISSED);
    }
}
