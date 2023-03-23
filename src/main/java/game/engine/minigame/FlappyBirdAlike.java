package game.engine.minigame;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.ArrayList;

import game.controlling.FlappyInput;
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

    private static final double RATIO = 16 / 9d;
    private static final int DEFAULT_HEIGHT = 900;
    private static final long INC_DIFF_TIME_WINDOW = 10_000L;
    private static final int X_OFFSET = 100;
    private static final int NUM_STEPS = 8;
    private final int height;
    private final int maxHeight;
    private final int enemySpeed;
    private final int heightOffset;
    private final int enemyWidth;
    private final int enemySpawn;
    private final List<GameObject> l = new ArrayList<>();
    private final Random rand = new Random();
    private final Function<Long, Integer> freqStrat = new StepRateStrat(NUM_STEPS, X_OFFSET, INC_DIFF_TIME_WINDOW);
    private long totalElapsed;
    private int enemyHeight;
    private boolean gameOver;

    /**
    * Contructs an instance of the flappy bird minigame.
    * 
    * @param height the height of the game's world
    */
    public FlappyBirdAlike(final int height) {
        this.height = height;
        this.enemySpeed = (int) (-height * RATIO / 32);
        final double cursorSize = height * RATIO / 8;
        this.heightOffset = (int) (height * RATIO / 16);
        this.enemyWidth = heightOffset;
        this.maxHeight = height - (int) cursorSize - 2 * heightOffset;
        this.enemySpawn = (int) (height * RATIO) + enemyWidth;
        this.l.add(new Cursor(new Point2D(cursorSize / 2 + height * RATIO / 32, height - cursorSize / 2),
                Vector2D.nullVector(),
                cursorSize,
                -enemySpeed,
                new FlappyInput()));
    }

    /**
     * Contructs an instance of the flappy bird minigame
     * with default height setting.
     */
    public FlappyBirdAlike() {
        this(DEFAULT_HEIGHT);
    }

    /**
    * Determines whether the game is over or not.
    *
    * @return true if you hit an obstacle.
    */
    @Override
    public boolean isGameOver() {
        if (!gameOver) {
            gameOver = l.size() > 1
                && new ColliderImpl().isColliding(l.get(0), l.get(1));
        }
        return gameOver;
    }

    /**
    * Computes the state of the objects in the minigame.
    *
    * @param elapsed the time passed since last compute.
    */
    @Override
    public void compute(final long elapsed) {
        totalElapsed += elapsed;
        if (l.size() == 1 || l.get(l.size() - 1).getCoor().getX() < freqStrat.apply(totalElapsed)) {
            enemyHeight = rand.nextInt(maxHeight) + heightOffset;
            final double y = rand.nextInt(2) == 1 ? enemyHeight / 2.0 : height - enemyHeight / 2.0;
            l.add(new GameObject(new Point2D(enemySpawn, y),
                    new Vector2D(enemySpeed, 0),
                    0, new NullInput(),
                    new SimplePhysics(),
                    new RectangleAspect(enemyWidth, enemyHeight, ColorRGB.black(), false),
                    new RectangleHitBoxModel(enemyWidth, enemyHeight)));
        }

        l.removeIf(e -> e.getCoor().getX() < -enemyWidth);
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
