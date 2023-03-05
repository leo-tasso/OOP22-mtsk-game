package game.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class that contains values related to 
 * the third level of the whac-a-mole game.
 */
public class LevelThree implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 3;
    private static final double VECTOR_ORDINATE = -100;
    private static final long MAX_WAIT_TO_SPAWN = 5000L;
    private static final long STATIONARY_TIME = 2000L;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelThree.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelThree.VECTOR_ORDINATE);
    }

    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(0, LevelThree.MAX_WAIT_TO_SPAWN);
    }

    @Override
    public long getHalfwayTime() {
        return LevelThree.STATIONARY_TIME;
    }
}