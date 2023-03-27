package game.engine.gameobject.whacamoleobjects;

import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.engine.gameobject.AspectModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.PhysicsModel;
import game.engine.minigame.whacamoleminigame.Level;

/**
 * Class that models objects belonging to the Whac-a-Mole minigame.
 * If I hadn't created this class, extending the characters' classes 
 * directly from GameObject, I would have violated the DRY principle,
 * since many functions would have had the same implementation.
 */
public abstract class WamObject extends GameObject {
    /**
     * Difference of the Y coordinate between the starting point 
     * of the object and the most distant point from the hole
     * it reaches (i.e. the one where it will stop for a while)
     */
    public static final int DELTA_Y = 110; 

    private Status status;
    private final Level level;
    private final int holeNumber;
    private final Point2D startCoor;
    private final long appearanceTime;
    private long motionRestartTime;

    /**
     * Basic initialization of the various fields.
     * 
     * @param startCoor      the initial coordinates of the object
     * @param appearanceTime the time it actually comes into play
     * @param currentLevel   lv from which to take the difficulty parameters
     * @param holeNumber     assigned hole number
     * @param physicsModel   the physicsModel of the object
     * @param aspectModel    the aspectModel of the object
     * @param inputModel     the inputModel of the object
     */
    public WamObject(final Point2D startCoor,
                    final long appearanceTime, 
                    final Level currentLevel,
                    final int holeNumber,
                    final PhysicsModel physicsModel,
                    final AspectModel aspectModel,
                    final InputModel inputModel) {
        super(startCoor, Vector2D.nullVector(), 0, inputModel, physicsModel, aspectModel);
        this.status = Status.WAITING;
        this.level = currentLevel;
        this.holeNumber = holeNumber;
        this.appearanceTime = appearanceTime;
        this.startCoor = startCoor;
        motionRestartTime = 0L;
    }

    /**
     * Method that checks whether or not the object caused the 
     * GameOver, with a different check routine depending on 
     * the character type of the class implementing WamObject.
     * 
     * @return whether the game is over or not
     */
    public abstract boolean isGameOver();

    /**
     * Check if the object has already performed its task 
     * in the game and has therefore become irrelevant 
     * (and that it is not the cause of GameOver).
     * 
     * @return whether the object is still in use or not
     */
    public abstract boolean isStillInUse();

    /**
     * Getter method for the current 
     * logical state of the GameObject.
     * 
     * @return the status
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Setter method for the current 
     * logical state of the GameObject.
     * 
     * @param status the new status
     */
    public void setStatus(final Status status) {
        this.status = status;
    }

    /**
     * Getter method for the number of the 
     * hole the object will come out of.
     * 
     * @return the hole assigned to this object
     */
    public int getHoleNumber() {
        return this.holeNumber;
    }

    /**
     * Getter method for the coordinates from which the 
     * object starts its motion (just above the hole).
     * 
     * @return the starting point (x,y)
     */
    public Point2D getStartCoor() {
        return this.startCoor;
    }

    /**
     * Getter method for the time instant in which 
     * the object will be in its initial coordinates.
     * 
     * @return the time of appearance (in ms)
     */
    public long getAppearanceTime() {
        return this.appearanceTime;
    }

    /**
     * Getter method for the instant of time in which the 
     * object, after having stopped for a certain amount of time 
     * outside the hole, restarts its motion to return inside.
     * 
     * @return the time it starts to go back to the den (in ms)
     */
    public long getMotionRestartTime() {
        return this.motionRestartTime;
    }

    /**
     * Method to set the motion restart time, 
     * calculated once the object reaches 
     * the highest point of its journey.
     * 
     * @param l the time it starts to go back 
     * to the den (in ms)
     */
    public void setMotionRestartTime(final long l) {
        this.motionRestartTime = l;
    }

    /**
     * Getter method for the level to 
     * which this object belongs.
     * 
     * @return the level of difficulty
     */
    public Level getLevel() {
        return this.level;
    }
}
