package api;

/**
 * Class to simply encrypt and decrypt bytes.
 */
public interface Encrypter {
    /**
     * Method to encrypt data.
     * 
     * @param data to be encrypted.
     * @return the encrypted data.
     */
    byte[] encrypt(byte[] data);

    /**
     * Method to decrypt data.
     * 
     * @param data the encrypted data.
     * @return the decrypted data.
     */
    byte[] decrypt(byte[] data);

}
