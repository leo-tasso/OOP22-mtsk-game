package game.engine.gameobject.whacamoleobjects;

/**
 * Class that contains all the possible states that a mole or bomb can assume.
 */
public enum Status {

    /**
     * The GameObject has been created and is waiting for its time to exit.
     */
    WAITING,

    /**
     * The object is either rising from the hole or returning to it.
     */
    IN_MOTION,

    /**
     * The GameObject is pausing at the highest point of its path.
     */
    HALFWAY,

    /**
     * The user hit the object while it was in the open.
     */
    HIT,

    /**
     * The GameObject managed to return unharmed inside its lair.
     */
    MISSED;
}
