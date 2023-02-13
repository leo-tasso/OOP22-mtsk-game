package game.minigame;

import java.util.ArrayList;
import java.util.List;

import api.Point2D;
import api.Vector2D;
import game.gameobject.Circle;
import game.gameobject.GameObject;

/**
 * Minigame where the player has to catch sqares before the time runs out.
 */
public class CatchTheSquare implements Minigame {
    private final List<GameObject> gObjects;

    CatchTheSquare() {
        this.gObjects = new ArrayList<>();
        gObjects.add(new Circle(Point2D.origin(), Vector2D.nullVector(), 0));
    }

    /**
     * The game is over if any of the sqares timers runs out.
     * 
     * @return if the minigame is over.
     */
    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Changes the sta states of the objects according the elapsed time.
     * 
     * @param elapsed the elapsed time.
     */
    @Override
    public void compute(final long elapsed) {
        // TODO Auto-generated method stub

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
}
