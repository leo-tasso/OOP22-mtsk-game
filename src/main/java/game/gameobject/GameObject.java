package game.gameobject;

import api.Input;
import api.InputModel;
import api.Point2D;
import api.Vector2D;
import game.minigame.Minigame;

/**
 * Abstract class to represent a gameobject.
 */
public class GameObject {
    private Point2D coor;
    private Vector2D vel;
    private int rotation;
    private InputModel inputModel;
    private PhysicsModel physicsModel;

    /**
     * Constructor for general GameObject.
     * 
     * @param coor       the initial coordinates
     * @param vel        the initial velocity.
     * @param rotation   the initial rotation.
     * @param inputModel the initial inputModel.
     * @param physicsModel the initial physicsModel.
     */
    public GameObject(final Point2D coor, final Vector2D vel, final int rotation, final InputModel inputModel,
            final PhysicsModel physicsModel) {
        this.coor = coor;
        this.vel = vel;
        this.rotation = rotation;
        this.inputModel = inputModel;
        this.physicsModel = physicsModel;
    }

    /**
     * Method to get the inputModel.
     * 
     * @return the input model
     */
    public InputModel getInputModel() {
        return inputModel;
    }

    /**
     * Method to set the input model.
     * 
     * @param inputModel the input model to set.
     */
    public void setInputModel(final InputModel inputModel) {
        this.inputModel = inputModel;
    }

    /**
     * Getter for the rotation.
     * 
     * @return the gameobject rotation.
     */
    public int getRotation() {
        return rotation;
    }

    /**
     * A setter for the rotation of the object.
     * 
     * @param rotation the new rotation for the object.
     */
    public void setRotation(final int rotation) {
        this.rotation = rotation;
    }

    /**
     * Getter for the coordinates.
     * 
     * @return the gameobject coordinate.
     */
    public Point2D getCoor() {
        return coor;
    }

    /**
     * A setter for the coordinates of the object.
     * 
     * @param coor the new coordinates for the object.
     */
    public void setCoor(final Point2D coor) {
        this.coor = coor;
    }

    /**
     * A getter for the speed vector of the object.
     * 
     * @return the speed vector.
     */
    public Vector2D getVel() {
        return vel;
    }

    /**
     * A setter for the speed vector of the object.
     * 
     * @param vel the new speed vector for the object.
     */
    public void setVel(final Vector2D vel) {
        this.vel = vel;
    }

    /**
     * A constructor for the object.
     * 
     * @param coor the starting coordinates.
     * @param vel  the starting speed.
     */
    public GameObject(final Point2D coor, final Vector2D vel) {
        this.coor = coor;
        this.vel = vel;
    }

    /**
     * The method to update the input of the object.
     *
     * @param input
     */
    public void updateinput(final Input input) {
        inputModel.update(this, input);
    }

    /**
     * The method to update the input of the object.
     * 
     * @param deltaTime the elapsed time between frames
     * @param m         the minigame of the object
     */
    public void updatePhysics(final long deltaTime, final Minigame m) {
        physicsModel.update(deltaTime, this, m);
    }

    /**
     * Getter method for the PhysicsModel.
     * 
     * @return the physics model of the object
     */
    public PhysicsModel getPhysicsModel() {
        return physicsModel;
    }

    /**
     * Setter method for the physicsModel.
     * 
     * @param physicsModel the new physicsModel
     */
    public void setPhysicsModel(final PhysicsModel physicsModel) {
        this.physicsModel = physicsModel;
    }
}
