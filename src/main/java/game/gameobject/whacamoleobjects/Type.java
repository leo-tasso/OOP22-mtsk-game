package game.gameobject.whacamoleobjects;

/**
 * Field of each GameObjects that determines whether 
 * the object in question is a mole or a bomb: making 
 * two special classes would not have made sense as 
 * they would have been too similar (DRY principle).
 */
public enum Type {
    BOMB,
    MOLE;
}