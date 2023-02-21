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
    
    @Override
    public Set<GameObject> draw(Level currLevel, long upperBond, int numHoles) {
        final Set<GameObject> newGameObjs = new HashSet<>();
        final int maxObjs = currLevel.getMaxObjsSimultaneouslyOut();
        final Map<Integer, Boolean> isHoleBusy = new HashMap<>();
        for (int i = 1; i <= numHoles; i++) {
            isHoleBusy.put(i, false);
        }
        final Random rand = new Random();
        final int nMoles = rand.nextInt(maxObjs + 1);
        final int nBombs = rand.nextInt(maxObjs - nMoles + 1); 
        for (int i = 0; i < nMoles; i++) {           
            final long lowerBond = System.currentTimeMillis();
            final long appearanceTime = (int) Math.random() * (upperBond - lowerBond) + lowerBond;
            newGameObjs.add(new Mole(currLevel.getObjExitPeriod(), appearanceTime, assignHole(isHoleBusy)));
        }
        for (int i = 0; i < nBombs; i++) {
            final long lowerBond = System.currentTimeMillis();
            final long appearanceTime = (int) Math.random() * (upperBond - lowerBond) + lowerBond;
            newGameObjs.add(new Bomb(currLevel.getObjExitPeriod(), appearanceTime, assignHole(isHoleBusy)));
        }
        return newGameObjs; 
    }
}
