package api;

import java.io.File;
import java.sql.Timestamp;
import java.util.Map;

public class RecordLoaderImpl implements RecordLoader {

    private static final AesEncrypterImpl ENCRYPTER = new AesEncrypterImpl("myPassword123456");
    private static final String FILENAME = "Scores.txt";
    private final File file;
    
    public RecordLoaderImpl() {
        this.file = new File(System.getProperty("user.home")
                + System.getProperty("file.separator")
                + FILENAME);
    }

    @Override
    public Map<Timestamp, Long> getRecords() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecords'");
    }

    @Override
    public void setRecord(Timestamp timestamp, Long score) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRecord'");
    }
}
