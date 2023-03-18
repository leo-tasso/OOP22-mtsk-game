package game.engine.gameobject.dodgeatriangleobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * An aspect model class to visualize cells in DodgeATriangle.
 */
public class SlotAspect implements AspectModel {

    private final double side;
    private final Point2D center;
    private final int numSlots;
    private final int slotOffset;

    /**
     * Simple constructor for the SlotAspect class. 
     * slotOffset represents the number of spaces 
     * above the dodger at the start of the game 
     * (also equal to those below).
     * 
     * @param side     square side of a single slot
     * @param center   the coordinates of the center of the playing field
     * @param numSlots number of slots
     */
    public SlotAspect(final double side, final Point2D center, final int numSlots) {
        this.side = side;
        this.center = center;
        this.numSlots = numSlots;
        this.slotOffset = numSlots / 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject obj, final Drawings drawing) {
        for (int i = 0; i < numSlots; i++) {
            drawing.drawSquare(
                new GameObject(center.sum(new Point2D(0, (i - slotOffset) * side)),
                Vector2D.nullVector()),
                ColorRGB.black(),
                side,
                false);
        }
    }
}
