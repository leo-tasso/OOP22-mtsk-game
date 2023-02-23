package game.gameobject.flappybirdalikeobjects;

import api.ColorRGB;
import game.gameobject.AspectModel;
import game.gameobject.GameObject;
import game.view.Drawings;

/**
* The AspectModel for the Cursor object.
*
*/
public class CursorAspect implements AspectModel {

    private final double size;

    /**
    * The constructor for CursorAspect.
    *
    * @param size the size of the cursor.
    */
    public CursorAspect(final double size) {
        this.size = size;
    }

    /**
    * Updates the aspect state of the cursor.
    *
    * @param object the object to draw.
    * @param drawing the way to draw the cursor.
    */
    @Override
    public void update(final GameObject object, final Drawings drawing) {
        drawing.drawTriangle(object, ColorRGB.blue(), size);
    }
}
