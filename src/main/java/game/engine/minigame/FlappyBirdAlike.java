package game.engine.minigame;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.ArrayList;

import game.controlling.NullInput;
import game.engine.difficultystrats.StepRateStrat;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.RectangleAspect;
import game.engine.gameobject.SimplePhysics;
import game.engine.gameobject.flappybirdalikeobjects.Cursor;
import game.engine.gameobject.hitboxmodel.ColliderImpl;
import game.engine.gameobject.hitboxmodel.RectangleHitBoxModel;
import api.ColorRGB;
import api.Point2D;
import api.Vector2D;

/**
* The flappy bird like minigame.
*
*/
public class FlappyBirdAlike implements Minigame {

    private static final double CURSOR_SIZE = 200;
    private static final double CURSOR_X = 50;
    private static final int ENEMY_WIDTH = 100;
    private static final int ENEMY_SPAWN = 2000;
    private static final int ENEMY_SPEED = -50;
    private static final int FIELD_HEIGHT = 900;
    private static final int HEIGHT_OFFSET = 100;
    private static final int MAX_HEIGHT = FIELD_HEIGHT - (int) CURSOR_SIZE - 2 * HEIGHT_OFFSET;
    private static final long INC_DIFF_TIME_WINDOW = 10_000L;
    private static final int X_OFFSET = 100;
    private static final int NUM_STEPS = 8;
    private final List<GameObject> l = new ArrayList<>();
    private final Random rand = new Random();
    private Function<Long, Integer> freqStrat = new StepRateStrat(NUM_STEPS, X_OFFSET, INC_DIFF_TIME_WINDOW);
    private long totalElapsed;
    private int enemyHeight;
    private int curLimit;

    /**
    * Contructs an instance of the flappy bird minigame.
    *
    */
    public FlappyBirdAlike() {
        this.l.add(new Cursor(new Point2D(CURSOR_SIZE / 2 + CURSOR_X, FIELD_HEIGHT - CURSOR_SIZE / 2),
                Vector2D.nullVector(),
                CURSOR_SIZE,
                -ENEMY_SPEED));
    }

    /**
    * Determines whether the game is over or not.
    *
    * @return true if you hit an obstacle.
    */
    @Override
    public boolean isGameOver() {
        return l.size() > 1
                && new ColliderImpl().isColliding(l.get(0), l.get(1));
    }

    /**
    * Computes the state of the objects in the minigame.
    *
    * @param elapsed the time passed since last compute.
    */
    @Override
    public void compute(final long elapsed) {
        totalElapsed += elapsed;
        curLimit = freqStrat.apply(totalElapsed);
        if (l.size() == 1 || l.get(l.size() - 1).getCoor().getX() < curLimit) {
            enemyHeight = rand.nextInt(MAX_HEIGHT) + HEIGHT_OFFSET;
            final double y = rand.nextInt(2) == 1 ? enemyHeight / 2.0 : FIELD_HEIGHT - enemyHeight / 2.0;
            l.add(new GameObject(new Point2D(ENEMY_SPAWN, y),
                    new Vector2D(ENEMY_SPEED, 0),
                    0, new NullInput(),
                    new SimplePhysics(),
                    new RectangleAspect(ENEMY_WIDTH, enemyHeight, ColorRGB.black()),
                    new RectangleHitBoxModel(ENEMY_WIDTH, enemyHeight)));
        }

        l.removeIf(e -> e.getCoor().getX() < -ENEMY_WIDTH);
        l.forEach(e -> e.updatePhysics(elapsed, this));
    }

    /**
    * A method that returns the GameObjects from the minigame.
    *
    * @return the list of GameObjects currently loaded.
    */
    @Override
    public List<GameObject> getObjects() {
        return new ArrayList<>(this.l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTutorial() {
        return "Press the SPACEBAR to jump and evade obstacles.\n You can jump in midair too!";
    }

}
