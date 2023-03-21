package encryptiontest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;

import api.AesEncrypterImpl;
import api.Encrypter;

/**
 * Class to check the {@link AesEncryptionImpl} functionality.
 */
class AesEncryptionImplTest {
    private static final String PASSWORD = "myPassword123456";
    private static final String TEST_STRING = "hello world test";

    /**
     * To check that we get new bytes and that they are different.
     */
    @Test
    void isEncoding() {
        final Encrypter e = new AesEncrypterImpl(PASSWORD);
        final byte[] encoded = e.encrypt(TEST_STRING.getBytes(StandardCharsets.UTF_8));
        assertNotEquals(encoded, null);
        assertNotEquals(TEST_STRING, new String(encoded, StandardCharsets.UTF_8));

    }

    /**
     * To check the decoding function.
     */
    @Test
    void decoding() {
        final Encrypter e = new AesEncrypterImpl(PASSWORD);
        final byte[] encoded = e.encrypt(TEST_STRING.getBytes(StandardCharsets.UTF_8));
        final Encrypter e1 = new AesEncrypterImpl(PASSWORD);
        assertEquals(TEST_STRING, new String(e1.decrypt(encoded), StandardCharsets.UTF_8));
    }

}
