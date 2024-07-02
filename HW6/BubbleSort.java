public class BubbleSort extends SortAlgorithm {

	 /*
      * Constructor to initialize the array
      * @param input_array: the array to be sorted
      */
    public BubbleSort(int input_array[]) {
        super(input_array);
    }
    
    @Override
    /*
     * The method should update the array arr and the counters comparison_counter and swap_counter
     * The method should sort the array in ascending order
     * The method should have O(n^2) time complexity
     * The method should have O(1) space complexity
     */
    public void sort() {
        boolean temp;// flag to check if the elements are swapped
        int length_array = arr.length;// length of the array
        // Traverse through all array elements
        for (int i = 0; i < length_array - 1; i++) {
            temp = false;// flag to check if the elements are swapped
            // Last i elements are already in place
            for (int j = 0; j < length_array - i - 1; j++) {
                comparison_counter++;
            // Traverse the array from 0 to length_array - i - 1
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);// Swap if the element found is greater than the next element
                   temp = true;
                }
            }
            // If no two elements were swapped in the inner loop, then break the loop as the array is sorted 
            if (!temp) {
                break;
            }
        }
    }
    
    @Override
    /*
     * Print the comparison counter, swap counter and the sorted array
     */
    public void print() {
    	System.out.print("Bubble Sort\t=>\t");
    	super.print();
    }
}
