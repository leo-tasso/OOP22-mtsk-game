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

    /**
     * Basic initialization of the various fields.
     * 
     * @param type           the role of this object in the game
     * @param appearanceTime the time it actually comes into play
     * @param currentLevel   lv from which to take the difficulty parameters
     * @param holeNumber     assigned hole number
     */
    public WamObject(Type type, long appearanceTime, Level currentLevel, int holeNumber) {
        super(HolesCoor.get(), Vector2D.nullVector());
        this.type = type;
        this.status = Status.WAITING;
        this.level = currentLevel;
        this.holeNumber = holeNumber;
        startCoor = HolesCoor.get();
        motionRestartTime = 0L;
        //TODO Auto-generated constructor stub
    }

    /**
     * Getter method for the type of character 
     * this object represents (mole or bomb).
     * 
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

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
