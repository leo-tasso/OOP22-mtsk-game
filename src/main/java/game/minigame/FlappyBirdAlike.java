package game.minigame;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;

import game.controlling.NullInput;
import game.gameobject.GameObject;
import game.gameobject.RectangleAspect;
import game.gameobject.SimplePhysics;
import game.gameobject.flappybirdalikeobjects.Cursor;
import api.Point2D;
import api.Vector2D;

/**
* The flappy bird like minigame.
*
*/
public class FlappyBirdAlike implements Minigame {

    private static final double CURSOR_SIZE = 200;
    private static final int ENEMY_WIDTH = 100;
    private static final int MAX_HEIGHT = 1000 - (int) CURSOR_SIZE;
    private final List<GameObject> l = new ArrayList<>();
    private int enemyHeight;

    /**
    * Contructs an instance of the flappy bird minigame.
    *
    */
    public FlappyBirdAlike() {
        this.l.add(new Cursor(new Point2D(CURSOR_SIZE / 2, 1000), Vector2D.nullVector(), CURSOR_SIZE));
    }

    /**
    * Determines whether the game is over or not.
    *
    * @return true if you hit an obstacle.
    */
    @Override
    public boolean isGameOver() {
        return l.size() > 1
                && obstacleInRange()
                && obstacleHit(l.get(1).getCoor().getY());
    }

    private boolean obstacleInRange() {
        return l.get(1).getCoor().getX() - ENEMY_WIDTH / 2 < l.get(0).getCoor().getX() + CURSOR_SIZE / 2
                && l.get(1).getCoor().getX() + ENEMY_WIDTH / 2 > l.get(0).getCoor().getX() - CURSOR_SIZE / 2;
    }

    private boolean obstacleHit(final double y) {
        return ( y < 500 && l.get(0).getCoor().getY() - boundingOffset() < enemyHeight )
                || ( y > 500 && l.get(0).getCoor().getY() + boundingOffset() > 1000 - enemyHeight );
    }

    private double boundingOffset() {
        return ( l.get(0).getCoor().getX() - l.get(1).getCoor().getX() + CURSOR_SIZE / 2 ) / 2 ;
    }

    /**
    * Computes the state of the objects in the minigame.
    *
    * @param elapsed the time passed since last compute.
    */
    @Override
    public void compute(final long elapsed) {
        if (l.size() == 1) {
            Random r = new Random();
            enemyHeight = r.nextInt(MAX_HEIGHT);
            double y = r.nextInt(2) == 1 ? enemyHeight / 2 : 1000 - enemyHeight / 2;
            l.add(new GameObject(new Point2D(2000, y),
                    new Vector2D(-50, 0),
                    0, new NullInput(),
                    new SimplePhysics(),
                    new RectangleAspect(ENEMY_WIDTH, enemyHeight)));
        }

        l.removeIf(e -> e.getCoor().getX() < - ENEMY_WIDTH);
        l.forEach(e -> e.updatePhysics(elapsed, this));
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

}
