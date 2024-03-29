package game.engine.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class that contains values related to 
 * the third level of the whac-a-mole game.
 */
public class LevelThree implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 3;
    private static final double VECTOR_ORDINATE = -100;
    private static final long MIN_WAIT_TO_SPAWN = 5_000L;
    private static final long MAX_WAIT_TO_SPAWN = 10_000L;
    private static final long STATIONARY_TIME = 2000L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelThree.MAX_OBJS_OUT_AT_ONCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelThree.VECTOR_ORDINATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(LevelThree.MIN_WAIT_TO_SPAWN, LevelThree.MAX_WAIT_TO_SPAWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHalfwayTime() {
        return LevelThree.STATIONARY_TIME;
    }
}
