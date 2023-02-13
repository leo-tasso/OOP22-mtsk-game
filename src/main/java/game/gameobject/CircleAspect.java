package game.gameobject;

import api.ColorRGB;
import game.view.Drawings;

/**
 * An aspect model class to draw a circle.
 */
public class CircleAspect implements AspectModel {

    private final double radius;

    /**
     * The constructor for the circle.
     * 
     * @param radius the radius of the circle
     */
    public CircleAspect(final double radius) {
        this.radius = radius;
    }

    /**
     * The method to draw the circle according the Drawing class.
     */
    @Override
    public void update(final GameObject object, final Drawings drawing) {
        drawing.drawCircle(object, ColorRGB.green(), radius);
    }

}
