package game.engine.gameobject.whacamoleobjects;

/**
 * Class that contains all the possible states that a mole or bomb can assume.
 * 
 * WAITING: The GameObject has been created and is waiting for its time to exit
 * IN_MOTION: The object is either rising from the hole or returning to it
 * HALFWAY: The GameObject is pausing at the highest point of its path
 * HIT: The user hit the object while it was in the open
 * MISSED: The GameObject managed to return unharmed inside its lair
 */
public enum Status {
    WAITING,
    IN_MOTION,
    HALFWAY,
    HIT, 
    MISSED;
}

