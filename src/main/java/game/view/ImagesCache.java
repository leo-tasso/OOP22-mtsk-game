package game.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.scene.image.Image;

/**
 * Class that acts as an external memory that contains
 * the images used in the minigames, being that if 
 * they had to be re-instantiated every time inside
 * JavaFxDrawings the performance would drop drastically.
 */
@SuppressFBWarnings 
public class ImagesCache {
    /** Width of the rectangle circumscribing 
     * each of the two parts of the hole. */
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

    private final Image moleImage;
    private final Image bombImage;
    private final Image hitMoleImage;
    private final Image hitBombImage;
    private final Image holeLowerPartImage;
    private final Image holeUpperPartImage;

    /**
     * Constructor that creates Image objects once and for all.
     */
    public ImagesCache() {
        try {
            this.moleImage          = new Image(new FileInputStream("src/main/resources/mole.png"));
            this.bombImage          = new Image(new FileInputStream("src/main/resources/bomb.png"));
            this.hitBombImage       = new Image(new FileInputStream("src/main/resources/boom.png"));
            this.hitMoleImage       = new Image(new FileInputStream("src/main/resources/hit_mole.png"));
            this.holeLowerPartImage = new Image(new FileInputStream("src/main/resources/hole_lower_part.png"));
            this.holeUpperPartImage = new Image(new FileInputStream("src/main/resources/hole_upper_part.png"));
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Unable to load images", e);
        }
    }

    /**
     * Getter method for the image of the mole.
     * 
     * @return the image already loaded
     */
    public Image getMoleImage() {
        return this.moleImage;
    }

    /**
     * Getter method for the image of the bomb.
     * 
     * @return the image already loaded
     */
    public Image getBombImage() {
        return this.bombImage;
    }

    /**
     * Getter method for the image of the
     * mole after it has been squashed.
     * 
     * @return the image already loaded
     */
    public Image getHitMoleImage() {
        return this.hitMoleImage;
    }

    /**
     * Getter method for the image of
     * the bomb after it has been hit.
     * 
     * @return the image already loaded
     */
    public Image getHitBombImage() {
        return this.hitBombImage;
    }

    /**
     * Getter method for the image of the "tube"
     * through which moles and bombs pass.
     * 
     * @return the image already loaded
     */
    public Image getHoleLowerPartImage() {
        return this.holeLowerPartImage;
    }

    /**
     * Getter method for the image of the semicircle
     * placed above the "tube" (part of the hole entity).
     * 
     * @return the image already loaded
     */
    public Image getHoleUpperPartImage() {
        return this.holeUpperPartImage;
    }
}
