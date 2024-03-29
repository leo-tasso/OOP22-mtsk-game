package game.engine.gameobject.dodgeatriangleobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.InputModel;
import game.engine.gameobject.RectangleAspect;
import game.engine.gameobject.SimplePhysics;
import game.engine.gameobject.hitboxmodel.SquareHitBoxModel;

/**
 * Class that models the character played by the user, i.e. 
 * a rectangle that moves vertically and that has to dodge 
 * the triangles that cross the playing field horizontally.
 */
public class Dodger extends GameObject {

    private static final double RATIO = 16 / 9.0;

    /**
     * Simple constructor for Dodger fields.
     * 
     * @param initialY    the initial Y coordinate
     * @param size        the side of the square
     * @param inputModel  the dodger's input model
     */
    public Dodger(final int initialY, final int size, final InputModel inputModel) {
        super(new Point2D(initialY * RATIO, initialY), Vector2D.nullVector());
        this.setInputModel(inputModel);
        this.setPhysicsModel(new SimplePhysics());
        this.setAspectModel(new RectangleAspect(size, size, ColorRGB.red(), true));
        this.setHitBoxModel(new SquareHitBoxModel(size));
    }
}
