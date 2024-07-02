public class QuickSort extends SortAlgorithm {

	public QuickSort(int input_array[]) {
		super(input_array);
	}
	
    /*
     * Partition the array into two subarrays such that all elements less than the pivot are on the left side of the pivot
     * and all elements greater than the pivot are on the right side of the pivot
     * The method should update the array arr
     * The method should return the index of the pivot
     * The method should have O(n) time complexity
     * The method should have O(1) space complexity
     */
    private int partition(int low, int high) {
        // fill this method
        // index of smaller element
        int k = (low - 1);
        // pivot element
        int pivot = arr[high];
        // Traverse through all array elements
        for (int i = low; i < high; i++) {
            comparison_counter++;
        // If the current element is smaller than the pivot
            if (arr[i] < pivot) {
                k++;
                swap(k, i);
            }
        
        }
        // Swap the elements
        swap(k + 1, high);
        return k + 1;
    }

    /*
     * Sort the array using Quick Sort algorithm
     * The method should update the array arr and the counters comparison_counter and swap_counter
     * The method should sort the array in ascending order
     * The method should have O(n^2) time complexity
     * The method should have O(logn) space complexity
     * @return void
     */
    private void sort(int low, int high) {
        // fill this method
        // Check if the low index is less than the high index
        if (low < high) {
        // Find the partition index
            int pi = partition(low, high);
            sort(low, pi - 1);// Sort the left subarray
            sort(pi + 1, high);// Sort the right subarray
        }
    }

    @Override
    public void sort() {
    	sort(0, arr.length - 1);
    }

    @Override
    public void print() {
    	System.out.print("Quick Sort\t=>\t");
    	super.print();
    }
}
