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
     * Getter for the first component).
     * 
     * @return the first component.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the second component.
     * 
     * @return the second component.
     */
    public double getY() {
        return y;
    }

    /**
     * Method to sum vectors.
     * 
     * @param v2 vector to sum.
     * @return the result vector.
     */
    public Vector2D sum(final Vector2D v2) {
        return new Vector2D(this.x + v2.x, this.y + v2.y);
    }

    /**
     * Method to multiply the vector by a coefficient.
     * 
     * @param alpha the coefficent.
     * @return the result vector.
     */
    public Vector2D mul(final double alpha) {
        return new Vector2D(alpha * this.x, alpha * this.y);
    }

    /**
     * Method to calculate the module of the vector.
     * 
     * @return the module of the vector.
     */
    public double module() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    /**
     * Method to calculate the opposite vector.
     * 
     * @return the opposite of the vector.
     */
    public Vector2D invert() {
        return new Vector2D(-this.x, -this.y);
    }

    /**
     * String representation of Vector2D.
     */
    @Override
    public String toString() {
        return "Vector2D (" + this.x + ", " + this.y + ")";
    }

    /**
     * Hash code generator for Vector2D.
     */
    @Override
    public int hashCode() {
        final int prime = 79;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Equals method for Vector2D.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vector2D other = (Vector2D) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns a null Vector2D (0,0).
     * 
     * @return a null Vector2D (0,0).
     */
    public static Vector2D nullVector() {
        return new Vector2D(0, 0);
    }

}
