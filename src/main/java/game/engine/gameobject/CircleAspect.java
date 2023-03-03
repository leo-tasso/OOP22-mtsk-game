package game.engine.gameobject;

import api.ColorRGB;
import game.view.Drawings;

/**
 * An aspect model class to draw a circle.
 */
public class CircleAspect implements AspectModel {

    private final double radius;
    private final ColorRGB color;

    /**
     * The constructor for the circle.
     * 
     * @param radius the radius of the circle
     * @param color the color of the circle.
     */
    public CircleAspect(final double radius, final ColorRGB color) {
        this.radius = radius;
        this.color = color;
    }

    /**
     * The method to draw the circle according the Drawing class.
     */
    @Override
    public void update(final GameObject object, final Drawings drawing) {
        drawing.drawCircle(object, color, radius);
    }

}
