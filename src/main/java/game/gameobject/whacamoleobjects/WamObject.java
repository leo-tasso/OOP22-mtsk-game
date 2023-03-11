package game.gameobject.whacamoleobjects;

import javax.swing.AbstractAction;

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
public abstract class WamObject extends GameObject {
    /* Difference of the Y coordinate between the starting point */
    /* of the object and the most distant point from the hole    */
    /* it reaches (i.e. the one where it will stop for a while)  */
    public static final int DELTA_Y = 20; 

    private Status status;
    private final Level level;
    private final int holeNumber;
    private final Point2D startCoor;
    private final long appearanceTime;
    private long motionRestartTime;

    /**
     * Basic initialization of the various fields.
     * 
     * @param type           the role of this object in the game
     * @param appearanceTime the time it actually comes into play
     * @param currentLevel   lv from which to take the difficulty parameters
     * @param holeNumber     assigned hole number
     */
    public WamObject(long appearanceTime, 
                    Level currentLevel, 
                    int holeNumber, 
                    PhysicsModel physicsModel, 
                    AspectModel aspectModel, 
                    InputModel inputModel) {
        super(HolesCoor.get(), Vector2D.nullVector());
        this.status = Status.WAITING;
        this.level = currentLevel;
        this.holeNumber = holeNumber;
        startCoor = HolesCoor.get();
        motionRestartTime = 0L;
        //TODO Auto-generated constructor stub
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
    public void setStatus(Status status) {
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
