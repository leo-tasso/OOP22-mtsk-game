package game.engine.gameobject.whacamoleobjects;

import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.SimplePhysics;
import game.engine.minigame.Minigame;
import game.engine.minigame.whacamoleminigame.WhacAMole;

/**
 * Class that deals with the physics of
 * objects in the Whac-A-Mole minigame.
 */
public class WamPhysicsModel extends SimplePhysics {

    /**
     * Method that manages the vertical translations of
     * the GameObjects, also checking if they have reached
     * their maximum position (and if so, it stops them).
     */
    @Override
    public void update(final long dt, final GameObject obj, final Minigame miniGame) {
        super.update(dt, obj, miniGame); 
        final WamObject wamObj = (WamObject) obj; //TODO  istanceof
        final long currentTime = ((WhacAMole) miniGame).getCurrentTime();
        if (wamObj.getCoor().getY() <= wamObj.getStartCoor().getY() - WamObject.DELTA_Y
                && !wamObj.getStatus().equals(Status.HALFWAY)) {
            wamObj.setStatus(Status.HALFWAY);
            wamObj.setVel(Vector2D.nullVector());
            wamObj.setMotionRestartTime(currentTime + wamObj.getLevel().getHalfwayTime());
        }
    }
}
