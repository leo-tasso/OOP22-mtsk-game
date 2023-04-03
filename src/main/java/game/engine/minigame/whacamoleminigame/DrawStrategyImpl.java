package game.engine.minigame.whacamoleminigame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import game.engine.gameobject.GameObject;
import game.engine.gameobject.whacamoleobjects.WamBomb;
import game.engine.gameobject.whacamoleobjects.WamBombAspectModel;
import game.engine.gameobject.whacamoleobjects.WamInputModel;
import game.engine.gameobject.whacamoleobjects.Mole;
import game.engine.gameobject.whacamoleobjects.MoleAspectModel;
import game.engine.gameobject.whacamoleobjects.WamPhysicsModel;

/**
 * Class that implements a simple draw of GameObjects.
 */
public class DrawStrategyImpl implements DrawStrategy {
    private static final long SAFETY_TIME_MARGIN = 10L;
    private static final Random RANDOM = new Random();
    private final List<GameObject> holes;

    /**
     * Constructor that takes the list of holes 
     * in the game, from which to take the initial 
     * coordinates of the created objects.
     * 
     * @param holes the list of holes 
     */
    public DrawStrategyImpl(final List<GameObject> holes) {
        this.holes = new ArrayList<>();
        holes.forEach(h -> this.holes.add(h));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> draw(final Level currentLevel, final long currentTime) {
        final Set<GameObject> newGameObjs = new HashSet<>();
        final int maxObjs = currentLevel.getMaxObjsSimultaneouslyOut();
        final Map<Integer, Boolean> holesOccupied = new HashMap<>();
        for (int i = 1; i <= holes.size(); i++) {
            holesOccupied.put(i, false);
        }
        final int nMoles = RANDOM.nextInt(maxObjs + 1);
        final int nBombs = RANDOM.nextInt(maxObjs - nMoles + 1); 
        /* To avoid assigning an appearance time so close that */
        /* the program is still executing the underlying loops */
        final long lowerBound = currentTime + SAFETY_TIME_MARGIN;
        for (int i = 0; i < nMoles; i++) {
            final int holeAssigned = assignHole(holesOccupied);
            final long appearanceTime = lowerBound + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(
                new Mole(holes.get(holeAssigned - 1).getCoor(),
                        appearanceTime, 
                        currentLevel, 
                        holeAssigned, 
                        new WamPhysicsModel(), 
                        new MoleAspectModel(), 
                        new WamInputModel())
            );
        }
        for (int i = 0; i < nBombs; i++) {
            final int holeAssigned = assignHole(holesOccupied);
            final long appearanceTime = lowerBound + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(
                new WamBomb(holes.get(holeAssigned - 1).getCoor(),
                        appearanceTime,
                        currentLevel, 
                        holeAssigned,
                        new WamPhysicsModel(),
                        new WamBombAspectModel(), 
                        new WamInputModel())
            );
        }
        return newGameObjs; 
    }

    /**
     * Method that randomly assigns a Hole from 
     * which to make the bomb or mole emerge.
     * 
     * @param holesOccupied from the hole's number
     * to 'true' if it has already been assigned
     * @return the integer representing the hole
     */
    private Integer assignHole(final Map<Integer, Boolean> holesOccupied) {
        final Boolean holeFound = false;
        Integer holeAssigned = RANDOM.nextInt(holesOccupied.size()) + 1;
        while (!holeFound) {
            if (!holesOccupied.get(holeAssigned)) {
                holesOccupied.replace(holeAssigned, true);
                return holeAssigned;
            } 
            holeAssigned = RANDOM.nextInt(holesOccupied.size()) + 1;
        }
        return holeAssigned;
    }
}
