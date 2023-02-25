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

    /**
     * At this point the logical state of the GameObjects will have already 
     * been updated by user's input, so I update their physics accordingly, 
     * and check if it's time to level up and/or create new Objs to appear.
     * 
     * @param elapsed time elapsed since the last frame
     */
    @Override
    public void compute(final long elapsed) {
        /* I have to use two streams because if I generalize the type  */
        /* to GameObject I could not do the check on the state of      */
        /* the objects so I would have to create 2 classes for physics */
        this.moles.stream().filter(m -> m.getStatus().equals(Status.IN_MOTION)).forEach(m -> m.updatePhysics(elapsed, this));
        this.bombs.stream().filter(b -> b.getStatus().equals(Status.IN_MOTION)).forEach(b -> b.updatePhysics(elapsed, this));
        this.deleteOldObjs();
        this.calculateLevel(System.currentTimeMillis());

        final Long timeNextDraw = this.timeLastDraw.orElse(0L) + TIME_BETWEEN_DRAWS;
        if (timeLastDraw.isEmpty() || timeNextDraw <= System.currentTimeMillis()) {
            final long upperBond = timeNextDraw + TIME_BETWEEN_DRAWS - currentLevel.getObjExitPeriod();
            /* Having to assign a random appearance time to     */
            /* the objects, I need to know what is the maximum  */
            /* instant in which to start its exit from the hole */
            this.draw.draw(this.currentLevel, upperBond, NUM_HOLES).stream()
                .forEach(o -> {
                    if (o instanceof Mole) {
                        moles.add((Mole) o);
                    } else {
                        bombs.add((Bomb) o);
                    } 
                });
        }
    }

    /**
     * Returns all the GameObjects currently in use in the game.
     * 
     * @return the list of GameObjects
     */
    @Override
    public List<GameObject> getGameObjects() {
        final List<GameObject> retList = new ArrayList<>(moles);
        retList.addAll(bombs);
        return retList;
    }

    /**
     * Method for calculating the difficulty level based on how 
     * long the user is in the game. If there are no more levels 
     * available to advance, then the user will stay on the last one.
     * 
     * @param currentTimeMillis
     */
    private void calculateLevel(final long currentTime) {
        final int levelIndex = (int) (currentTime - this.startTime) / (int) TIME_TO_NEXT_LEVEL;
        if (levelIndex > this.levels.size() - 1) {
            this.currentLevel = this.levels.get(this.levels.size() - 1);
        } else {
            this.currentLevel = this.levels.get(levelIndex);
        }
    }

    /**
     * Method to clean the lists from GameObjects no longer in use.
     */
    private void deleteOldObjs() {
        this.moles.stream()
            .filter(m -> m.getDisappearanceTime() < System.currentTimeMillis())
            .filter(m -> m.getStatus().equals(Status.HIT))
            .forEach(m -> this.moles.remove(m));
        
        this.bombs.stream()
            .filter(b -> b.getDisappearanceTime() < System.currentTimeMillis())
            .filter(b -> b.getStatus().equals(Status.MISSED))
            .forEach(b -> this.bombs.remove(b));
    }

    @Override
    public String getTutorial() {
        return "Smash the moles before they can get back to their hole by "
             + "clicking the number from 1 to 9 on your keyboard corresponding to "
             + "the hole the mole came out of, but be careful not to hit the bombs!";
    }
}
