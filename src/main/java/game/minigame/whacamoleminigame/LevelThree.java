package game.minigame.whacamoleminigame;

/**
 * Class that contains values related to 
 * the third level of the whac-a-mole game.
 */
public class LevelThree implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 3;
    private static final long EXIT_TIME = 3000L;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelThree.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public long getObjExitPeriod() {
        return LevelThree.EXIT_TIME;
    }
}