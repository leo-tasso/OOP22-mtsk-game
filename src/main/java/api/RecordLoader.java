package api;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Interface used to implement save data features.
 */
public interface RecordLoader {

    /**
     * Method to retrieve data from previous matches.
     * 
     * @return a map that contains all previous games data
     */
    Map<Timestamp, Long> getRecords();

    /**
     * Method to save data from the last match played.
     * 
     * @param timestamp the time at which the current game ended
     * @param score the score the player got in the current game
     */
    void setRecord(Timestamp timestamp, Long score);

}
