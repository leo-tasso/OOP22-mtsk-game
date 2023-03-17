package game.engine.minigame;

import java.util.ArrayList;
import java.util.List;

import game.controlling.DodgerInputModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.dodgeatriangleobjects.Dodger;
import game.engine.gameobject.hitboxmodel.Collider;
import game.engine.gameobject.hitboxmodel.ColliderImpl;

/**
 * Class that models the dodge a triangle minigame, where 
 * the player must move a small square on a vertical 
 * axis, avoiding triangles that come from left to right.
 */
public class DodgeATriangle implements Minigame {

    private static final int INITIAL_Y = 450;
    private static final int SIDE_LENGTH = 140;

    private final List<GameObject> l = new ArrayList<>();
    private final Collider c = new ColliderImpl();
    private long totalElapsed;

    /**
     * Constructor that initializes the 
     * list with the main character.
     */
    public DodgeATriangle() {
        this.l.add(new Dodger(INITIAL_Y, SIDE_LENGTH,
                new DodgerInputModel(SIDE_LENGTH, INITIAL_Y)));
    }

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
        this.totalElapsed += elapsed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<GameObject> getObjects() {
        return new ArrayList<>(this.l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTutorial() {
        return "Use the arrows to move forward and backwards.\nAvoid the triangles.";
    }
}
