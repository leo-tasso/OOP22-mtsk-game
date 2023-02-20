package game.controlling;

import game.gameobject.GameObject;

/**
 * Class for GameObjects that reqires no inputs.
 */
public class NullInput implements InputModel {

    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
    }

}
