package game.controlling;

/**
 * Interface to model the inputs.
 */
public interface Input {

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

}
