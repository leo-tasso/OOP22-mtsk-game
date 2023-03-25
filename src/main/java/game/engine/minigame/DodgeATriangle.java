package game.engine.minigame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import api.Point2D;
import api.Vector2D;
import game.controlling.DodgerInputModel;
import game.controlling.NullInput;
import game.engine.difficultystrats.StepRateStrat;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.SimplePhysics;
import game.engine.gameobject.dodgeatriangleobjects.DatTriangle;
import game.engine.gameobject.dodgeatriangleobjects.Dodger;
import game.engine.gameobject.dodgeatriangleobjects.SlotAspect;
import game.engine.gameobject.hitboxmodel.Collider;
import game.engine.gameobject.hitboxmodel.ColliderImpl;

/**
 * Class that models the dodge a triangle minigame, where 
 * the player must move a small square on a vertical 
 * axis, avoiding triangles that come from left to right.
 */
public class DodgeATriangle implements Minigame {

    private static final double RATIO = 16 / 9d;
    private static final int DEFAULT_HEIGHT = 900;
    private static final int DIFFICULTY_OFFSET = 100;
    private static final int SLOT_RATIO = 9;
    private static final Vector2D DEFAULT_SPEED = new Vector2D(40, 0);
    private static final int NUM_SLOTS = 5;
    private static final int NUM_STEPS = 8;
    private static final long MS_TO_ADD_ENEMY = 10_000L;

    private final int width;
    private final int initialY;
    private final int sideLength;
    private final int spawnLeft;
    private final int spawnRight;
    private final Vector2D enemySpeed;
    private final List<GameObject> l = new ArrayList<>();
    private final GameObject slots;
    private final Collider c = new ColliderImpl();
    private final Random rand = new Random();
    private long totalElapsed;
    private final Function<Long, Integer> diff;
    private boolean gameOver;

    /**
     * Constructor that initializes the 
     * list with the main character.
     * 
     * @param height the height of the game's world
     */
    public DodgeATriangle(final int height) {
        this.width = (int) (height * RATIO);
        final int initialX = (int) (width / 2);
        this.initialY = height / 2;
        this.sideLength = (height - 2 * height / SLOT_RATIO) / NUM_SLOTS;
        this.spawnLeft = -sideLength;
        this.spawnRight = (int) (width + sideLength);
        this.enemySpeed = DEFAULT_SPEED.mul(height / (double) DEFAULT_HEIGHT);
        this.diff = new StepRateStrat(NUM_STEPS, height / DEFAULT_HEIGHT * DIFFICULTY_OFFSET, MS_TO_ADD_ENEMY);
        this.l.add(new Dodger(initialY, sideLength,
                new DodgerInputModel(sideLength, initialY)));
        this.slots = new GameObject(new Point2D(initialX, initialY), enemySpeed);
        this.slots.setAspectModel(new SlotAspect(sideLength, new Point2D(initialX, initialY), NUM_SLOTS));
        this.slots.setInputModel(new NullInput());
        this.slots.setPhysicsModel(new SimplePhysics());
        this.slots.setVel(Vector2D.nullVector());
    }

    /**
     * Constructor with default height setting.
     */
    public DodgeATriangle() {
        this(DEFAULT_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        if (!gameOver) {
            final GameObject pl = l.get(0);
            gameOver =  l.stream()
                .skip(1)
                .anyMatch(o -> c.isColliding(pl, o));
        }
        return gameOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compute(final long elapsed) {
        this.totalElapsed += elapsed;
        if (l.size() == 1 || canSpawnNewEnemy()) {
            final int enemyY = Optional.of(rand.nextInt(NUM_SLOTS))
                    .map(y -> initialY + (y - 2) * sideLength)
                    .get();
            final int enemyX = Optional.of(rand.nextInt(2))
                    .map(x -> x == 0 ? spawnLeft : spawnRight)
                    .get();
            l.add(new DatTriangle(new Point2D(enemyX, enemyY),
                    enemyX < 0 ? enemySpeed : enemySpeed.invert(),
                    sideLength));
        }
        l.removeIf(o -> o.getCoor().getX() < spawnLeft
                || o.getCoor().getX() > spawnRight);
        l.forEach(e -> e.updatePhysics(elapsed, this));
    }

    /**
     * Method that calculates whether a new enemy can appear based on 
     * the last enemy's current position and the difficulty settings.
     * 
     * @return whether a new enemy can appear or not
     */
    private boolean canSpawnNewEnemy() {
        return l.get(l.size() - 1).getVel().getX() < 0
                && l.get(l.size() - 1).getCoor().getX() < diff.apply(totalElapsed)
                || l.get(l.size() - 1).getVel().getX() > 0
                && l.get(l.size() - 1).getCoor().getX() > width - diff.apply(totalElapsed);
    }

    /**
     * The slots object is only used to add visual cells to the 
     * game, therefore it's not in the main gameobject list.
     */
    @Override
    public List<GameObject> getObjects() {
        return Stream.concat(l.stream(), Stream.of(slots)).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTutorial() {
        return "Use the arrows to move forward and backwards.\nAvoid the triangles.";
    }
}
