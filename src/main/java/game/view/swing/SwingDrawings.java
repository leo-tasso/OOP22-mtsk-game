package game.view.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.FontMetrics;

import api.ColorRGB;
import api.Point2D;
import game.engine.gameobject.GameObject;
import game.view.Drawings;

/**
 * A class used to draw a circle.
 */
public class SwingDrawings implements Drawings {
    private final int coefficient;
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
     * @param coefficient   the height in points of the field that the view shall
     *                      display
     */
    public SwingDrawings(final Graphics2D g2, final Point2D startingPoint, final float dimention,
            final int coefficient) {
        this.coefficient = coefficient;
        this.g2 = (Graphics2D) g2.create();
        this.startingPoint = startingPoint;
        this.dimention = dimention;
        this.g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        this.g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_SPEED);
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
        final int rad = (int) Math.round(dimention / coefficient * radius);
        g2.drawOval(
                // coordinates of the upper left corner of the square circumscribing the circle
                (int) (x * dimention / coefficient - rad + startingPoint.getX()),
                (int) (y * dimention / coefficient - rad + startingPoint.getY()),
                rad * 2, // side of the square
                rad * 2);
    }

    /**
     * The method with the instructions on how to draw a Triangle object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawTriangle(final GameObject object, final ColorRGB color, final double side, final double angle) {
        final double radius = side / Math.sqrt(3);
        final double angle1 = angle + Math.PI * 2 / 3;
        final double angle2 = angle + Math.PI * 4 / 3;

        final List<Double> xs = new ArrayList<>();
        xs.add(object.getCoor().getX() + radius * Math.cos(angle1));
        xs.add(object.getCoor().getX() + radius * Math.cos(angle2));
        xs.add(object.getCoor().getX() + radius * Math.cos(angle));

        final List<Double> ys = new ArrayList<>();
        ys.add(object.getCoor().getY() + radius * Math.sin(angle1));
        ys.add(object.getCoor().getY() + radius * Math.sin(angle2));
        ys.add(object.getCoor().getY() + radius * Math.sin(angle));

        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g2.setStroke(new BasicStroke(2f));
        g2.drawPolygon(xs.stream().mapToInt(x -> (int) Math.round(x * dimention / coefficient + startingPoint.getX())).toArray(),
                ys.stream().mapToInt(y -> (int) Math.round(y * dimention / coefficient + startingPoint.getY())).toArray(), 3);
    }

    /**
     * The method with the instructions on how to draw a Square object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawSquare(final GameObject object, final ColorRGB color, final double side, final boolean filled) {
        drawRectangle(object, color, side, side, filled);
    }

    /**
     * The method with the instructions on how to draw a Rectangl object.
     *
     * @param object the object to draw
     */
    @Override
    public void drawRectangle(final GameObject object,
            final ColorRGB color,
            final double width,
            final double height,
            final boolean filled) {
        final double actualHeight = height * dimention / coefficient;
        final double actualWidth = width * dimention / coefficient;
        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        g2.setStroke(new BasicStroke(2f));
        g2.drawRect(
                /*
                 * coordinates of the upper left corner of rectangle: the
                 * last addendum is necessary to enter the right play field
                 */
                (int) ((object.getCoor().getX() - width / 2) * dimention / coefficient + startingPoint.getX()),
                (int) ((object.getCoor().getY() - height / 2) * dimention / coefficient + startingPoint.getY()),
                (int) (actualWidth),
                (int) (actualHeight));
    }

    /**
     * Method to draw a label.
     * 
     * @param object the label gameObject.
     * @param color  the color of the label.
     * @param size   the size of the label.
     * @param string the string to draw.
     */
    @Override
    public void drawLabel(final GameObject object, final ColorRGB color, final int size, final String string) {
        final FontRenderContext frc = g2.getFontRenderContext();
        final Font font = new Font("Courier", Font.BOLD, (int) (size * dimention / coefficient));
        final TextLayout tl = new TextLayout(string, font, frc);
        g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue()));
        final FontMetrics fm = g2.getFontMetrics(font);
        final double x = object.getCoor().getX() * dimention / coefficient - (float) fm.stringWidth(string) / 2;
        final double y = object.getCoor().getY() * dimention / coefficient
                + (float) (fm.getHeight() - fm.getAscent()) / 2;
        tl.draw(g2, (float) (x + startingPoint.getX()), (float) (y + startingPoint.getY()));
    }

    /**
     * Currently using JavaFx.
     */
    @Override
    public void drawMole(final GameObject object, final Boolean beenHit) {
        throw new UnsupportedOperationException("Unimplemented method 'drawMole'");
    }

    /**
     * Currently using JavaFx.
     */
    @Override
    public void drawWamBomb(final GameObject object, final Boolean beenHit) {
        throw new UnsupportedOperationException("Unimplemented method 'drawBomb'");
    }

    /**
     * Currently using JavaFx.
     */
    @Override
    public void drawHoleUpperPart(final GameObject object) {
        throw new UnsupportedOperationException("Unimplemented method 'drawHoleUpperPart'");
    }

    /**
     * Currently using JavaFx.
     */
    @Override
    public void drawHoleLowerPart(final GameObject object) {
        throw new UnsupportedOperationException("Unimplemented method 'drawHoleLowerPart'");
    }
}
