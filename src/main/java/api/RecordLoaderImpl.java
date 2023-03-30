package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that saves matches results in a file and retrieves them if needed.
 */
public class RecordLoaderImpl implements RecordLoader {

    private static final AesEncrypterImpl ENCRYPTER = new AesEncrypterImpl("myPassword123456");
    private static final String FILENAME = "Scores.txt";
    private final File file;

    /**
     * Constructor that sets the filepath for save data.
     */
    public RecordLoaderImpl() {
        this.file = new File(System.getProperty("user.home")
                + System.getProperty("file.separator")
                + FILENAME);
        createNewFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Timestamp, Long> getRecords() {
        String curLine;
        final List<byte[]> data = new ArrayList<>();
 
        try (BufferedReader bf = Files.newBufferedReader(file.toPath())) {
            while ((curLine = bf.readLine()) != null) {
                data.add(curLine.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final Map<Timestamp, Long> result = new HashMap<>();
        data.stream()
            //.map(x -> ENCRYPTER.decrypt(x))
            .map(x -> new String(x))
            .map(x -> x.split(","))
            .forEach(x -> result.put(Timestamp.valueOf(x[0]), Long.parseLong(x[1])));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRecord(final Timestamp timestamp, final Long score) {
        final String line = timestamp.toString() + "," + score + "\n";
        try {
            Files.write(file.toPath(),
                    line.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that creates a new file if it doesn't exist already.
     */
    private void createNewFile() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
