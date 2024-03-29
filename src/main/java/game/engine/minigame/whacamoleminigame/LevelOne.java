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
    private static final long MIN_WAIT_TO_SPAWN = 5_000L;
    private static final long MAX_WAIT_TO_SPAWN = 15_000L;
    private static final long STATIONARY_TIME = 5000L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelOne.MAX_OBJS_OUT_AT_ONCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelOne.VECTOR_ORDINATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(LevelOne.MIN_WAIT_TO_SPAWN, LevelOne.MAX_WAIT_TO_SPAWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHalfwayTime() {
        return LevelOne.STATIONARY_TIME;
    }
}
