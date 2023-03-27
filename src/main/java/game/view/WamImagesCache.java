package game.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;

/**
 * Class that acts as an external memory that contains 
 * the images of the Whac-a-Mole game, being that if 
 * they had to be re-instantiated every time inside 
 * JavaFxDrawings the performance would drop drastically.
 */
public class WamImagesCache {
    private static final int CIRCUMSCRIBED_SQUARE_SIDE = 300;

    private final Image moleImage;
    private final Image bombImage;
    private final Image hitMoleImage;
    private final Image hitBombImage;
    private final Image holeLowerPartImage;
    private final Image holeUpperPartImage;

    /**
     * Constructor that creates Image objects once and for all.
     * 
     * @throws FileNotFoundException if one of the files is not found
     */
    public WamImagesCache(final int coefficient, final double dimention) throws FileNotFoundException {
        this.moleImage = new Image(new FileInputStream("src/main/resources/mole.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
        this.bombImage = new Image(new FileInputStream("src/main/resources/bomb.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
        this.hitMoleImage = new Image(new FileInputStream("src/main/resources/hit_mole.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
        this.hitBombImage = new Image(new FileInputStream("src/main/resources/boom.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
        this.holeLowerPartImage = new Image(new FileInputStream("src/main/resources/hole_lower_part.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
        this.holeUpperPartImage = new Image(new FileInputStream("src/main/resources/hole_upper_part.png"),
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                CIRCUMSCRIBED_SQUARE_SIDE * dimention / coefficient,
                                true,
                                false);
    }

    /**
     * Getter method for the side of 
     * the square surrounding each image.
     * 
     * @return the constant containing that value
     */
    public static int getCircumscribedSquareSide() {
        return CIRCUMSCRIBED_SQUARE_SIDE;
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
