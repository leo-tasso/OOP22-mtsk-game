package game.engine.minigame.whacamoleminigame;

import java.util.ArrayList;
import java.util.List;
import api.Vector2D;
import game.engine.minigame.Minigame;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.whacamoleobjects.Status;
import game.engine.gameobject.whacamoleobjects.WamObject;

/**
 * Class that implements the Whac-a-mole minigame logic.
 */
public class WhacAMole implements Minigame {
    /**
     *  Number of holes in the game.
     */
    public static final int NUM_HOLES = 9;
    private static final int DRAWS_TO_NEXT_LEVEL = 3;
    
    private List<WamObject> objs;
    private final List<Level> levels; 
    private long currentTime;
    private final DrawStrategy draw;
    private Level currentLevel;
    private int numDraws;

    /**
     * General initialization of the fields. The start time 
     * is requested in order to calculate when to advance 
     * in level, the WamObject list is initialized with the 
     * objects representing the holes in the playing field.
     * 
     * @param fieldHeight the height of the playing field
     */
    public WhacAMole(final int fieldHeight) {
        this.currentTime = 0L;
        this.levels = List.of(new LevelOne(), new LevelTwo(), new LevelThree());
        this.objs = new ArrayList<>(new HolesGenerator(fieldHeight).generate(NUM_HOLES));
        this.draw = new DrawStrategyImpl(new ArrayList<>(this.objs.subList(NUM_HOLES, this.objs.size())));
        this.currentLevel = this.levels.get(0);
        this.numDraws = 0;
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
        return this.objs.stream()
            .anyMatch(o -> o.isGameOver());
    }

    /**
     * At this point each object that has been hit will have been notified 
     * by the InputModel, so now I need to update the logical state 
     * of all the others and manage the physics of the moving objects.
     * Finally I check if a new extraction is necessary.
     * 
     * @param elapsed time elapsed since the last frame
     */
    @Override
    public void compute(final long elapsed) {
        this.currentTime += elapsed;
        this.deleteOldObjs();
        this.calculateLevel();
        this.drawIfNecessary();

        this.objs.stream()
            .filter(o -> !o.getStatus().equals(Status.WAITING))
            .forEach(o -> o.updatePhysics(elapsed, this));

        this.objs.stream()
            .filter(o -> o.getStatus().equals(Status.WAITING))
            .filter(o -> o.getAppearanceTime() <= this.currentTime)
            .forEach(o -> {
                o.setStatus(Status.IN_MOTION);
                o.setVel(o.getLevel().getObjSpeed());
            });

        this.objs.stream()
            .filter(o -> o.getCoor().getY() > o.getStartCoor().getY())
            .forEach(o -> {
                o.setVel(Vector2D.nullVector());
                if (!o.getStatus().equals(Status.HIT)) {
                    o.setStatus(Status.MISSED);
                }
            });

        this.objs.stream()
            .filter(o -> o.getStatus().equals(Status.HALFWAY))
            .filter(o -> o.getMotionRestartTime() <= this.currentTime)
            .forEach(o -> {
                o.setStatus(Status.IN_MOTION);
                o.setVel(this.currentLevel.getObjSpeed().invert());
            });
    }

    /**
     * Method that checks whether it is time 
     * to perform a draw, and if so, does it.
     */
    private void drawIfNecessary() {
        if (this.objs.size() == WhacAMole.NUM_HOLES * 2) {
            this.draw.draw(this.currentLevel, this.currentTime).stream()
                .forEach(
                    /* Since the visualization of the layers when they   */
                    /* overlap depends on the print order, I have to put */
                    /* all the objects after the upper part of the holes */
                    o -> this.objs.add(NUM_HOLES, (WamObject) o)
                );
            this.numDraws = this.numDraws + 1;
        }
    }

    /**
     * Returns all the GameObjects currently in use in the game.
     * 
     * @return the list of GameObjects
     */
    @Override
    public List<GameObject> getObjects() {
        return new ArrayList<>(objs);
    }

    /**
     * Method for calculating the difficulty level based on how 
     * many draws have been made. If there are no more levels 
     * available to advance, then the user will stay on the last one.
     */
    private void calculateLevel() {
        final int levelIndex = (int) this.numDraws / (int) DRAWS_TO_NEXT_LEVEL;
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
        this.objs.removeAll(
            this.objs.stream()
                .filter(o -> !o.isStillInUse())
                .toList()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTutorial() {
        return "Smash the moles before they can get back to their hole by "
             + "clicking the number from 1 to 9 on your keyboard corresponding to "
             + "the hole the mole came out of, but be careful not to hit the bombs!";
    }

    /**
     * Getter method for the local time of this minigame.
     * 
     * @return the current time
     */
    public long getCurrentTime() {
        return this.currentTime;
    }

    /**
     * Method that allows you to update the list of objects (It is 
     * used to test that the bombs work correctly, eliminating the 
     * moles to ensure that they do not interfere with the test result).
     * 
     * @param objs the new object list
     */
    public void setObjects(final List<WamObject> objs) {
        this.objs = new ArrayList<>(objs);
    }
}
