package game.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Class that acts as an external memory that contains
 * the images used in the minigames, being that if
 * they had to be re-instantiated every time inside
 * JavaFxDrawings the performance would drop drastically.
 */
public final class ImagesCache {
    /**
     * Width of the rectangle circumscribing
     * each of the two parts of the hole.
     */
    public static final int HOLE_WIDTH = 250;
    /** Height of the rectangle circumscribing the "tube". */
    public static final int HEIGHT_LOWER_PART = 150;
    /** Height of the rectangle circumscribing the "semicircle". */
    public static final int HEIGHT_UPPER_PART = 80;
    /** Height of the rectangle circumscribing the mole. */
    public static final int MOLE_HEIGHT = 150;
    /** Height of the rectangle surrounding the bomb. */
    public static final int BOMB_HEIGHT = 150;
    /** Width of the rectangle circumscribing the mole. */
    public static final int MOLE_WIDTH = 190;
    /** Width of the rectangle circumscribing the bomb. */
    public static final int BOMB_WIDTH = 190;
    /** A mole image. */
    public static final Image MOLE_IMAGE;
    /** A Bomb Image. */
    public static final Image BOMB_IMAGE;
    /** A hit Mole Image. */
    public static final Image HIT_MOLE_IMAGE;
    /** A hit Bomb Image. */
    public static final Image HIT_BOMB_IMAGE;
    /** A Image of the Lower Part of the imag. */
    public static final Image HOLE_LOWER_PART_IMAGE;
    /** A Hole of the upper part of the image. */
    public static final Image HOLE_UPPER_PART_IMAGE;
    static {
        try {
            MOLE_IMAGE = new Image(new FileInputStream("src/main/resources/mole.png"));
            BOMB_IMAGE = new Image(new FileInputStream("src/main/resources/bomb.png"));
            HIT_BOMB_IMAGE = new Image(new FileInputStream("src/main/resources/boom.png"));
            HIT_MOLE_IMAGE = new Image(new FileInputStream("src/main/resources/hit_mole.png"));
            HOLE_LOWER_PART_IMAGE = new Image(new FileInputStream("src/main/resources/hole_lower_part.png"));
            HOLE_UPPER_PART_IMAGE = new Image(new FileInputStream("src/main/resources/hole_upper_part.png"));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Unable to load images", e);
        }
    }

    /** Constructor throws exeption, utility class only. */
    private ImagesCache() {
        throw new IllegalStateException("Utility class");
    }
}
