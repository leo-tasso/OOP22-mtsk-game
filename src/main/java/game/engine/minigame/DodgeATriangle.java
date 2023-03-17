package game.engine.minigame;

import java.util.ArrayList;
import java.util.List;

import game.engine.gameobject.GameObject;
import game.engine.gameobject.hitboxmodel.Collider;
import game.engine.gameobject.hitboxmodel.ColliderImpl;

/**
 * Class that models the dodge a triangle minigame, where 
 * the player must move a small square on a vertical 
 * axis, avoiding triangles that come from left to right.
 */
public class DodgeATriangle implements Minigame {

    private final List<GameObject> l = new ArrayList<>();
    private final Collider c = new ColliderImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        final GameObject pl = l.get(0);
        return l.stream()
            .skip(1)
            .anyMatch(o -> c.isColliding(pl, o));                               
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
