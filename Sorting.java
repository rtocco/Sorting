
public class Sorting {
	public static void main(String[] args) {
		int[] test1 = { 3, 2, 1, 7, 6, 4, 5, 6, 9, 8 };
		int[] result = new int[10];
		mergeSort(test1, result, 0, test1.length - 1);
		print(result);

		int[] test2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		result = new int[9];
		mergeSort(test2, result, 0, test2.length - 1);
		print(result);

		int[] test3 = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		result = new int[9];
		mergeSort(test3, result, 0, test3.length - 1);
		print(result);
	}

	static void bubbleSort(int[] numbers) {
		// Check for if the list is sorted yet.
		boolean done = false;

		// We don't have to check the last element after the first
		// pass, second-to-last after the second pass, etc.
		int iterations = 0;

		while(!done) {
			done = true;

			for(int i = 0; i < numbers.length - 1 - iterations; i++) {
				if(numbers[i] > numbers[i + 1]) {
					int temp = numbers[i];
					numbers[i] = numbers[i + 1];
					numbers[i + 1] = temp;
					done = false;
				}
			}

			iterations++;
		}
	}

	static void selectionSort(int[] numbers) {

		for(int i = 0; i < numbers.length; i++) {

			// We want to find the smallest remaining value and remember its index.
			int smallest = Integer.MAX_VALUE;
			int index = i;

			// Go through the remaining numbers and find the smallest.
			for(int j = i; j < numbers.length; j++) {
				if(numbers[j] < smallest) {
					smallest = numbers[j];
					index = j;
				}
			}

			// If the smallest number is not in the correct place, swap.
			if(i != index) {
				int temp = numbers[i];
				numbers[i] = smallest;
				numbers[index] = temp;
			}
		}
	}

	static void insertionSort(int[] numbers) {

		for(int i = 1; i < numbers.length; i++) {

			// The number to be inserted.
			int current = numbers[i];
			
			int j = i - 1;
			while((j >= 0) && (current < numbers[j])) {
				numbers[j + 1] = numbers[j];
				j--;
			}

			numbers[j + 1] = current;
		}
	}

	// Recursive quick sort.
	static void quickSort(int[] numbers, int low, int high) {
		// End recursion
		if(low >= high) {
			return;
		}
		
		// We choose the rightmost number as the pivot.
		int pivot = numbers[high];
		int pivotPosition = high;

		// Move all numbers that are greater than the pivot onto the pivot's right side.
		for(int i = high - 1; i >= low; i--) {
			if(numbers[i] > pivot) {
				// Swap the number to be moved with the number one position to the left of the pivot.
				int temp = numbers[pivotPosition - 1];
				numbers[pivotPosition - 1] = numbers[i];
				numbers[i] = temp;

				// Now swap that number with the pivot.
				numbers[pivotPosition] = numbers[pivotPosition - 1];
				numbers[pivotPosition - 1] = pivot;

				pivotPosition--;
			}
		}

		// Continue recursion on the low and high sides of the pivot, respectively.
		quickSort(numbers, low, pivotPosition - 1);
		quickSort(numbers, pivotPosition + 1, high);
	}

	// Recursive merge sort.
	static void mergeSort(int[] numbers, int[] newNumbers, int low, int high) {
		int middle = (low + high) / 2;

		// Divide given list in half recursively.
		if(high > low) {
			mergeSort(numbers, newNumbers, low, middle);
			mergeSort(numbers, newNumbers, middle + 1, high);
		} else {
			// The list is of size one.
			return;
		}

		// Before returning, sort this piece of the list.
		int list1 = low;
		int list2 = middle + 1;
		int i = low;

		// Each of the bottom and top halves of the list will be
		// sorted, so we go through each, comparing values.
		while(list1 <= middle && list2 <= high) {
			if(numbers[list1] < numbers[list2]) {
				newNumbers[i] = numbers[list1];
				list1++;
			} else {
				newNumbers[i] = numbers[list2];
				list2++;
			}

			i++;
		}

		// The above while loop finished when it reached the end of
		// just one of the lists. We now go through the rest of the
		// other list, adding its values to newNumbers.
		while(list1 <= middle) {
			newNumbers[i] = numbers[list1];
			list1++;
			i++;
		}
		while(list2 <= high) {
			newNumbers[i] = numbers[list2];
			list2++;
			i++;
		}

		// Now we go through and set numbers equal to
		// newNumbers from positon low to positon high.
		for(i = low; i <= high; i++) {
			numbers[i] = newNumbers[i];
		}
	}

	static void print(int[] numbers) {
		for(int i = 0; i < numbers.length; i++) {
			System.out.print(numbers[i] + " ");
		}

		System.out.println();
	}
}