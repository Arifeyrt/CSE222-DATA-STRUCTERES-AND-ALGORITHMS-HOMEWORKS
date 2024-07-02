import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;

/*
 * The alphabet class is responsible for generating the Vigenere cipher map.
 */
public class alphabet {
	private Set<Character> english_alphabet = new LinkedHashSet<Character>();
	private Map<Character, Map<Character, Character>> map = new HashMap<Character,  Map<Character, Character>>();
	
	/*
	 * Constructor Initializes the alphabet class by filling the English alphabet and the Vigenere cipher map.
	 */
	public alphabet() {
		// do not edit this method
		fill_english_alphabet();
		fill_map();
	}
	
	/*
	 * Fills the English alphabet set with the uppercase English alphabet. 
	 */
	private void fill_english_alphabet() {
		// do not edit this method
		for(char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
		    english_alphabet.add(c);
		}
	}
	
	/*
	 * Fills the Vigenere cipher map using the English alphabet set.
	 */
	private void fill_map() {
		// You must use the "english_alphabet" variable in this method, to fill the "map" variable.
		// You can define 1 or 2 iterators to iterate through the set items.
		Iterator<Character> iter_row = english_alphabet.iterator();//iterator for the row
		// Fill the map variable with the Vigenere cipher map using the english_alphabet set and the iterators
        while (iter_row.hasNext()) {
            char row_c = iter_row.next();//get the next character from the iterator
            Iterator<Character> temp_iter = english_alphabet.iterator();//iterator for the column
            Map<Character, Character> inner_map = new HashMap<>();//inner map
            // Fill the inner map with the Vigenere cipher map
            while (temp_iter.hasNext()) {//iterate through the column
                char col_c = temp_iter.next();//get the next character from the iterator
                inner_map.put(col_c, row_c);//put the row character to the inner map
                row_c = (char) ((row_c + 1 - 'A') % 26 + 'A');//increment the row character
            }
            map.put(row_c, inner_map);//put the inner map to the map
        }
    }

	/*
	 * Prints the Vigenere cipher map.
	 */
	public void print_map() {
		// do not edit this method
		System.out.println("*** Viegenere Cipher ***\n\n");//print the title
		System.out.println("    " + english_alphabet);
		System.out.print("    ------------------------------------------------------------------------------");
		for(Character k: map.keySet()) {
			System.out.print("\n" + k + " | ");
			System.out.print(map.get(k).values());
		}
		System.out.println("\n");
		
	}
/*
 * This method returns the Vigenere cipher map.
 */
	public Map get_map() {
		 return map;
	}
}