package game.view.javafx.viewstate.gamestate;

import game.controlling.Input;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of {@link InputButtons}.
 */
public class InputButtonsImpl implements InputButtons {

    /**
     * {@inheritDoc}
     */
    @Override
    public void attach(final Scene scene, final Input input) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.W)) {
                input.setMoveUp(true);
            } else if (e.getCode().equals(KeyCode.A)) {
                input.setMoveLeft(true);
            } else if (e.getCode().equals(KeyCode.S)) {
                input.setMoveDown(true);
            } else if (e.getCode().equals(KeyCode.D)) {
                input.setMoveRight(true);
            }
        });
        scene.addEventHandler(KeyEvent.KEY_RELEASED, e -> {
            if (e.getCode().equals(KeyCode.W)) {
                input.setMoveUp(false);
            } else if (e.getCode().equals(KeyCode.A)) {
                input.setMoveLeft(false);
            } else if (e.getCode().equals(KeyCode.S)) {
                input.setMoveDown(false);
            } else if (e.getCode().equals(KeyCode.D)) {
                input.setMoveRight(false);
            }
        });
    }
}
