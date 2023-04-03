package game.engine.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

import api.ColorRGB;
import api.Point2D;
import game.engine.difficultystrats.IncrRateStrat;
import game.engine.gameobject.CircleAspect;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.InputModel;
import game.engine.gameobject.RectangleAspect;
import game.engine.gameobject.catchthesqareobjects.BoundaryDumpedPhysics;
import game.engine.gameobject.catchthesqareobjects.CtsBomb;
import game.engine.gameobject.catchthesqareobjects.Defuser;
import game.engine.gameobject.catchthesqareobjects.DirectionalInput;
import game.engine.gameobject.hitboxmodel.Collider;
import game.engine.gameobject.hitboxmodel.ColliderImpl;

/**
 * Minigame where the player has to catch sqares before the time runs out.
 */
public class CatchTheSquare implements Minigame {

    private static final int DEFUSER_RADIUS = 100;
    private static final int BOMB_SIDE = (int) (DEFUSER_RADIUS * 1.5);
    private static final int MAX_OBJECT = 6;
    private static final double MAX_BOMB_RATE = 0.7;
    private static final double BOMB_SPAWN_DIFF = 1.05;
    private static final double DUMP_COEFFICIENT = 2;
    private static final double RATIO = 16 / 9d;

    private final int rightBound;
    private final int bottomBound;
    private long totalElapsed;
    private int totalBombsSpawned;
    private final Defuser defuser;
    private final List<GameObject> gObjects;
    private final Random r;
    private final Function<Long, Long> spawnFreqStrat;

    /**
     * Constructor for the minigame, it initializes its fields.
     * 
     * @param spawnFreqStrat    the function strategy to regulate the spawn
     *                          frequency
     *                          of the boms.
     * @param defuserInputModel the InputModel to be used my the defuser.
     * @param bottomBound       the height in points that the View will
     *                          display.
     */
    public CatchTheSquare(final Function<Long, Long> spawnFreqStrat, final InputModel defuserInputModel,
            final int bottomBound) {
        this.gObjects = new ArrayList<>();
        this.bottomBound = bottomBound;
        this.rightBound = (int) (bottomBound * RATIO);
        this.totalElapsed = 0;
        this.totalBombsSpawned = 0;
        this.r = new Random();
        this.spawnFreqStrat = spawnFreqStrat;
        defuser = new Defuser(new Point2D(rightBound / 2d, bottomBound / 2d), DEFUSER_RADIUS, defuserInputModel,
                new BoundaryDumpedPhysics(rightBound, bottomBound, DEFUSER_RADIUS, DUMP_COEFFICIENT));
        gObjects.add(defuser);
    }

    /**
     * Constructor with default values.
     * 
     * @param bottomBound the height in points that the View will display.
     */
    public CatchTheSquare(final int bottomBound) {
        this(new IncrRateStrat(BOMB_SPAWN_DIFF, MAX_BOMB_RATE), new DirectionalInput(), bottomBound);
    }

    /**
     * The game is over if any of the sqares timers runs out.
     * 
     * @return if the minigame is over.
     */
    @Override
    public boolean isGameOver() {
        return !gObjects.stream()
                .filter(o -> o instanceof CtsBomb)
                .map(obj -> (CtsBomb) obj)
                .allMatch(b -> b.getTimer() >= 0);
    }

    /**
     * Changes the sta states of the objects according the elapsed time.
     * 
     * @param elapsed the elapsed time.
     */
    @Override
    public void compute(final long elapsed) {
        totalElapsed += elapsed;
        final Optional<GameObject> collider = checkCollision(defuser);
        if (collider.isPresent()) {
            gObjects.remove(collider.get());
        }
        if (totalBombsSpawned < spawnFreqStrat.apply(totalElapsed) && gObjects.size() < MAX_OBJECT) {
            gObjects.add(new CtsBomb(randSpawnPoint(), BOMB_SIDE, ColorRGB.black())); // if changing bomb shape, also
                                                                                      // change
                                                                                      // checkCollision method
            totalBombsSpawned++;
        }
        gObjects.forEach(b -> b.updatePhysics(elapsed, this));

    }

    private Optional<GameObject> checkCollision(final Defuser defuser) {
        if (defuser.getAspectModel() instanceof CircleAspect) { // check if the bounding box is a circle
            final List<GameObject> bombs = gObjects.stream()
                    .filter(o -> o instanceof CtsBomb)
                    .filter(b -> b.getAspectModel() instanceof RectangleAspect) // check if bounding box is a square
                    .toList();
            for (final GameObject bomb : bombs) {
                final Collider c = new ColliderImpl();
                if (c.isColliding(bomb, defuser)) {
                    return Optional.of(bomb);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * A method to get a random spawn point that is at least {@value #BOMB_SIDE}*2
     * distant from other objects.
     * 
     * @return the new spawn point.
     */
    private Point2D randSpawnPoint() {
        final Point2D p = new Point2D(r.nextInt(BOMB_SIDE / 2, rightBound - BOMB_SIDE / 2),
                r.nextInt(BOMB_SIDE / 2, bottomBound - BOMB_SIDE / 2));
        for (final GameObject gameObject : gObjects) {
            if (p.distance(gameObject.getCoor()) < BOMB_SIDE * 2) {
                return randSpawnPoint(); // Throws exeption if there's no space for new object, adjust sizes and spawn
                                         // frequency
            }
        }
        return p;
    }

    /**
     * Returns the list of the gameObjects present in the minigame.
     * 
     * @return the list of the gameObjects present in the minigame.
     */
    @Override
    public List<GameObject> getObjects() {
        return new ArrayList<>(gObjects);
    }

    /**
     * Method to get the tutorial string.
     */
    @Override
    public String getTutorial() {
        return "Use \"WASD\" to control the the circle and "
                + "defuse all the squares by touching them before the time runs out"; // In the future the keys might be
                                                                                      // fetched dinamically
    }
}
