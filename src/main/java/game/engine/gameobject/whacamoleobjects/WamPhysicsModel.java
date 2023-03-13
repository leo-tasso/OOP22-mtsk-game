package game.engine.gameobject.whacamoleobjects;

import api.Vector2D;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.PhysicsModel;
import game.engine.gameobject.SimplePhysics;
import game.engine.minigame.Minigame;
import game.engine.minigame.whacamoleminigame.WhacAMole;

/**
 * Class that deals with the physics of 
 * objects in the Whac-A-Mole minigame.
 */
public class WamPhysicsModel implements PhysicsModel {

    /**
     * Method that manages the vertical translations of 
     * the GameObjects, also checking if they have reached 
     * their maximum position (and if so, it stops them).
     */
    @Override
    public void update(long dt, GameObject obj, Minigame miniGame) {
        final WamObject wamObj = (WamObject) obj;
        final long currentTime = ((WhacAMole) miniGame).getCurrentTime();
        new SimplePhysics().update(dt, wamObj, miniGame);
        if (wamObj.getCoor().getY() >= wamObj.getStartCoor().getY() + WamObject.DELTA_Y) {
            wamObj.setStatus(Status.HALFWAY);
            wamObj.setVel(Vector2D.nullVector());
            wamObj.setMotionRestartTime(currentTime + wamObj.getLevel().getHalfwayTime());
        }
    }
}
