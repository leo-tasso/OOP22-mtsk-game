package game.engine.minigame.whacamoleminigame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import game.controlling.WamInputModel;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.whacamoleobjects.WamBomb;
import game.engine.gameobject.whacamoleobjects.WamBombAspectModel;
import game.engine.gameobject.whacamoleobjects.Mole;
import game.engine.gameobject.whacamoleobjects.MoleAspectModel;
import game.engine.gameobject.whacamoleobjects.WamPhysicsModel;

/**
 * Class that implements a simple draw of GameObjects.
 */
public class DrawStrategyImpl implements DrawStrategy {
    private static final long SAFETY_TIME_MARGIN = 10L;
    private static final Random RANDOM = new Random();
    private final int numHoles;

    /**
     * Constructor that takes the number of holes in the game, 
     * so as to correctly assign them to the extracted objects.
     * 
     * @param numHoles the number of holes in the playing field
     */
    public DrawStrategyImpl(final int numHoles) {
        this.numHoles = numHoles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> draw(final Level currentLevel, final long currentTime) {
        final Set<GameObject> newGameObjs = new HashSet<>();
        final int maxObjs = currentLevel.getMaxObjsSimultaneouslyOut();
        final Map<Integer, Boolean> holesOccupied = new HashMap<>();
        for (int i = 1; i <= numHoles; i++) {
            holesOccupied.put(i, false);
        }
        final int nMoles = RANDOM.nextInt(maxObjs + 1);
        final int nBombs = RANDOM.nextInt(maxObjs - nMoles + 1); 
        /* To avoid assigning an appearance time so close that */
        /* the program is still executing the underlying loops */
        final long lowerBound = currentTime + SAFETY_TIME_MARGIN;
        for (int i = 0; i < nMoles; i++) {
            final long appearanceTime = lowerBound + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(
                new Mole(appearanceTime, 
                        currentLevel, 
                        assignHole(holesOccupied), 
                        new WamPhysicsModel(), 
                        new MoleAspectModel(), 
                        new WamInputModel())
            );
        }
        for (int i = 0; i < nBombs; i++) {
            final long appearanceTime = lowerBound + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(
                new WamBomb(appearanceTime, 
                        currentLevel, 
                        assignHole(holesOccupied),
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
     * @param isHoleBusy
     * @return the integer representing the hole
     */
    private Integer assignHole(final Map<Integer, Boolean> isHoleBusy) {
        final Boolean holeFound = false;
        Integer holeAssigned = RANDOM.nextInt(isHoleBusy.size()) + 1;
        while (!holeFound) {
            if (!isHoleBusy.get(holeAssigned)) {
                isHoleBusy.replace(holeAssigned, true);
                return holeAssigned;
            } 
            holeAssigned = RANDOM.nextInt(isHoleBusy.size()) + 1;
        }
        return holeAssigned;
    }
}
