package game.minigame.whacamoleminigame;

/**
 * Class that contains values related to 
 * the second level of the whac-a-mole game.
 */
public class LevelTwo implements Level {
    private static final int MAX_OBJS_OUT_AT_ONCE = 2;
    private static final long EXIT_TIME = 4000L;
    
    @Override
    public int getMaxObjsSimultaneouslyOut() {
        return LevelTwo.MAX_OBJS_OUT_AT_ONCE;
    }

    @Override
    public long getMoleExitPeriod() {
        return LevelTwo.EXIT_TIME;
    }
}