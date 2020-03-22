/**
 * The interface Hashing.
 * @author Po Yat Ching David UID:3035372098
 */
public interface Hashing {
    /**
     * Hash the given plaintext password to hashed password.
     *
     * @param password the plaintext password
     * @return the hashed password
     */
    String hash(String password);
}
