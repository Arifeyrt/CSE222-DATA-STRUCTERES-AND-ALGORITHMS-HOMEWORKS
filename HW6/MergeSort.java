public class MergeSort extends SortAlgorithm {
	
	public MergeSort(int input_array[]) {
		super(input_array);
	}

	/*
     * Merge the subarrays arr[l..m] and arr[m+1..r]
     * The method should update the array arr
     * The method should have O(n) time complexity
     * The method should have O(n) space complexity
     * @param left: the left index of the subarray
     * @param middle: the middle index of the subarray
     * @param right: the right index of the subarray
     * @return void
     */
	private void merge(int left, int middle, int right) {
        // fill this method
        int temp1 = middle - left + 1;// size of the left array
        int temp2 = right - middle;// size of the right array

        int L[] = new int[temp1];// left array
        int R[] = new int[temp2];// right array

        for (int i = 0; i < temp1; i++) {
            L[i] = arr[left + i];// copy the elements to the left array
        }
        for (int j = 0; j < temp2; j++) {
            R[j] = arr[middle + 1 + j];// copy the elements to the right array
        }

        int i = 0, j = 0;
        int k = left;

        // Merge the left and right arrays
        while (i < temp1 && j < temp2) {
            comparison_counter++;
        // Compare the elements of the left and right arrays
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                // If the element of the right array is smaller than the element of the left array
                arr[k] = R[j];
                j++;
            }
            k++;
        }
// Copy the remaining elements of the left array
        while (i < temp1) {
            arr[k] = L[i];
            i++;
            k++;
        }
// Copy the remaining elements of the right array
        while (j < temp2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /*
     * Sort the array using Merge Sort algorithm
     * The method should update the array arr
     * The method should sort the array in ascending order
     * The method should have O(nlogn) time complexity
     * The method should have O(n) space complexity
     * @return void
     */
    private void sort(int l,int r){

        // fill this method
        // Check if the left index is less than the right index
        if (l < r) {
        // Find the middle index
            int m = (l + r) / 2;
            sort(l, m);// Sort the left subarray
            sort(m + 1, r);// Sort the right subarray
            merge(l, m, r);// Merge the sorted subarrays
        }
    }
    
    @Override
    /*
     * Sort the array using Merge Sort algorithm
     * The method should update the array arr and the counters comparison_counter and swap_counter
     * The method should sort the array in ascending order
     * The method should have O(nlogn) time complexity
     * The method should have O(n) space complexity
     */
    public void sort() {
    	sort(0, arr.length - 1 );
    }
    
    @Override
    public void print() {
    	System.out.print("Merge Sort\t=>\t");
    	super.print();
    }
}
