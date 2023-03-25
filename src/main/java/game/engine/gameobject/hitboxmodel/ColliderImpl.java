package game.engine.gameobject.hitboxmodel;

import game.engine.gameobject.GameObject;

/**
 * Implementation for the Collider.
 */
public class ColliderImpl implements Collider {
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isColliding(final GameObject g, final GameObject h) {
        if (g.getHitBoxModel() instanceof CircleHitBoxModel && h.getHitBoxModel() instanceof RectangleHitBoxModel) {
            return circleRectangleCompare(g, h);
        }
        if (h.getHitBoxModel() instanceof CircleHitBoxModel && g.getHitBoxModel() instanceof RectangleHitBoxModel) {
            return circleRectangleCompare(h, g);
        }
        if (h.getHitBoxModel() instanceof RectangleHitBoxModel && g.getHitBoxModel() instanceof RectangleHitBoxModel) {
            return rectangleRectangleCompare(h, g);
        }
        return false;
    }

    /**
     * Checks if two rectangles are colliding.
     * 
     * @param g the first rectangle.
     * @param h the second rectangle.
     * @return if the two rectangles are colliding.
     */
    private boolean rectangleRectangleCompare(final GameObject h, final GameObject g) {
        return h.getCoor().getX() - h.getHitBoxModel().getSizes().get(0) / 2 < g.getCoor().getX()
                + g.getHitBoxModel().getSizes().get(0) / 2
                && h.getCoor().getX() + h.getHitBoxModel().getSizes().get(0) / 2 > g.getCoor().getX()
                        - g.getHitBoxModel().getSizes().get(0) / 2
                &&
                h.getCoor().getY() - h.getHitBoxModel().getSizes().get(1) / 2 < g.getCoor().getY()
                        + g.getHitBoxModel().getSizes().get(1) / 2
                &&
                h.getCoor().getY() + h.getHitBoxModel().getSizes().get(1) / 2 > g.getCoor().getY()
                        - g.getHitBoxModel().getSizes().get(1) / 2;
    }

    /**
     * Checks if a circle and a rectangle are colliding.
     * 
     * @param circle    the circle.
     * @param rectangle the rectangle.
     * @return if the circle and the rectangle are collidign.
     */
    private boolean circleRectangleCompare(final GameObject circle, final GameObject rectangle) {
        final double circleDistancex = Math.abs(circle.getCoor().getX() - rectangle.getCoor().getX());
        final double rectWidth = rectangle.getHitBoxModel().getSizes().get(0);
        final double circleRad = circle.getHitBoxModel().getSizes().get(0);
        if (circleDistancex > (rectWidth / 2 + circleRad)) {
            return false;
        }
        final double circleDistancey = Math.abs(circle.getCoor().getY() - rectangle.getCoor().getY());
        final double rectHeight = rectangle.getHitBoxModel().getSizes().get(1);
        if (circleDistancey > (rectHeight / 2 + circleRad)) {
            return false;
        }

        if (circleDistancex <= (rectWidth / 2)) {
            return true;
        }
        if (circleDistancey <= (rectHeight / 2)) {
            return true;
        }

        final double cornerDistanceSq = Math.pow(circleDistancex - rectWidth / 2, 2)
                + Math.pow(circleDistancey - rectHeight / 2, 2);

        return cornerDistanceSq <= Math.pow(circleRad, 2);
    }

}
