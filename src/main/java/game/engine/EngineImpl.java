package game.engine;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import game.controlling.Input;
import game.engine.gameobject.GameObject;
import game.engine.minigame.CatchTheSquare;
import game.engine.minigame.Minigame;
import game.engine.minigame.TestMinigame;
import game.engine.minigame.whacamoleminigame.WhacAMole;

/**
 * Main game engine responsible of controlling the game.
 */
public class EngineImpl implements Engine {
    private static final List<Class<? extends Minigame>> MINIGAME_SEQUENCE = List.of(WhacAMole.class, CatchTheSquare.class,
            CatchTheSquare.class, TestMinigame.class);
    private final List<Minigame> minigameList = new LinkedList<>();
    private int addedMinigame;

    /**
     * {@inheritDoc}
     */
    @Override
    public String addMinigame() {
        Minigame newMinigame;
        try {
            newMinigame = MINIGAME_SEQUENCE.get(addedMinigame++).getDeclaredConstructor().newInstance();

            minigameList.add(newMinigame);
            return newMinigame.getTutorial();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            return "";
        }

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
