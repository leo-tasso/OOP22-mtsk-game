package game.gameobject.whacamoleobjects;

import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.gameobject.AspectModel;
import game.gameobject.GameObject;
import game.gameobject.PhysicsModel;
import game.minigame.whacamoleminigame.Level;

/**
 * Class that models a GameObject belonging to the Whac-a-Mole minigame.
 */
public class WamObject extends GameObject {
    private static final int DELTA_Y = 0;
    
    private final Type type;
    private Status status;
    private final Level level;
    private final int holeNumber;
    private final Point2D startCoor;
    private final long appearanceTime;
    private long motionRestartTime;

}
