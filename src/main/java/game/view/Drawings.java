package game.view;

import api.ColorRGB;
import game.gameobject.GameObject;

/**
 * The interface to update the way an object is drawn.
 * 
 */
public interface Drawings {

    /**
     * The instructions on how to draw the circle.
     * 
     * @param object the gameObject of the circle to draw.
     * @param color  the color of the circle to draw.
     */
    void drawCircle(GameObject object, ColorRGB color);

    /**
     * The instructions on how to draw a sqare.
     * 
     * @param object the gameObject of the square to draw.
     * @param color  the color of the square to draw.
     * @param side   the lenght of the side of the square.
     */
    void drawSquare(GameObject object, ColorRGB color, double side);

    /**
     * The instructions on how to draw a rectangle.
     * 
     * @param object the gameObject of the rectangle to draw.
     * @param color  the color of the rectangle to draw.
     * @param width  the width of the rectangle to draw.
     * @param height the height of the rectangle to draw.
     */
    void drawRectangle(GameObject object, ColorRGB color, double width, double height);

    /**
     * The instructions on how to draw a triangle.
     * 
     * @param object the gameObject of the triangle to draw.
     * @param color  the color of the triangle to draw.
     */
    void drawTriangle(GameObject object, ColorRGB color);

    /**
     * The instructions on how to draw a mole.
     * 
     * @param object the gameObject of the mole to draw.
     * @param color  the color of the mole to draw.
     */
    void drawMole(GameObject object, ColorRGB color);

}
