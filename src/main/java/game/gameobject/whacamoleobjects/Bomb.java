package game.gameobject.whacamoleobjects;

import game.minigame.whacamoleminigame.Level;

public class Bomb extends WamObject {

    public Bomb(long appearanceTime, Level currentLevel, int holeNumber) {
        super(appearanceTime, currentLevel, holeNumber);
        //TODO Auto-generated constructor stub
    }

    @Override
    public boolean isGameOver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isGameOver'");
    }

    @Override
    public boolean isStillInUse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isStillInUse'");
    }
    
}