package game.gameobject;

import api.ColorRGB;
import game.view.Drawings;
/**
 * Class to model the aspect of a Rectangle GameObject.
 */
public class Rectangle implements AspectModel {
    private int width;
    private int heigh;

    public Rectangle(int width, int heigh) {
        this.width = width;
        this.heigh = heigh;
    }

    @Override
    public void update(GameObject object, Drawings d) {
        d.drawRectangle(object, ColorRGB.white(), width, heigh);        
    }
    
}
