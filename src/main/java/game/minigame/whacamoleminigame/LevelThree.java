package game.minigame.whacamoleminigame;

import java.util.Vector;

import api.Vector2D;

/**
 * Class that contains values related to 
 * the third level of the whac-a-mole game.
 */
public class LevelThree implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 3;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelThree.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public Vector2D getObjSpeed() {
        return new Vector2D(0, 100);
    }
}