package game.gameobject.flappybirdalikeobjects;

import api.Point2D;
import api.Vector2D;
import game.gameobject.GameObject;
import game.gameobject.SimplePhysics;
import game.minigame.Minigame;

public class FlappyPhysics extends SimplePhysics {

    private static final double Y_MIN = 0;
    private static final double Y_MAX = 1000;
    private final double cursorOffset;

    public FlappyPhysics(final double cursorSize) {
        this.cursorOffset = cursorSize / 2;
    }

    @Override
    public void update(long dt, GameObject obj, Minigame miniGame) {
        super.update(dt, obj, miniGame);
        if (obj.getCoor().getY() < Y_MIN + cursorOffset) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), Y_MIN + cursorOffset));
            obj.setVel(Vector2D.nullVector());
        }
        if (obj.getCoor().getY() > Y_MAX - cursorOffset) {
            obj.setCoor(new Point2D(obj.getCoor().getX(), Y_MAX - cursorOffset));
            obj.setVel(Vector2D.nullVector());
        }
    }
    
}
