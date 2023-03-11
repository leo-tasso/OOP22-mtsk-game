package game.controlling;

import game.gameobject.GameObject;
import game.gameobject.whacamoleobjects.Status;
import game.gameobject.whacamoleobjects.WamObject;

/**
 * Class responsible for updating the object (that contains an 
 * instance of it), according to the input received from the View, 
 * notifying in particular whether it has been squashed or not.
 */
public class WamInputModel implements InputModel {

    /**
     * In case there is an object outside 
     * the user's selected hole, I hit it.
     */
    @Override
    public void update(GameObject obj, Input c, long elapsedTime) {
        final WamObject wamObj = (WamObject) obj;
        if (wamObj.getHoleNumber() == c.getNumberPressed().orElse(0)
            && (wamObj.getStatus().equals(Status.IN_MOTION)
            || wamObj.getStatus().equals(Status.HALFWAY))) {
                wamObj.hit();
        }
    }
}
