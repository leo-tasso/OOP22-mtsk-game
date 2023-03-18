package game.engine.gameobject.whacamoleobjects;

import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * The AspectModel for the Hole's upper section.
 */
public class HoleUpperPartAspectModel implements AspectModel {

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(GameObject object, Drawings drawing) {
        drawing.drawHoleUpperPart(object);
    }   
}
