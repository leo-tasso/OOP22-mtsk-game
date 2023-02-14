package game.gameobject.catchthesqareobjects;

import api.ColorRGB;
import game.gameobject.GameObject;
import game.gameobject.RectangleAspect;
import game.view.Drawings;

/**
 * Aspect for for BombSquare objects displays a square with its timer inside.
 */
public class BombSquareAspect extends RectangleAspect {
/**
 * Constructor for the component.
 * @param side the side of the square.
 * @param color the color of the square.
 */
    public BombSquareAspect(final int side, final ColorRGB color) {
        super(side, side, color);
    }
/**
 * Method to draw the object.
 */
    @Override
    public void update(final GameObject object, final Drawings d) {
        if (!(object instanceof BombSquare)) {
            throw new IllegalCallerException("TimerPhysics can be used only on BombSquare or his subclasses");
        }
        super.update(object, d);
        final BombSquare bObj = (BombSquare) object;
        d.drawLabel(object, super.getColor(), getHeight() / 4, Long.toString(bObj.getTimer() / 1000));
    }

}
