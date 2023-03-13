package game.engine.gameobject.hitboxmodel;

/**
 * Class to model a square hitbox.
 */
public class SquareHitBoxModel extends RectangleHitBoxModel {
    /**
     * Constructor for the hitbox.
     * 
     * @param size the side of the square.
     */
    public SquareHitBoxModel(final double size) {
        super(size, size);
    }
}
