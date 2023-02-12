package game.gameobject;

import api.ColorRGB;
import game.view.Drawings;

/**
 * An aspect model class to draw a circle.
 */
public class CircleAspect implements AspectModel {

    /**
     * The method to draw the circle according the Drawing class.
     */
    @Override
    public void update(final GameObject object, final Drawings d) {
        d.drawCircle(object, ColorRGB.green());
    }

}
