package game.view;

import api.Point2D;
import game.gameobject.GameObject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * A class used to draw a circle.
 */
public class DrawingsImpl implements Drawings {
    private static final int CIRCLE_RADIUS = 100;
    private static final int COEFFICIENT = 1000;
    private final Graphics2D g2;
    private final Point2D startingPoint;
    private final float dimention;

    /**
     * The constructor for the Drawing instructions.
     * 
     * @param g2            the Graphics2D object.
     * @param startingPoint the up-left corner coordinates of the canvas
     * @param dimention     the current height of the canvas
     */
    @SuppressFBWarnings
    public DrawingsImpl(final Graphics2D g2, final Point2D startingPoint, final float dimention) {
        this.g2 = g2;
        this.startingPoint = startingPoint;
        this.dimention = dimention;
    }

    /**
     * The method with the instructions on how to draw a Circle object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawCircle(final GameObject object) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        final Point2D pos = object.getCoor();
        final int y = (int) pos.getY();
        final int x = (int) pos.getX();
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));
        final int rad = Math.round(dimention / COEFFICIENT * CIRCLE_RADIUS);
        g2.drawOval((int) ((x - rad) * dimention / COEFFICIENT + (int) startingPoint.getX()),
                (int) ((y - rad) * dimention / COEFFICIENT + (int) startingPoint.getY()), rad * 2, rad * 2);
    }
}
