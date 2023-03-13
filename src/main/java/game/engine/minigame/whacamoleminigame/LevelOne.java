package game.engine.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class that contains values related to 
 * the first level of the whac-a-mole game.
 */
public class LevelOne implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 1;
    private static final double VECTOR_ORDINATE = -20;
    private static final long MAX_WAIT_TO_SPAWN = 15000L;
    private static final long STATIONARY_TIME = 5000L;

    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelOne.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelOne.VECTOR_ORDINATE);
    }

    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(0, LevelOne.MAX_WAIT_TO_SPAWN);
    }

    @Override
    public long getHalfwayTime() {
        return LevelOne.STATIONARY_TIME;
    }
}
