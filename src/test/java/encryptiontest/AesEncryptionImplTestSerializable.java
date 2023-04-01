package encryptiontest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import org.junit.jupiter.api.Test;

import api.AesEncrypterImpl;
import api.Encrypter;

/**
 * Encrytion test applied to serialized object.
 */
class AesEncryptionImplTestSerializable {
    private static final String PASSWORD = "myPassword123456";
    private static final String FILE_NAME = "test.log";
    private final Map<String, String> m = Map.of(
            "a", "b",
            "c", "d");

    /**
     * To check that we get new bytes and that they are different.
     */
    @Test
    void isEncoding() {
        // create encrypter
        Encrypter e = new AesEncrypterImpl(PASSWORD);
        // create byteStream
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos);
                FileOutputStream fos = new FileOutputStream(FILE_NAME);) {
            // serialize object
            oos.writeObject(m);
            // encode bytes
            final byte[] encoded = e.encrypt(bos.toByteArray());
            // save to file

            fos.write(encoded);
            fos.close();
        } catch (IOException e1) {
            throw new IllegalStateException("Error in serialize", e1);
        }
        try {
            // retrive file
            final FileInputStream fis = new FileInputStream(FILE_NAME);
            e = new AesEncrypterImpl(PASSWORD);
            // decrypt to bytes
            final ByteArrayInputStream bis = new ByteArrayInputStream(e.decrypt(fis.readAllBytes()));
            // to object
            final ObjectInputStream ois = new ObjectInputStream(bis);
            final Object obj = ois.readObject();
            fis.close();
            ois.close();
            assertEquals(obj, m);
        } catch (IOException | ClassNotFoundException e1) {
            throw new IllegalStateException("Error in serialize", e1);
        }
    }
}
