package game.engine.minigame.whacamoleminigame;

import java.util.ArrayList;
import java.util.List;
import api.Point2D;
import game.controlling.NullInput;
import game.engine.gameobject.SimplePhysics;
import game.engine.gameobject.whacamoleobjects.HoleLowerPart;
import game.engine.gameobject.whacamoleobjects.HoleLowerPartAspectModel;
import game.engine.gameobject.whacamoleobjects.HoleUpperPart;
import game.engine.gameobject.whacamoleobjects.HoleUpperPartAspectModel;
import game.engine.gameobject.whacamoleobjects.WamObject;

/**
 * Class that manages the creation and location of dens from 
 * which moles and bombs come and go in the game Whac-a-Mole.
 */
public class HolesGenerator {
    private static final double RATIO = 16.0 / 9.0;
    private static final int HALF_SIDE_LOWER_PART = 150;
    private static final int HALF_WIDTH_UPPER_PART = 150;
    private static final int HALF_HEIGHT_UPPER_PART = 20;
    private final int fieldHeight;
    private final int fieldWidth;

    /**
     * Constructor that sets the value of the height 
     * of the playing field and calculate its width.
     * 
     * @param fieldHeight the height of the playing fields
     */
    public HolesGenerator(final int fieldHeight) {
        this.fieldHeight = fieldHeight;
        this.fieldWidth = (int) (fieldHeight * RATIO);
    }

    /**
     * Taking the dimensions (in coor) of the playing field and 
     * the number of holes in the game, places the holes in a 
     * "table" where the number of rows of columns is the same.
     * 
     * @param numHoles the number of dens to creates
     * @return a list containing the holes created
     */
    public List<WamObject> generate(final int numHoles) {
        final List<WamObject> holes = new ArrayList<>();
        final int dx = (int) (this.fieldWidth / (Math.sqrt(numHoles) * 2));
        final int dy = (int) (this.fieldHeight / (Math.sqrt(numHoles) * 2));
        final int holesPerRow = (int) Math.sqrt(numHoles);
        int holesCounter = 1;
        /* I fill the list so that in the end it has the first half */
        /* of HoleUpperPart and the second half of HoleLowerPart    */
        for (int y = dy; holesCounter <= numHoles; y += dy * 2) {
            int holesInThisRow = 0;
            for (int x = dx;  holesInThisRow < holesPerRow; x += dx * 2) {
                holes.add(new HoleUpperPart(
                    new Point2D(x - HALF_WIDTH_UPPER_PART, y - HALF_HEIGHT_UPPER_PART * 2),
                    0, 
                    new LevelNull(), 
                    holesCounter,
                    new SimplePhysics(), 
                    new HoleLowerPartAspectModel(),
                    new NullInput()));
                holes.add(holesCounter - 1, new HoleLowerPart(
                    new Point2D(x - HALF_SIDE_LOWER_PART, y),
                    0, 
                    new LevelNull(), 
                    holesCounter++, 
                    new SimplePhysics(), 
                    new HoleUpperPartAspectModel(), 
                    new NullInput()));
                holesInThisRow += 1;
            }
        }
        return holes;
    }
}
