package api;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Implementation of Encrypter usig AES algorytm.
 */
public class AesEncrypterImpl implements Encrypter {
    private final Key key;

    /**
     * Constructor of the class.
     * 
     * @param password the key to use to encrypt and decrypt.
     */
    public AesEncrypterImpl(final String password) {
        this.key = new SecretKeySpec(password.getBytes(StandardCharsets.UTF_8), "AES");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] encrypt(final byte[] data) {
        try {
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException
                | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Unable to Encrypt", e);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] decrypt(final byte[] data) {
        try {
            final Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException
                | NoSuchAlgorithmException e) {
            throw new IllegalStateException("Unable to Encrypt", e);
        }
    }

}
