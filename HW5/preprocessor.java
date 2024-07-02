/*
 * This class preprocesses the input string by capitalizing all the letters and removing all non-alphabetic characters.
 */
public class preprocessor {
	private String initial_string;
	private String preprocessed_string;

	/*
	 * Constructor for the preprocessor class.
	 */
	public preprocessor(String str) {
		initial_string = str;
		preprocess();

	}

	/*
	 * This method preprocesses the input string by capitalizing all the letters and
	 * removing all non-alphabetic characters.
	 */
	public void preprocess() {
		// do not edit this method
		capitalize();
		clean();
	}

	/*
	 * This method capitalizes all the letters in the input string.
	 */
	private void capitalize() {
        initial_string =initial_string.replaceAll("ı", "");
		preprocessed_string = initial_string.replaceAll("i", "I").toUpperCase();
	}

	/*
	 * * This method removes all non-alphabetic characters from the input string.
	 */
	private void clean() {
		preprocessed_string = preprocessed_string.replaceAll("[^a-zA-Z]", "");
	}
	/*
	 * This method returns the preprocessed string.
	 */
	public String get_preprocessed_string() {
		return preprocessed_string;
	}
}