package game.minigame;

import java.util.List;
import java.util.ArrayList;

import game.gameobject.GameObject;
import game.gameobject.flappybirdalikeobjects.Cursor;
import api.Point2D;
import api.Vector2D;

/**
 * The flappy bird like minigame.
 *
 */
public class FlappyBirdAlike implements Minigame {

    private static final double CURSOR_SIZE = 100;
    private final List<GameObject> l = new ArrayList<>();

    /**
     * Contructs an instance of the flappy bird minigame.
     *
     */
    public FlappyBirdAlike() {
        this.l.add(new Cursor(Point2D.origin(), Vector2D.nullVector(), CURSOR_SIZE));
    }

    /**
     * Determines whether the game is over or not.
     *
     * @return true if you hit an obstacle.
     */
    @Override
    public boolean isGameOver() {
        return false;
    }

    /**
     * Computes the state of the objects in the minigame.
     *
     * @param elapsed the time passed since last compute.
     */
    @Override
    public void compute(final long elapsed) {

    }

    /**
     * A method that returns the GameObjects from the minigame.
     *
     * @return the list of GameObjects currently loaded.
     */
    @Override
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(this.l);
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
