package game.engine.minigame;

import java.util.List;

import game.engine.gameobject.GameObject;

/**
 * TODO.
 */
public class DodgeATriangle implements Minigame {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compute(final long elapsed) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        // TODO Auto-generated method stub
        return List.of();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTutorial() {
        return "Use the arrows to move forward and backwards.\nAvoid the triangles.";
    }
}
