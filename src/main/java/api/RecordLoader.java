package api;

import java.sql.Timestamp;
import java.util.Map;

public interface RecordLoader {

    Map<Timestamp, Long> getRecords();

    void setRecord(Timestamp timestamp, Long score);
    
}
