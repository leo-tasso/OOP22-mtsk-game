package api;

/**
 * Class to implement 2d points.
 */
public class Point2D {

    private final double x;
    private final double y;

    /**
     * Constructor with 2 arguments.
     * 
     * @param x first component
     * @param y
     */
    public Point2D(final double x, final double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the first component.
     * 
     * @return double of the first component.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the second component.
     * 
     * @return double of the second component.
     */
    public double getY() {
        return y;
    }

    /**
     * Sums two points.
     * 
     * @param p2 the point to sum.s
     * @return the new point.
     */
    public Point2D sum(final Point2D p2) {
        return new Point2D(this.x + p2.getX(), this.y + p2.getY());
    }

    /**
     * Sums a vector to a point.
     * 
     * @param v2 the point to sum.
     * @return the new point.
     */
    public Point2D sum(final Vector2D v2) {
        return new Point2D(this.x + v2.getX(), this.y + v2.getY());
    }

    /**
     * Subtracts two points.
     * 
     * @param p2 the point to subtract.
     * @return the result point.
     */
    public Point2D sub(final Point2D p2) {
        return this.sum(p2.invert());
    }

    /**
     * To invert the sign of the point.
     * 
     * @return the inverted point.
     */
    public Point2D invert() {
        return new Point2D(-this.x, -this.y);
    }

    /**
     * Hash code generator for Point2D.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Equals method for Point2D.
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
        final Point2D other = (Point2D) obj;
        return this.x == other.x && this.y == other.y;
    }

    /**
     * To string method for Point2D.
     */
    @Override
    public String toString() {
        return "Point2D (" + this.x + ", " + this.y + ")";
    }
}
