package game.view.javafx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import api.ColorRGB;
import api.Point2D;
import game.engine.gameobject.GameObject;
import game.view.Drawings;
import game.view.WamImagesCache;
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
    /* coordinates (related to the Jframe) of  */
    /* the upper left corner of the play field */
    private final Point2D startingPoint;
    /* heigh of the play field (to scale     */
    /* the size of the objects inside of it) */
    private final double dimention;
    private final GraphicsContext gc;
    private final WamImagesCache wamCache;

    /**
     * Constructor for the class.
     * 
     * @param canvas        the canvas in which to draw.
     * @param startingPoint the starting point for the minigame area.
     * @param dimention     dimention of the hight of the minigame area.
     * @param coefficient   the height in points of the field that the view shall
     *                      display.
     * @throws FileNotFoundException
     */
    public JavaFxDrawings(final Canvas canvas, final Point2D startingPoint, final double dimention,
            final int coefficient) throws FileNotFoundException {
        this.coefficient = coefficient;
        this.gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(3.0);
        this.startingPoint = startingPoint;
        this.dimention = dimention;
        this.wamCache = new WamImagesCache(coefficient, dimention);
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
    public void drawSquare(final GameObject object, final ColorRGB color, final double side, final boolean filled) {
        drawRectangle(object, color, side, side, filled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawRectangle(final GameObject object,
            final ColorRGB color,
            final double width,
            final double height,
            final boolean filled) {
        final double actualHeight = height * dimention / coefficient;
        final double actualWidth = width * dimention / coefficient;

        if (filled) {
            gc.setFill(jfxColor(color));
            gc.fillRect(
                    /*
                     * coordinates of the upper left corner of rectangle: the
                     * last addendum is necessary to enter the right play field
                     */
                    (object.getCoor().getX() - width / 2) * dimention / coefficient + startingPoint.getX(),
                    (object.getCoor().getY() - height / 2) * dimention / coefficient + startingPoint.getY(),
                    actualWidth,
                    actualHeight);
        } else {
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
    }

    /**
     * {@inheritDoc}
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

        gc.setFill(jfxColor(color));
        gc.fillPolygon(xs.stream().mapToDouble(x -> x * dimention / coefficient + startingPoint.getX()).toArray(),
                ys.stream().mapToDouble(y -> y * dimention / coefficient + startingPoint.getY()).toArray(), 3);
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
        if (beenHit) {
            gc.drawImage(wamCache.getHitMoleImage(), object.getCoor().getX() * dimention / coefficient,
                                                     object.getCoor().getY() * dimention / coefficient);
        } else {
            gc.drawImage(wamCache.getMoleImage(), object.getCoor().getX() * dimention / coefficient,
                                                  object.getCoor().getY() * dimention / coefficient);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawWamBomb(final GameObject object, final Boolean beenHit) {
        if (beenHit) {
            gc.drawImage(wamCache.getHitBombImage(), object.getCoor().getX() * dimention / coefficient,
                                                     object.getCoor().getY() * dimention / coefficient);
        } else {
            gc.drawImage(wamCache.getBombImage(), object.getCoor().getX() * dimention / coefficient, 
                                                  object.getCoor().getY() * dimention / coefficient);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawHoleUpperPart(final GameObject object) {
        gc.drawImage(wamCache.getHoleUpperPartImage(), object.getCoor().getX() * dimention / coefficient, 
                                                       object.getCoor().getY() * dimention / coefficient);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawHoleLowerPart(final GameObject object) {
        gc.drawImage(wamCache.getHoleLowerPartImage(), object.getCoor().getX() * dimention / coefficient, 
                                                       object.getCoor().getY() * dimention / coefficient);
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
