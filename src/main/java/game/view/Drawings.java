package game.view;

import api.ColorRGB;
import game.engine.gameobject.GameObject;

/**
 * The interface to update the way an object is drawn.
 */
public interface Drawings {

    /**
     * The instructions on how to draw the circle.
     * 
     * @param object the gameObject of the circle to draw.
     * @param color  the color of the circle to draw.
     * @param radius the radius of the circle.
     */
    void drawCircle(GameObject object, ColorRGB color, double radius);

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
     * @param object   the gameObject of the triangle to draw.
     * @param color    the color of the triangle to draw.
     * @param side     the side lenght of the triangle.
     * @param rotAngle the angle of rotation of the triangle.
     */
    void drawTriangle(GameObject object, ColorRGB color, double side, double rotAngle);

    /**
     * The instructions on how to draw a mole.
     * 
     * @param object the gameObject of the mole to draw.
     * @param beenHit indicates which of the two aspects to represent.
     */
    void drawMole(GameObject object, Boolean beenHit);

    /**
     * The instructions on how to draw a bomb.
     * 
     * @param object the gameObject of the bomb to draw.
     * @param beenHit indicates which of the two aspects to represent.
     */
    void drawBomb(GameObject object, Boolean beenHit);

    /**
     * The instructions on how to draw the upper part of the hole.
     * 
     * @param object the Hole's section to be drawn
     */
    void drawHoleUpperPart(GameObject object);

    /**
     * The instructions on how to draw the lower part of the hole.
     * 
     * @param object the Hole's section to be drawn
     */
    void drawHoleLowerPart(GameObject object);

    /**
     * Method to draw a label.
     * 
     * @param object the label gameObject.
     * @param color  the color of the label.
     * @param size   the size of the label.
     * @param string the string to draw.
     */
    void drawLabel(GameObject object, ColorRGB color, int size, String string);

}
