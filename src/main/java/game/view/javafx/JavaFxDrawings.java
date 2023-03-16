package game.view.javafx;

import api.ColorRGB;
import api.Point2D;
import game.engine.gameobject.GameObject;
import game.view.Drawings;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * Implementation of Drawings for JavaFX.
 */
public class JavaFxDrawings implements Drawings {
    private final int coefficient;
    // coordinates (related to the Jframe) of the upper left corner of the play
    // field
    private final Point2D startingPoint;

    // heigh of the play field (to scale the size of the objects inside of it)
    private final double dimention;

    private final GraphicsContext gc;

    /**
     * Constructor for the class.
     * 
     * @param canvas        the canvas in which to draw.
     * @param startingPoint the starting point for the minigame area.
     * @param dimention     dimention of the hight of the minigame area.
     * @param coefficient   the height in points of the field that the view shall
     *                      display.
     */
    public JavaFxDrawings(final Canvas canvas, final Point2D startingPoint, final double dimention,
            final int coefficient) {
        this.coefficient = coefficient;
        this.gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3.0);
        this.startingPoint = startingPoint;
        this.dimention = dimention;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCircle(final GameObject object, final ColorRGB color, final double radius) {
        final Point2D pos = object.getCoor();
        final int x = (int) pos.getX(); // x of the centre
        final int y = (int) pos.getY(); // y of the centre
        final int scaledRad = (int) Math.round(dimention / coefficient * radius);
        gc.setStroke(jfxColor(color));
        gc.strokeOval(x * dimention / coefficient - scaledRad + startingPoint.getX(),
                y * dimention / coefficient - scaledRad + startingPoint.getY(),
                scaledRad * 2, scaledRad * 2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawSquare(final GameObject object, final ColorRGB color, final double side) {
        drawRectangle(object, color, side, side);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final GameObject object, final ColorRGB color, final double width, final double height) {
        final double actualHeight = height * dimention / coefficient;
        final double actualWidth = width * dimention / coefficient;
        gc.setStroke(jfxColor(color));
        gc.strokeRect(
                /*
                 * coordinates of the upper left corner of rectangle: the
                 * last addendum is necessary to enter the right play field
                 */
                (object.getCoor().getX() - width / 2) * dimention / coefficient + startingPoint.getX(),
                (object.getCoor().getY() - height / 2) * dimention / coefficient + startingPoint.getY(),
                actualWidth,
                actualHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawTriangle(final GameObject object, final ColorRGB color, final double radius) {
        gc.setFill(jfxColor(color));
        gc.fillPolygon(new double[] {
                (object.getCoor().getX() - radius / 2) * dimention / coefficient + startingPoint.getX(),
                (object.getCoor().getX() - radius / 2) * dimention / coefficient + startingPoint.getX(),
                (object.getCoor().getX() + radius) * dimention / coefficient + startingPoint.getX() },
                new double[] {
                        (object.getCoor().getY() - radius * Math.sqrt(3) / 2) * dimention / coefficient
                                + startingPoint.getY(),
                        (object.getCoor().getY() + radius * Math.sqrt(3) / 2) * dimention / coefficient
                                + startingPoint.getY(),
                        object.getCoor().getY() * dimention / coefficient + startingPoint.getY(),
                }, 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawLabel(final GameObject object, final ColorRGB color, final int size, final String string) {
        gc.setFill(jfxColor(color));
        gc.setFont(new Font("futura", size * dimention / coefficient));
        final double x = object.getCoor().getX() * dimention / coefficient;
        final double y = object.getCoor().getY() * dimention / coefficient;
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.CENTER);
        gc.fillText(string, x + startingPoint.getX(), y + startingPoint.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawMole(final GameObject object, final Boolean beenHit) {
        // TODO Auto-generated method stub
        if (!beenHit) {
            drawCircle(object, ColorRGB.white(), 0);
        } else {
            drawCircle(object, ColorRGB.red(), 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawBomb(final GameObject object, final Boolean beenHit) {
        if (!beenHit) {
            drawSquare(object, ColorRGB.blue(), 0);
        } else {
            drawSquare(object, ColorRGB.aqua(), 0);
        }
    }

    /**
     * Method to translate ColorRGB colors to JavaFx Color.
     * 
     * @param color the ColorRgb input color.
     * @return the corresponding JavaFx Color.
     */
    private Paint jfxColor(final ColorRGB color) {
        return Color.color(color.getRed() / (double) ColorRGB.COLOR_RANGE_TOP,
                color.getGreen() / (double) ColorRGB.COLOR_RANGE_TOP,
                color.getBlue() / (double) ColorRGB.COLOR_RANGE_TOP);
    }

}
