package game.engine.gameobject.catchthesqareobjects;

import api.ColorRGB;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.RectangleAspect;
import game.view.Drawings;

/**
 * Aspect for for a Bomb object, displayed with its timer inside.
 */
public class CtsBombAspect extends RectangleAspect {
    private static final int WARING_SECONDS = 5;
    private static final int FONT_SIZE_REDUCTION = 4;
    private final ColorRGB previousColor;
    private final ColorRGB warningColor = ColorRGB.red();

    /**
     * Constructor for the component.
     * 
     * @param side  the side of the object.
     * @param color the color of the object.
     */
    public CtsBombAspect(final int side, final ColorRGB color) {
        super(side, side, color);
        previousColor = color;
    }

    /**
     * Method to draw the object.
     */
    @Override
    public void update(final GameObject object, final Drawings d) {
        if (!(object instanceof CtsBomb)) {
            throw new IllegalCallerException("TimerPhysics can be used only on Bomb or his subclasses");
        }
        final CtsBomb bObj = (CtsBomb) object;
        if (bObj.getTimer() < WARING_SECONDS * 1000 && (long) (bObj.getTimer() / 100) % 2 == 0) {
            super.setColor(warningColor);
        } else {
            super.setColor(previousColor);
        }
        super.update(object, d);
        d.drawLabel(object, super.getColor(), getHeight() / FONT_SIZE_REDUCTION, Long.toString(bObj.getTimer() / 1000));
    }

}
