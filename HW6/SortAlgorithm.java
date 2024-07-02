public class SortAlgorithm {
	static int arr[];
	static int comparison_counter;
	static int swap_counter;

	public SortAlgorithm(int input_array[]) {
		arr = input_array.clone(); // clone the input array
		comparison_counter = 0;// initialize the comparison counter
		swap_counter = 0;// initialize the swap counter
	}
	
	public void sort() {
		// this method should be empty.

	}
	
	/*
	 * Swap the elements at index_1 and index_2 in the array arr
	 * The method should update the array arr and the counter swap_counter
	 * @param index_1: the index of the first element
	 * @param index_2: the index of the second element
	 * @return void
	 */
	protected static void swap(int index_1, int index_2) {
		// Swap the elements at index_1 and index_2
		int temp = arr[index_1];
		arr[index_1] = arr[index_2];// Swap the elements
		arr[index_2] = temp;// Swap the elements
		swap_counter += 1;// Increment the swap counter
	}
	
	public void print() {
		System.out.print("Comparison Counter: " + comparison_counter);
		System.out.print("   \t Swap Counter: " + swap_counter);
		System.out.print("   \t Sorted Array: ");
		for(int e: arr)
			System.out.print(e + " ");
		System.out.println();		
	}
	
	public void sort_and_print() {
		sort();
		print();
	}
}