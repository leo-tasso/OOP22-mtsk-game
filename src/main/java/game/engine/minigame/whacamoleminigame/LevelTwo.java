package game.engine.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class that contains values related to 
 * the second level of the whac-a-mole game.
 */
public class LevelTwo implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 2;
    private static final double VECTOR_ORDINATE = -50;
    private static final long MIN_WAIT_TO_SPAWN = 5_000L;
    private static final long MAX_WAIT_TO_SPAWN = 12_500L;
    private static final long STATIONARY_TIME = 3000L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelTwo.MAX_OBJS_OUT_AT_ONCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelTwo.VECTOR_ORDINATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(LevelTwo.MIN_WAIT_TO_SPAWN, LevelTwo.MAX_WAIT_TO_SPAWN);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHalfwayTime() {
        return LevelTwo.STATIONARY_TIME;
    }
}
