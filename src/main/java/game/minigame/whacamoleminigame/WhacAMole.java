package game.minigame.whacamoleminigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import game.gameobject.GameObject;
import game.gameobject.whacamoleobjects.Bomb;
import game.gameobject.whacamoleobjects.Mole;
import game.gameobject.whacamoleobjects.Status;
import game.minigame.Minigame;

/**
 * Class that implements the Whac-a-mole minigame logic.
 */
public class WhacAMole implements Minigame {
    private static final int NUM_HOLES = 9;
    private static final long TIME_TO_NEXT_LEVEL = 24000L;
    private static final long TIME_BETWEEN_DRAWS = 8000L;

    private List<Mole> moles; 
    private List<Bomb> bombs;
    private final List<Level> levels; 
    private final long startTime;
    private DrawStrategy draw;
    private Optional<Long> timeLastDraw; 
    private Level currentLevel;

    /**
     * General initialization of the fields, the start time is 
     * requested in order to calculate when to advance in level.
     * 
     * @param startTime The instant in which the user starts playing (in ms)
     */
    public WhacAMole(final long startTime) {
        this.startTime = startTime;
        this.levels = List.of(new LevelOne(), new LevelTwo(), new LevelThree());
        this.currentLevel = this.levels.get(0);
        this.draw = new DrawStrategyImpl();
        this.timeLastDraw = Optional.empty();
    }

    /**
     * Checks if one of the bombs in the game was hit by the user 
     * or if a mole managed to re-enter its hole without being 
     * crushed (both sufficient conditions for the game to end).
     * 
     * @return whether the game is over or not
     */
    @Override
    public boolean isGameOver() { 
        return this.moles.stream().map(Mole::getStatus).anyMatch(s -> s.equals(Status.MISSED))
            || this.bombs.stream().map(Bomb::getStatus).anyMatch(s -> s.equals(Status.HIT));
    } 

}
