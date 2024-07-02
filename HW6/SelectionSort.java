public class SelectionSort extends SortAlgorithm {

	public SelectionSort(int input_array[]) {
		super(input_array);
	}

    @Override
    /*
     * Sort the array using Selection Sort algorithm
     * The method should update the array arr and the counters comparison_counter and swap_counter
     * The method should sort the array in ascending order
     * The method should have O(n^2) time complexity
     * The method should have O(1) space complexity
     * @return void
     */
    public void sort() {
        // fill this method
        // Traverse through all array elements
        int array_length = arr.length;
        // One by one move the boundary of the unsorted subarray
        for (int i = 0; i < array_length-1; i++) {
            int  minIndex = i;// Find the minimum element in unsorted array
            // Traverse through all array elements
            for (int j = i+1; j < array_length; j++) {
                comparison_counter++;
            // If the current element is smaller than the minimum element
                if (arr[j] < arr[ minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            swap(i,  minIndex);
        }
    }

    @Override
    public void print() {
    	System.out.print("Selection Sort\t=>\t");
    	super.print();
    }
}
