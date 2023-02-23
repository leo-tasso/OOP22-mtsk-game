package game.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import api.ColorRGB;
import api.Point2D;
import game.gameobject.CircleAspect;
import game.gameobject.GameObject;
import game.gameobject.catchthesqareobjects.Bomb;
import game.gameobject.catchthesqareobjects.BombAspect;
import game.gameobject.catchthesqareobjects.Defuser;
import game.minigame.catchthesquare.IncrSpawnStrat;
import game.minigame.catchthesquare.SpawnFreqStrat;

/**
 * Minigame where the player has to catch sqares before the time runs out.
 */
public class CatchTheSquare implements Minigame {
    private static final int RIGHT_BOUND = 1600;
    private static final int BOTTOM_BOUND = 900;
    private static final int DEFUSER_RADIUS = 100;
    private static final int BOMB_SIDE = (int) (DEFUSER_RADIUS * 1.5);
    private static final double BOMB_SPAWN_DIFF = 1.05;
    private static final Point2D SPAWN_POINT_DEFUSER = new Point2D(RIGHT_BOUND / 2, BOTTOM_BOUND / 2);
    private static final int MAX_OBJECT = 6;

    private long totalElapsed;
    private int totalBombsSpawned;
    private final Defuser defuser;
    private final List<GameObject> gObjects;
    private final Random r;
    private final SpawnFreqStrat spawnFreqStrat;

    /**
     * Constructor for the minigame, it initializes its fields.
     */
    public CatchTheSquare() {
        this.gObjects = new ArrayList<>();
        this.totalElapsed = 0;
        this.totalBombsSpawned = 0;
        this.r = new Random();
        this.spawnFreqStrat = new IncrSpawnStrat(BOMB_SPAWN_DIFF);
        defuser = new Defuser(SPAWN_POINT_DEFUSER, DEFUSER_RADIUS);
        gObjects.add(defuser);
    }

    /**
     * The game is over if any of the sqares timers runs out.
     * 
     * @return if the minigame is over.
     */
    @Override
    public boolean isGameOver() {
        return !gObjects.stream()
                .filter(o -> o instanceof Bomb)
                .map(obj -> (Bomb) obj)
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
        if (totalBombsSpawned < spawnFreqStrat.bombNumber(totalElapsed) && gObjects.size() < MAX_OBJECT) {
            gObjects.add(new Bomb(randSpawnPoint(), BOMB_SIDE, ColorRGB.black())); // if changing bomb shape, also
                                                                                   // change
                                                                                   // checkCollision method
            totalBombsSpawned++;
        }
        gObjects.forEach(b -> b.updatePhysics(elapsed, this));

    }

    private Optional<GameObject> checkCollision(final Defuser defuser) {
        if (defuser.getAspectModel() instanceof CircleAspect) { // check if the bounding box is a circle
            final List<GameObject> bombs = gObjects.stream()
                    .filter(o -> o instanceof Bomb)
                    .filter(b -> b.getAspectModel() instanceof BombAspect)
                    .toList();
            for (final GameObject bomb : bombs) {
                final double circleDistancex = Math.abs(defuser.getCoor().getX() - bomb.getCoor().getX());
                final double circleDistancey = Math.abs(defuser.getCoor().getY() - bomb.getCoor().getY());

                if (circleDistancex > (BOMB_SIDE / 2 + DEFUSER_RADIUS)) {
                    continue;
                }
                if (circleDistancey > (BOMB_SIDE / 2 + DEFUSER_RADIUS)) {
                    continue;
                }

                if (circleDistancex <= (BOMB_SIDE / 2)) {
                    return Optional.of(bomb);
                }
                if (circleDistancey <= (BOMB_SIDE / 2)) {
                    return Optional.of(bomb);
                }

                final double cornerDistanceSq = Math.pow(circleDistancex - BOMB_SIDE / 2, 2)
                        + Math.pow(circleDistancey - BOMB_SIDE / 2, 2);

                if (cornerDistanceSq <= Math.pow(DEFUSER_RADIUS, 2)) {
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
        final Point2D p = new Point2D(r.nextInt(BOMB_SIDE / 2, RIGHT_BOUND - BOMB_SIDE / 2),
                r.nextInt(BOMB_SIDE / 2, BOTTOM_BOUND - BOMB_SIDE / 2));
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
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(gObjects);
    }

    /**
     * Method to get the tutorial string.
     */
    @Override
    public String getTutorial() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTutorial'");
    }
}
