package game.engine.gameobject.whacamoleobjects;

import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * The AspectModel for the Hole's lower section.
 */
public class HoleLowerPartAspectModel implements AspectModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final GameObject object, final Drawings drawing) {
        drawing.drawHoleLowerPart(object);
    }
}
