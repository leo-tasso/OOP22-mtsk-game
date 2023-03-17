package game.engine.gameobject.dodgeatriangleobjects;

import api.ColorRGB;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * Class that deals with drawing the triangle, 
 * also specifying its rotation according 
 * to the direction of its motion.
 */
public class DatTriangleAspectModel implements AspectModel {
    
    private final int side;
    private final boolean rightSpawn;

    /**
     * Constructor for the triangle's aspect model.
     * 
     * @param side       the side of the triangle (equilateral)
     * @param rightSpawn true if the triangle moves from right to left
     */
    public DatTriangleAspectModel(final int side, final boolean rightSpawn) {
        this.side = side;
        this.rightSpawn = rightSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject object, final Drawings drawing) {
        drawing.drawTriangle(object, ColorRGB.green(), side, rightSpawn ? Math.PI : 0);
    }
}
