package game.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class that contains values related to 
 * the second level of the whac-a-mole game.
 */
public class LevelTwo implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 2;
    private static final double VECTOR_ORDINATE = 50;
    private static final long MAX_WAIT_TO_SPAWN = 10000L;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelTwo.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, LevelTwo.VECTOR_ORDINATE);
    }

    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(0, LevelTwo.MAX_WAIT_TO_SPAWN);
    }
}