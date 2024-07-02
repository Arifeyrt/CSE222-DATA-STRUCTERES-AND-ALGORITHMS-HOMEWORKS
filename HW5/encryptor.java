import java.util.Map;

/*
 * The encryptor class is responsible for encrypting the plaintext using the Vigenere cipher.
 */
public class encryptor {
	private Map<Character, Map<Character, Character>> map;
	private String key;
	private String keystream = "";
	private String plain_text;
	private String cipher_text = "";

	/*
	 * Constructor Initializes the encryptor with the map,key, and plaintext
	 */
	public encryptor(Map<Character, Map<Character, Character>> _map, String _key, String text) {
		map = _map;
		key = _key;
		plain_text = text;
	}

	/*
	 * Encrypts the plaintext using the Vigenere cipher.
	 */
	public void encrypt() {
		// do not edit this method
		generate_keystream();
		generate_cipher_text();
	}

	/*
	 * Generates the keystream based on the key and plaintext length.
	 */
	private void generate_keystream() {
		StringBuilder sb = new StringBuilder();// Create a string builder
		for (int i = 0; i < plain_text.length(); i++) {// Iterate through the plain text
			char key_char = key.charAt(i % key.length());// Get the key character
			sb.append(key_char);// Append the key character to the string builder
		}
		keystream = sb.toString();// Convert the string builder to a string
	}

	/*
	 * Generates the ciphertext based on the keystream and plaintext.
	 */
	private void generate_cipher_text() {
		StringBuilder sb = new StringBuilder();// Create a string builder
		for (int i = 0; i < plain_text.length(); i++) {// Iterate through the plain text
			char plain_char = plain_text.charAt(i);// Get the plain character
			char key_char = keystream.charAt(i);// Get the key character
			char cipher_char = map.get(plain_char).get(key_char);// Get the cipher character from the map
			sb.append(cipher_char);// Append the cipher character to the string builder
		}
		cipher_text = sb.toString();// Convert the string builder to a string
	}

	/*
	 * Returns the keystream.
	 */
	public String get_keystream() {
		return keystream;

	}

	/*
	 * Returns the ciphertext.
	 */
	public String get_cipher_text() {
		return cipher_text;

	}
}
