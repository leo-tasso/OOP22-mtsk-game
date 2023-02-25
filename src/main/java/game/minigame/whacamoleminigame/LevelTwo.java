package game.minigame.whacamoleminigame;

import api.Vector2D;

/**
 * Class that contains values related to 
 * the second level of the whac-a-mole game.
 */
public class LevelTwo implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 2;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelTwo.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, 50);
    }
}