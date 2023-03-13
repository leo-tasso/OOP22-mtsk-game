package game.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import api.ColorRGB;
import api.Point2D;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.gameobject.GameObject;

/**
 * A class used to draw a circle.
 */
public class SwingDrawings implements Drawings {
    private static final int COEFFICIENT = 1000;
    private final Graphics2D g2;
    // coordinates (related to the Jframe) of the upper left corner of the play
    // field
    private final Point2D startingPoint;
    // heigh of the play field (to scale the size of the objects inside of it)
    private final float dimention;

    /**
     * The constructor for the Drawing instructions.
     * 
     * @param g2            the Graphics2D object.
     * @param startingPoint the up-left corner coordinates of the canvas
     * @param dimention     the current height of the canvas
     */
    @SuppressFBWarnings
    public SwingDrawings(final Graphics2D g2, final Point2D startingPoint, final float dimention) {
        this.g2 = g2;
        this.startingPoint = startingPoint;
        this.dimention = dimention;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
    }

    /**
     * The method with the instructions on how to draw a Circle object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawCircle(final GameObject object, final ColorRGB color, final double radius) {
        final Point2D pos = object.getCoor();
        final int x = (int) pos.getX(); // x of the centre
        final int y = (int) pos.getY(); // y of the centre
        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g2.setStroke(new BasicStroke(2f)); // the pen width
        final int rad = (int) Math.round(dimention / COEFFICIENT * radius);
        g2.drawOval(
                // coordinates of the upper left corner of the square circumscribing the circle
                (int) ((x - rad) * dimention / COEFFICIENT + startingPoint.getX()),
                (int) ((y - rad) * dimention / COEFFICIENT + startingPoint.getY()),
                rad * 2, // side of the square
                rad * 2);
    }

    /**
     * The method with the instructions on how to draw a Triangle object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawTriangle(final GameObject object, final ColorRGB color, final double side, final double rotAngle) {
        final double radius = side / Math.sqrt(3);
        final double rotAngle1 = rotAngle + Math.PI * 2 / 3;
        final double rotAngle2 = rotAngle + Math.PI * 4 / 3;

        final List<Double> xs = new ArrayList<>();
        xs.add(object.getCoor().getX() + radius * Math.cos(rotAngle1));
        xs.add(object.getCoor().getX() + radius * Math.cos(rotAngle2));
        xs.add(object.getCoor().getX() + radius * Math.cos(rotAngle));

        final List<Double> ys = new ArrayList<>();
        ys.add(object.getCoor().getY() + radius * Math.sin(rotAngle1));
        ys.add(object.getCoor().getY() + radius * Math.sin(rotAngle2));
        ys.add(object.getCoor().getY() + radius * Math.sin(rotAngle));

        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g2.setStroke(new BasicStroke(2f));
        g2.drawPolygon(xs.stream().mapToInt(x -> (int) Math.round(x * dimention / COEFFICIENT + startingPoint.getX())).toArray(),
                ys.stream().mapToInt(y -> (int) Math.round(y * dimention / COEFFICIENT + startingPoint.getY())).toArray(), 3);
    }

    /**
     * The method with the instructions on how to draw a Square object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawSquare(final GameObject object, final ColorRGB color, final double side) {
        drawRectangle(object, color, side, side);
    }

    /**
     * The method with the instructions on how to draw a Rectangl object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawRectangle(final GameObject object, final ColorRGB color, final double width, final double height) {
        final double actualHeight = height * dimention / COEFFICIENT;
        final double actualWidth = width * dimention / COEFFICIENT;
        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(
                /*
                 * coordinates of the upper left corner of rectangle: the
                 * last addendum is necessary to enter the right play field
                 */
                (int) ((object.getCoor().getX() - width / 2) * dimention / COEFFICIENT + startingPoint.getX()),
                (int) ((object.getCoor().getY() - height / 2) * dimention / COEFFICIENT + startingPoint.getY()),
                (int) (actualWidth),
                (int) (actualHeight));
    }

    @Override
    public void drawMole(final GameObject object, final ColorRGB color) {

    }

}
