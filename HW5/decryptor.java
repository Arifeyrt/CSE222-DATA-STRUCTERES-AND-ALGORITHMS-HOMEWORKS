import java.util.Map;
import java.util.Iterator;

/*
 * The decryptor class is responsible for decrypting the ciphertext using the Vigenere cipher.
 */
public class decryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text = "";
	private String cipher_text;

	/*
	 * Constructor Initializes the decryptor with the map,key, and ciphertext
	 */
	public decryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		cipher_text = text;
	}

	/*
	 * Decrypts the ciphertext using the Vigenere cipher.
	 */
	public void decrypt() {
		// do not edit this method
		generate_keystream();
		generate_plain_text();
	}

	/*
	 * Generates the keystream based on the key and ciphertext length.
	 */
	private void generate_keystream() {
		StringBuilder sb = new StringBuilder();// Create a string builder
		for (int i = 0; i < cipher_text.length(); i++) { // Iterate through the cipher text
			char key_char = key.charAt(i % key.length());// Get the key character
			sb.append(key_char);// Append the key character to the string builder
		}
		keystream = sb.toString();// Convert the string builder to a string
	}

	/*
	 * Generates the plaintext based on the keystream and ciphertext.
	 */
	private void generate_plain_text() {
		StringBuilder sb = new StringBuilder(); // Create a string builder
		Iterator<Character> keystreamIterator = keystream.chars().mapToObj(c -> (char) c).iterator(); // Get an iterator
																										// for the
																										// keystream
		for (int i = 0; i < cipher_text.length(); i++) { // Iterate through the cipher text
			char cipher_char = cipher_text.charAt(i); // Get the cipher character
			char key_char = keystreamIterator.next(); // Get the next character from the keystream using the iterator
			Iterator<Character> keySetIterator = map.get(key_char).keySet().iterator(); // Get an iterator for the key
																						// set of the map entry
			while (keySetIterator.hasNext()) { // Iterate through the key set
				char plain_char = keySetIterator.next(); // Get the next character from the key set
				if (map.get(key_char).get(plain_char) == cipher_char) { // Check if the mapped character equals the
																		// cipher character
					sb.append(plain_char); // Append the plain character to the string builder
					break; // Exit the loop since we found the corresponding plain character
				}
			}
		}
		plain_text = sb.toString(); // Convert the string builder to a string
		// You must use map.get(x).keySet() with an iterator in this method
	}

	/*
	 * Returns the keystream.
	 */
	public String get_keystream() {
		return keystream;
	}

	/*
	 * Returns the plaintext.
	 */
	public String get_plain_text() {
		return plain_text;
	}
}
