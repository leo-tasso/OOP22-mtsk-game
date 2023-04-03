package game.engine.gameobject;

import game.controlling.Input;

/**
 * Class for GameObjects that reqires no inputs.
 */
public class NullInput implements InputModel {

    @Override
    public void update(final GameObject obj, final Input c, final long elapsedTime) {
    }

}
