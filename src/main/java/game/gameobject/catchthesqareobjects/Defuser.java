package game.gameobject.catchthesqareobjects;

import api.ColorRGB;
import api.Point2D;
import api.Vector2D;
import game.controlling.InputModel;
import game.gameobject.CircleAspect;
import game.gameobject.GameObject;

/**
 * Class to model the defuser for the catchTheSquare Minigame.
 */
public class Defuser extends GameObject {

    private static final double DUMP_COEFFICIENT = 2;
    private static final ColorRGB CIRCLE_COLOR = ColorRGB.black();

    /**
     * Constructor for the defuser.
     * 
     * @param coor              the spawn coordinates.
     * @param radius            the radius of the defuser.
     * @param defuserInputModel the InputModel to be used by the defuser.
     */
    public Defuser(final Point2D coor, final int radius, final InputModel defuserInputModel) {
        super(coor, Vector2D.nullVector(), 0, defuserInputModel, new BoundaryDumpedPhysics(radius, DUMP_COEFFICIENT),
                new CircleAspect(radius, CIRCLE_COLOR));
    }

}
