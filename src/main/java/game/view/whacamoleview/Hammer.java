package game.view.whacamoleview;

import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.gameobject.AspectModel;
import game.gameobject.GameObject;
import game.gameobject.PhysicsModel;

/**
 * The hammer has no strategic role in the game, but only constitutes 
 * a graphical feedback of the player's input: a hammer animation will 
 * appear above the den hit by the user, to simulate the arcade version
 */
public class Hammer extends GameObject {

    /**
     * 
     * 
     * @param coor
     * @param vel
     * @param rotation
     * @param inputModel
     * @param physicsModel
     * @param aspectModel
     */
    public Hammer(Point2D coor, Vector2D vel, int rotation, InputModel inputModel, PhysicsModel physicsModel,
            AspectModel aspectModel) {
        super(coor, vel, rotation, inputModel, physicsModel, aspectModel);
        //TODO Auto-generated constructor stub
    }

    
    
}