package game.controlling;

import game.engine.gameobject.GameObject;
import game.engine.gameobject.whacamoleobjects.Status;
import game.engine.gameobject.whacamoleobjects.WamObject;

/**
 * Class responsible for updating the object (that contains an 
 * instance of it), according to the input received from the View, 
 * notifying in particular whether it has been squashed or not.
 */
public class WamInputModel implements InputModel {

    /**
     * I change the appearance of the hit object, then if it was a mole 
     * I make it go back to its hole, while if it was a bomb the game 
     * will end at the beginning of the next iteration of mainLoop().
     */
    @Override
    public void update(GameObject obj, Input c, long elapsedTime) {
        final WamObject wamObj = (WamObject) obj;
        if (wamObj.getHoleNumber() == c.getNumberPressed().orElse(0)
            && (wamObj.getStatus().equals(Status.IN_MOTION)
            || wamObj.getStatus().equals(Status.HALFWAY))) {
                wamObj.setStatus(Status.HIT);
                if (wamObj.getVel().getY() <= 0) {
                    wamObj.setVel(wamObj.getLevel().getObjSpeed().invert());
                }
        }
    }
}
