package game.minigame.whacamoleminigame;

/**
 * Class that contains values related to 
 * the first level of the whac-a-mole game.
 */
public class LevelOne implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 1;
    private static final long EXIT_TIME = 5000L;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelOne.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public long getObjExitPeriod() {
        return LevelOne.EXIT_TIME;
    }
}
