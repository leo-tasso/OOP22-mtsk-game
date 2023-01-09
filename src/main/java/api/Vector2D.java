package api;

/**
 * Class to implement a 2D vector.
 */
public class Vector2D {
    private final double x;
    private final double y;
    /**
     * Constructor for 2D vector 0->(x,y).
     * 
     * @param x x of the vector.
     * @param y y of the vector.
     */
    public Vector2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     *  Getter for the first component).
     *  @return the first component.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the second component.
     * @return the second component.
     */
    public double getY() {
        return y;
    }

    /**
     * Method to sum vectors.
     * @param v2 vector to sum.
     * @return the result vector.
     */
    public Vector2D sum(final Vector2D v2) {
        return new Vector2D(this.x + v2.x, this.y + v2.y);
    }

    /**
     * Method to multiply the vector by a coefficient.
     * @param alpha the coefficent.
     * @return  the result vector.
     */
    public Vector2D mul(final double alpha) {
        return new Vector2D(alpha * this.x, alpha * this.y);
    }

    /**
     * Method to calculate the module of the vector.
     * @return the module of the vector.
     */
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     *  Method to calculate the opposite vector.
        @return the opposite of the vector.
     */
    public Vector2D invert() {
        return new Vector2D(-this.x, -this.y);
    }

    /**
     *  String representation of Vector2D.
     */
    @Override
    public String toString() {
        return "Vector2D (" + this.x + ", " + this.y + ")";
    }
}
