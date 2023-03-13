package game.engine.gameobject.whacamoleobjects;

import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * The AspectModel for the Mole object.
 */
public class MoleAspectModel implements AspectModel {

    /**
     * I order to draw the Mole only if it has actually 
     * entered the game, i.e. if it has already been 
     * spawned, in which case I distinguish the object's 
     * appearance based on whether it has been hit or not.
     */
    @Override
    public void update(GameObject object, Drawings drawing) {
        final Status status = ((WamObject) object).getStatus();
        if (!status.equals(Status.WAITING)) {
            if (status.equals(Status.HIT)) {
                drawing.drawMole(object, true);
            } else {
                drawing.drawMole(object, false);
            }
        } 
    }   
}
