package game.gameobject;

import game.minigame.Minigame;

/**
 * 
 */
public interface PhysicsModel {
    /**
     * Method to update the physics state of the gameObject.
     * 
     * @param dt       the elapsed time between frames.
     * @param obj      this gameObject.
     * @param miniGame the minigame of the gameObject.
     */
    void update(long dt, GameObject obj, Minigame miniGame);
}
