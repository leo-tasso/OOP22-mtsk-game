package api;

/**
 * Class to manage RGB colors.
 *
 */
public class ColorRGB {
    private static final int COLOR_RANGE_TOP = 255;
    private static final int COLOR_RANGE_BOTTOM = 0;
    private final int red;
    private final int green;
    private final int blue;

    /**
     * Constructor for new RGB Color.
     * Values From {@value #COLOR_RANGE_BOTTOM} to {@value #COLOR_RANGE_TOP}.
     * 
     * @param red   the value of the red component
     * @param green the value of the green component.
     * @param blue  the value of the blue component.
     */
    public ColorRGB(final int red, final int green, final int blue) {
        if (!colorInRange(red) || !colorInRange(green) || !colorInRange(blue)) {
            throw new IllegalArgumentException("Illegal parameter, color range is 0-255 inclusive");
        }
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    /**
     * Method to check if a components respects the boundaries.
     * 
     * @param color the value to check.
     * @return if it respects the boundaries.
     */
    private boolean colorInRange(final int color) {
        return color >= COLOR_RANGE_BOTTOM && color <= COLOR_RANGE_TOP;
    }

    /**
     * Method to get the blue component
     * ({@value #COLOR_RANGE_BOTTOM}-{@value #COLOR_RANGE_TOP}).
     * 
     * @return the value of the blue component.
     */
    public int getBlue() {
        return blue;
    }

    /**
     * Method to get the green component
     * ({@value #COLOR_RANGE_BOTTOM}-{@value #COLOR_RANGE_TOP}).
     * 
     * @return the value of the green component.
     */
    public int getGreen() {
        return green;
    }

    /**
     * Method to get the red component
     * ({@value #COLOR_RANGE_BOTTOM}-{@value #COLOR_RANGE_TOP}).
     * 
     * @return the value of the red component.
     */
    public int getRed() {
        return red;
    }

    /**
     * Method to get a Red ColorRgb Object.
     * 
     * @return a Red ColorRgb Object.
     */
    public static ColorRGB red() {
        return new ColorRGB(COLOR_RANGE_TOP, COLOR_RANGE_BOTTOM, COLOR_RANGE_BOTTOM);
    }

    /**
     * Method to get a Green ColorRgb Object.
     * 
     * @return a Green ColorRgb Object.
     */
    public static ColorRGB green() {
        return new ColorRGB(COLOR_RANGE_BOTTOM, COLOR_RANGE_TOP, COLOR_RANGE_BOTTOM);
    }

    /**
     * Method to get a Blue ColorRgb Object.
     * 
     * @return a Blue ColorRgb Object.
     */
    public static ColorRGB blue() {
        return new ColorRGB(COLOR_RANGE_BOTTOM, COLOR_RANGE_BOTTOM, COLOR_RANGE_TOP);

    }

    /**
     * Method to get a White ColorRgb Object.
     * 
     * @return a White ColorRgb Object.
     */
    public static ColorRGB white() {
        return new ColorRGB(COLOR_RANGE_TOP, COLOR_RANGE_TOP, COLOR_RANGE_TOP);
    }

    /**
     * Method to get a Black ColorRgb Object.
     * 
     * @return a Black ColorRgb Object.
     */
    public static ColorRGB black() {
        return new ColorRGB(COLOR_RANGE_BOTTOM, COLOR_RANGE_BOTTOM, COLOR_RANGE_BOTTOM);
    }

    /**
     * Method to get a Orange ColorRgb Object.
     * 
     * @return a Orange ColorRgb Object.
     */
    public static ColorRGB orange() {
        return new ColorRGB(COLOR_RANGE_TOP, COLOR_RANGE_TOP / 2, COLOR_RANGE_BOTTOM);
    }
        /**
     * Method to get a lightBlue ColorRgb Object.
     * 
     * @return a lightBlue ColorRgb Object.
     */
    public static ColorRGB aqua() {
        return new ColorRGB(COLOR_RANGE_BOTTOM, COLOR_RANGE_TOP / 2, COLOR_RANGE_TOP / 2);
    }
}
