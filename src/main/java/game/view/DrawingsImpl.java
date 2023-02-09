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
 *
 */
public class DrawingsImpl implements Drawings {
    private final Graphics2D g2;

    /**
     * Sets the initial value for g2.
     * 
     * @param g2
     */
    @SuppressFBWarnings
    public DrawingsImpl(final Graphics2D g2) {
        this.g2 = g2;
    }

    /**
     * The method with the instructions on how to draw a Circle object.
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
        final int rad = 15;
        g2.drawOval(x - rad, y - rad, rad * 2, rad * 2);
    }
}
