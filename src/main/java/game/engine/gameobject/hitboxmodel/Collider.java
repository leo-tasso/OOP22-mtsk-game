package game.engine.gameobject.hitboxmodel;

import game.engine.gameobject.GameObject;

/**
 * Class to check if two {@link GameObject} are colliding.
 */
public interface Collider {
    /**
     * Checks if two {@link GameObject} are colliding.
     * 
     * @param g the first {@link GameObject}.
     * @param h the second {@link GameObject}.
     * @return if the two {@link GameObject} are colliding.
     */
    boolean isColliding(GameObject g, GameObject h);
}
