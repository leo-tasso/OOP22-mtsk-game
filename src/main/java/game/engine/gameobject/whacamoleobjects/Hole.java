package game.engine.gameobject.whacamoleobjects;

import api.Point2D;
import game.controlling.InputModel;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.whacamoleminigame.Level;

/**
 * Class that models the hole item in the Whac-a-Mole minigame.
 */
public class Hole extends WamObject {

    /**
     * Simple constructor aimed at initializing Hole fields.
     * 
     * @param coor           the coordinates it will keep for the whole game
     * @param appearanceTime the start time of the game
     * @param currentLevel   unused value
     * @param holeNumber     the number that identifies it
     * @param physicsModel   the null physics model of the hole (it doesn't move)
     * @param aspectModel    the aspect model of the hole 
     * @param inputModel     the null input model of the hole 
     */
    public Hole(final Point2D coor,
                final long appearanceTime, 
                final Level currentLevel, 
                final int holeNumber, 
                final PhysicsModel physicsModel,
                final AspectModel aspectModel, 
                final InputModel inputModel) {
        super(coor, appearanceTime, currentLevel, holeNumber, physicsModel, aspectModel, inputModel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStillInUse() {
        return true;
    }
}
