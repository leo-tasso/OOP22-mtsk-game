package game.gameobject.catchthesqareobjects;

import api.ColorRGB;
import game.gameobject.GameObject;
import game.gameobject.RectangleAspect;
import game.view.Drawings;

/**
 * Aspect for for a Bomb object, displayed with its timer inside.
 */
public class BombAspect extends RectangleAspect {
/**
 * Constructor for the component.
 * @param side the side of the object.
 * @param color the color of the object.
 */
    public BombAspect(final int side, final ColorRGB color) {
        super(side, side, color);
    }
/**
 * Method to draw the object.
 */
    @Override
    public void update(final GameObject object, final Drawings d) {
        if (!(object instanceof Bomb)) {
            throw new IllegalCallerException("TimerPhysics can be used only on Bomb or his subclasses");
        }
        super.update(object, d);
        final Bomb bObj = (Bomb) object;
        d.drawLabel(object, super.getColor(), getHeight() / 4, Long.toString(bObj.getTimer() / 1000));
    }

}
