package game.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import game.controlling.Input;
import game.engine.gameobject.GameObject;
import game.engine.minigame.CatchTheSquare;
import game.engine.minigame.DodgeATriangle;
import game.engine.minigame.FlappyBirdAlike;
import game.engine.minigame.Minigame;

/**
 * Main game engine responsible of controlling the game.
 */
public class EngineImpl implements Engine {
    private final int bottomLimit;
    private static final List<Class<? extends Minigame>> MINIGAME_SEQUENCE = List.of(DodgeATriangle.class);
    private final List<Minigame> minigameList = new LinkedList<>();
    private int addedMinigame;

    /**
     * Constructor for this {@link Engine}.
     * 
     * @param bottomLimit the height in points that the view will display.
     */
    public EngineImpl(final int bottomLimit) {
        this.bottomLimit = bottomLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String addMinigame() {
        Minigame newMinigame;
        try {
            newMinigame = MINIGAME_SEQUENCE.get(addedMinigame)
                    .getDeclaredConstructor(int.class)
                    .newInstance(bottomLimit);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            newMinigame = null;
        }
        try {
            if (newMinigame == null) {
                newMinigame = MINIGAME_SEQUENCE.get(addedMinigame)
                        .getDeclaredConstructor()
                        .newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new IllegalArgumentException("Unable to add minigame", e);
        }
        // ^^ TODO deprecated legacy code to support no arguments minigame constructors,
        // delete once all minigames implemented it

        minigameList.add(newMinigame);
        addedMinigame++;
        return newMinigame.getTutorial();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processInput(final long elapsedTime, final Input input) {
        minigameList.stream().flatMap(m -> m.getObjects().stream()).forEach(o -> o.updateinput(input, elapsedTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<List<GameObject>> getMinigameObjects() {
        return new LinkedList<>(minigameList).stream().map(g -> g.getObjects()).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateGame(final long elapsed) {
        minigameList.forEach(m -> m.compute(elapsed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return minigameList.stream().anyMatch(Minigame::isGameOver);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int activeMinigames() {
        return minigameList.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Class<? extends Minigame>> getMinigameSequence() {
        return MINIGAME_SEQUENCE;
    }
}
