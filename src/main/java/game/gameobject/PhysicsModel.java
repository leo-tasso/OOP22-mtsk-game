package game.gameobject;

import game.minigame.Minigame;

/**
 * Interface that deals with the state of a GameObject,
 * considering the environment in which it is placed,
 * and the other objects with which it interacts (It is 
 * not in charge of drawing but only to update the objects).
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
