package game.engine.gameobject.whacamoleobjects;

import api.Point2D;
import game.controlling.InputModel;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.whacamoleminigame.Level;

/**
 * Class that represents the upper part of the hole or rather 
 * the semicircle that gives a 3D effect, above which moles and 
 * bombs will have to be displayed. Having a different appearance 
 * than the bottom, and having to keep it in a different 
 * position on the GameObject list, it has a class of its own.
 */
public class HoleUpperPart extends HolePart {

    /**
     * Simple constructor aimed at initializing hole's upper part.
     * 
     * @param coor           the coordinates it will keep for the whole game
     * @param appearanceTime the start time of the game
     * @param currentLevel   unused value
     * @param holeNumber     the number that identifies it
     * @param physicsModel   the null physics model of the hole (it doesn't move)
     * @param aspectModel    the aspect model of the upper part of the hole 
     * @param inputModel     the null input model of the hole 
     */
    public HoleUpperPart(final Point2D coor,
                final long appearanceTime, 
                final Level currentLevel, 
                final int holeNumber, 
                final PhysicsModel physicsModel,
                final AspectModel aspectModel, 
                final InputModel inputModel) {
        super(coor, appearanceTime, currentLevel, holeNumber, physicsModel, aspectModel, inputModel);
    }
}
