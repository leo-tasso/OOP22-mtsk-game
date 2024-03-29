package game.controlling;

import java.util.Optional;

/**
 * Interface to model the inputs.
 */
public interface Input extends Cloneable {

    /**
     * Method to check if moveUp input is active.
     * 
     * @return if the moveUp command is active
     */
    boolean isMoveUp();

    /**
     * Method to check if moveDown input is active.
     * 
     * @return if the moveDown command is active
     */
    boolean isMoveDown();

    /**
     * Method to check if moveRight input is active.
     * 
     * @return if the moveRight command is active
     */
    boolean isMoveRight();

    /**
     * Method to check if moveLeft input is active.
     * 
     * @return if the moveLeft command is active
     */
    boolean isMoveLeft();

    /**
     * Method to check if jump input is active.
     * 
     * @return if the jump command is active
     */
    boolean isJump();

    /**
     * Method to check if forward input is active.
     * 
     * @return if the forward command is active
     */
    boolean isForward();

    /**
     * Method to check if backwards input is active.
     * 
     * @return if the backwards command is active
     */
    boolean isBackwards();

    /**
     * Method to set if moveUp input is active.
     * 
     * @param moveUp if the moveUp command is active
     */
    void setMoveUp(boolean moveUp);

    /**
     * Method to set moveDown input.
     * 
     * @param moveDown if the moveDown command is active
     */
    void setMoveDown(boolean moveDown);

    /**
     * Method to set if moveRight input is active.
     * 
     * @param moveRight if the moveRight command is active
     */
    void setMoveRight(boolean moveRight);

    /**
     * Method to set moveLeft input.
     * 
     * @param moveLeft new value for moveLeft
     */
    void setMoveLeft(boolean moveLeft);

    /**
     * Method that returns an Optional containing, 
     * possibly, the number pressed (in [1,9]).
     * 
     * @return an Optional of Integer
     */
    Optional<Integer> getNumberPressed();

    /**
     * Sets the number pressed on the keyboard by the player.
     * 
     * @param numberPressed if present, an int in [1,9]
     */
    void setNumberPressed(Optional<Integer> numberPressed);

    /**
     * Method to set jump input.
     * 
     * @param jump new value for jump
     */
    void setJump(boolean jump);

    /**
     * Method to set forward input.
     * 
     * @param forward new value for forward
     */
    void setForward(boolean forward);

    /**
     * Method to set backwards input.
     * 
     * @param backwards new value for backwards
     */
    void setBackwards(boolean backwards);

    /**
     * Method to reset all inputs to false.
     */
    void reset();

    /**
     * Method to clone the object.
     * 
     * @return the new copy of the object.
     */
    Input clone();
}
