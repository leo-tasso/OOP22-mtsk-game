package game.engine.gameobject.dodgeatriangleobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.RectangleAspect;
import game.engine.gameobject.hitboxmodel.SquareHitBoxModel;

/**
 * Class that models the character played by the user, i.e. 
 * a rectangle that moves vertically and that has to dodge 
 * the triangles that cross the playing field horizontally.
 */
public class Dodger extends GameObject {

    /**
     * Simple constructor for Dodger fields.
     * 
     * @param height
     * @param size
     * @param inputModel
     */
    public Dodger(final int height, final int size, final InputModel inputModel) {
        super(new Point2D(height * 8 / 9, height / 2), Vector2D.nullVector());
        this.setInputModel(inputModel);
        this.setPhysicsModel(null);
        this.setAspectModel(new RectangleAspect(size, size, ColorRGB.aqua()));
        this.setHitBoxModel(new SquareHitBoxModel(size));
    }
}