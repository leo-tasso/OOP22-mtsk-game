package game.engine.gameobject.catchthesqareobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.engine.gameobject.CircleAspect;
import game.engine.gameobject.GameObject;
import game.engine.gameobject.PhysicsModel;
import game.engine.gameobject.hitboxmodel.CircleHitBoxModel;

/**
 * Class to model the defuser for the catchTheSquare Minigame.
 */
public class Defuser extends GameObject {

    private static final ColorRGB CIRCLE_COLOR = ColorRGB.black();

    /**
     * Constructor for the defuser.
     * 
     * @param coor                the spawn coordinates.
     * @param radius              the radius of the defuser.
     * @param defuserInputModel   the InputModel to be used by the defuser.
     * @param defuserPhysicsModel the PhysicsModel to be used by the defuser.
     */
    public Defuser(final Point2D coor, final int radius, final InputModel defuserInputModel,
            final PhysicsModel defuserPhysicsModel) {
        super(coor, Vector2D.nullVector(), 0, defuserInputModel, defuserPhysicsModel,
                new CircleAspect(radius, CIRCLE_COLOR), new CircleHitBoxModel(radius));
    }

}
