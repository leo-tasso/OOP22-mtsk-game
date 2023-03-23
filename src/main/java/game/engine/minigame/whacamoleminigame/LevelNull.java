package game.engine.minigame.whacamoleminigame;

import api.TimeInterval;
import api.Vector2D;

/**
 * Class representing a level to be assigned 
 * to objects independent of levels.
 */
public class LevelNull implements Level {

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getObjSpeed() {
        return Vector2D.nullVector();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TimeInterval getSpawnWaitingTime() {
        return new TimeInterval(0L, 0L); 
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getHalfwayTime() {
        return 0L;
    }   
}
