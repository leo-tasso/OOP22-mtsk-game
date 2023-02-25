package game.minigame.whacamoleminigame;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import game.gameobject.GameObject;
import game.gameobject.whacamoleobjects.Bomb;
import game.gameobject.whacamoleobjects.Mole;

/**
 * Class that implements a simple draw of GameObjects.
 */
public class DrawStrategyImpl implements DrawStrategy {
    private static final long SAFETY_TIME_MARGIN = 10L;
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

    @Override
    public Set<GameObject> draw(Level currentLevel, long currentTime) {
        final Set<GameObject> newGameObjs = new HashSet<>();
        final int maxObjs = currentLevel.getMaxObjsSimultaneouslyOut();
        final Map<Integer, Boolean> isHoleBusy = new HashMap<>();
        for (int i = 1; i <= numHoles; i++) {
            isHoleBusy.put(i, false);
        }
        final Random rand = new Random();
        final int nMoles = rand.nextInt(maxObjs + 1);
        final int nBombs = rand.nextInt(maxObjs - nMoles + 1); 
        /* To avoid assigning an appearance time so close that */
        /* the program is still executing the underlying loops */
        final long lowerBound = currentTime + SAFETY_TIME_MARGIN;
        for (int i = 0; i < nMoles; i++) {
            final long appearanceTime = lowerBound + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(new Mole(, , assignHole(isHoleBusy)));
        }
        for (int i = 0; i < nBombs; i++) {
            final long appearanceTime = lowerBond + currentLevel.getSpawnWaitingTime().drawInBetween();
            newGameObjs.add(new Bomb(, , assignHole(isHoleBusy)));
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
    private Integer assignHole(Map<Integer, Boolean> isHoleBusy) {
        final Random rand = new Random();
        Boolean holeFound = false;
        Integer holeAssigned = rand.nextInt(isHoleBusy.size()) + 1;
        while (!holeFound) {
            if (!isHoleBusy.get(holeAssigned)) {
                isHoleBusy.replace(holeAssigned, true);
                holeFound = true;
                return holeAssigned;
            } 
            holeAssigned = rand.nextInt(isHoleBusy.size()) + 1;
        }
        return holeAssigned;
    }
}
