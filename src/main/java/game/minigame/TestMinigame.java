package game.minigame;

import java.util.ArrayList;
import java.util.List;

import api.Point2D;
import api.Vector2D;
import game.gameobject.Circle;
import game.gameobject.GameObject;

/**
 * A simple test minigame.
 */
public class TestMinigame implements Minigame {

    private static final int CIRCLE_Y = 0;
    private static final int CIRCLE_X = 0;
    private final List<GameObject> l;

    /**
     * Constructor for the test minigame.
     */
    public TestMinigame() {
        this.l = new ArrayList<>(List.of(new Circle(new Point2D(CIRCLE_X, CIRCLE_Y), new Vector2D(0, 1))));
    }

    /**
     * A method that returns the current state of the minigame.
     * 
     * @return true if the minigame is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        return false;
    }

    /**
     * A method to compute the new positions of the gameobjects after the elapsed
     * time.
     * 
     * @param elapsed the elapsed time.
     */
    @Override
    public void compute(final long elapsed) {
        l.forEach(b -> b.updatePhysics(elapsed, this));
        // System.out.println("Coordinates: " + l.get(0).getCoor() + "" +
        // l.get(0).getVel());
    }

    /**
     * Getter for the GameObjects list.
     * 
     * @return The list of gameobjects.
     */
    @Override
    public List<GameObject> getGameObjects() {
        return new ArrayList<>(l);
    }
}
