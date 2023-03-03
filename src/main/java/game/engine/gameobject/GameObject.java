package game.engine.gameobject;

import game.controlling.Input;
import game.controlling.InputModel;
import game.engine.minigame.Minigame;
import api.Point2D;
import api.Vector2D;
import game.view.Drawings;

/**
 * Abstract class to represent a gameobject.
 */
public class GameObject {
    private Point2D coor;
    private Vector2D vel;
    private int rotation;
    private InputModel inputModel;
    private PhysicsModel physicsModel;
    private AspectModel aspectModel;

    /**
     * Constructor for general GameObject.
     * 
     * @param coor         the initial coordinates
     * @param vel          the initial velocity.
     * @param rotation     the initial rotation.
     * @param inputModel   the initial inputModel.
     * @param physicsModel the initial physicsModel.
     * @param aspectModel  the initial aspectModel.
     */
    public GameObject(final Point2D coor, final Vector2D vel, final int rotation, final InputModel inputModel,
            final PhysicsModel physicsModel, final AspectModel aspectModel) {
        this.coor = coor;
        this.vel = vel;
        this.rotation = rotation;
        this.inputModel = inputModel;
        this.physicsModel = physicsModel;
        this.aspectModel = aspectModel;
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
     * Method to get the inputModel.
     * 
     * @return the input model
     */
    public InputModel getInputModel() {
        return this.inputModel;
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
        return this.rotation;
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
        return this.coor;
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
        return this.vel;
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
     * The method to update the input of the object.
     *
     * @param input
     * @param elapsedTime the time elapsed from the last frame
     */
    public void updateinput(final Input input, final long elapsedTime) {
        inputModel.update(this, input, elapsedTime);
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
     * Getter method for the physicsModel.
     * 
     * @return the physics model of the object
     */
    public PhysicsModel getPhysicsModel() {
        return this.physicsModel;
    }

    /**
     * Setter method for the physicsModel.
     * 
     * @param physicsModel the new physicsModel
     */
    public void setPhysicsModel(final PhysicsModel physicsModel) {
        this.physicsModel = physicsModel;
    }

    /**
     * Getter method for the aspectModel.
     * 
     * @return the aspect model of the object
     */
    public AspectModel getAspectModel() {
        return this.aspectModel;
    }

    /**
     * Setter method for the aspectModel.
     * 
     * @param aspectModel the new aspectModel
     */
    public void setAspectModel(final AspectModel aspectModel) {
        this.aspectModel = aspectModel;
    }

    /**
     * The method to update the object drawing.
     * 
     * @param drawing the instructions on how to draw the object
     */
    public void updateAspect(final Drawings drawing) {
        aspectModel.update(this, drawing);
    }
}
