package game.minigame.whacamoleminigame;

import api.Vector2D;

/**
 * Class that contains values related to 
 * the first level of the whac-a-mole game.
 */
public class LevelOne implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 1;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelOne.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, 20);
    }
}
