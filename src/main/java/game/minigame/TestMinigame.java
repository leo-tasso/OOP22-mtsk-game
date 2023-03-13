package game.minigame;

import java.util.ArrayList;
import java.util.List;

import api.Point2D;
import api.Vector2D;
import game.controlling.NullInput;
import game.gameobject.Circle;
import game.gameobject.GameObject;
import game.gameobject.RectangleAspect;
import game.gameobject.SimplePhysics;

/**
 * A simple test minigame.
 */
public class TestMinigame implements Minigame {

    private static final int START_CIRCLE_Y = 0;
    private static final int START_CIRCLE_X = 0;
    private static final int RECTANGLE_WIDTH = 100;
    private static final int RECTANGLE_HEIGHT = 200;
    private static final int RECTANGLE_STARTING_COORDINATE = 300;
    // private static final int FLOOR_GAME_OVER = 2000;
    private static final double CIRCLE_RADIUS = 100;
    private final List<GameObject> l;

    /**
     * Constructor for the test minigame.
     */
    public TestMinigame() {
        this.l = new ArrayList<>(List.of(
                new Circle(new Point2D(START_CIRCLE_X, START_CIRCLE_Y),
                        new Vector2D(0, 1),
                        CIRCLE_RADIUS)));
        l.add(new GameObject(new Point2D(RECTANGLE_STARTING_COORDINATE, RECTANGLE_STARTING_COORDINATE),
                new Vector2D(0, 0), 0, new NullInput(), new SimplePhysics(),
                new RectangleAspect(RECTANGLE_WIDTH, RECTANGLE_HEIGHT)));
    }

    /**
     * A method that returns the current state of the minigame.
     * 
     * @return true if the minigame is over, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        return false;
        // return l.get(0).getCoor().getY() > FLOOR_GAME_OVER;
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
        // TODO dubug only System.out.println("Coordinates: " + l.get(0).getCoor() + "" + l.get(0).getVel());
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