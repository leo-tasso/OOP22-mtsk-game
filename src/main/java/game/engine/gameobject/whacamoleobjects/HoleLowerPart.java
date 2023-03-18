package game.engine.gameobject.whacamoleobjects;

import api.Point2D;
import game.controlling.InputModel;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.whacamoleminigame.Level;

/**
 * Class that represents the lower part of the hole, 
 * i.e. the "tube" from which moles and bombs come out 
 * and re-enter, and which therefore must hide these.
 */
public class HoleLowerPart extends HolePart {

    /**
     * Simple constructor aimed at initializing the hole's lower part.
     * 
     * @param coor           the coordinates it will keep for the whole game
     * @param appearanceTime the start time of the game
     * @param currentLevel   unused value
     * @param holeNumber     the number that identifies it
     * @param physicsModel   the null physics model of the hole (it doesn't move)
     * @param aspectModel    the aspect model of the lower part of the hole 
     * @param inputModel     the null input model of the hole 
     */
    public HoleLowerPart(final Point2D coor,
                final long appearanceTime, 
                final Level currentLevel, 
                final int holeNumber, 
                final PhysicsModel physicsModel,
                final AspectModel aspectModel, 
                final InputModel inputModel) {
        super(coor, appearanceTime, currentLevel, holeNumber, physicsModel, aspectModel, inputModel);
    }
}
