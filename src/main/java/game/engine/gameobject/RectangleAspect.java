package game.engine.gameobject;

import api.ColorRGB;
import game.view.Drawings;

/**
 * Class to model the aspect of a Rectangle GameObject.
 */
public class RectangleAspect implements AspectModel {
    private int width;
    private int height;
    private ColorRGB color;
    private final boolean filled;

    /**
     * Constructor with the dimentions of the rectangle.
     * 
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     * @param color  the color of the rectangle.
     */
    public RectangleAspect(final int width, final int height, final ColorRGB color, final boolean filled) {
        this.width = width;
        this.height = height;
        this.color = color;
        this.filled = filled;
    }

    /**
     * The method that performs the re-drawing of an object (every "frame"), which
     * happens regardless of the inputs received, since objects move by themselves,
     * following the strategy contained in the Drawings parameter.
     */
    @Override
    public void update(final GameObject object, final Drawings d) {
        d.drawRectangle(object, color, width, height, filled);
    }

    /**
     * Method to get the Width of the rectangle.
     * 
     * @return the Width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * Method to set the Width of the rectangle.
     * 
     * @param width the new width.
     */
    public void setWidth(final int width) {
        this.width = width;
    }

    /**
     * Method to get the height of the rectangle.
     * 
     * @return the height of the rectangle
     */
    public int getHeight() {
        return height;
    }

    /**
     * Method to set the height of the rectangle.
     * 
     * @param height the new height.
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Method to get the color of the rectangle.
     * 
     * @return the color of the rectangle.
     */
    public ColorRGB getColor() {
        return color;
    }

    /**
     * Method to set the color of the rectangle.
     * 
     * @param color the color of the rectangle.
     */
    public void setColor(final ColorRGB color) {
        this.color = color;
    }
}
