package game.view.javafx.viewstate.gamestate;

import java.util.Optional;

import game.controlling.Input;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of {@link InputButtons}.
 */
public class InputButtonsImpl implements InputButtons {
    private static final int ONE_BUTTON = 49;
    private static final int NINE_BUTTON = 57;
    private static final int KEYCODE_NUM_DIFF = 48;

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
            } else if (e.getCode().getCode() >= ONE_BUTTON && e.getCode().getCode() <= NINE_BUTTON) {
                input.setNumberPressed(Optional.of(e.getCode().getCode() - KEYCODE_NUM_DIFF));
            } else if (e.getCode().equals(KeyCode.SPACE)) {
                input.setJump(true);
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
            } else if (e.getCode().getCode() >= ONE_BUTTON && e.getCode().getCode() <= NINE_BUTTON) {
                input.setNumberPressed(Optional.empty());
            } else if (e.getCode().equals(KeyCode.SPACE)) {
                input.setJump(false);
            }
        });
    }
}
