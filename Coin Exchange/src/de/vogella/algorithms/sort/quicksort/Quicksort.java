package de.vogella.algorithms.sort.quicksort;


/**
 * 
 * Slightly modified typical q-sort
 * 
 * @author Lars Vogel
 * @author Alpen Ditrix
 * 
 */
public class Quicksort {
	private static int[] numbers;
	private static int number;
	private static boolean direction;

	public static void sort(int[] values, boolean ascending) {
		// Check for empty or null array
		if (values == null || values.length == 0) {
			return;
		}
		numbers = values;
		number = values.length;
		direction = ascending;
		quicksort(0, number - 1);
	}

	private static void quicksort(int low, int high) {
		int i = low, j = high;
		int pivot = (int) numbers[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while ((int) numbers[i] < pivot) {
					i++;
				}
				while ((int) numbers[j] > pivot) {
					j--;
				}
			} else {
				while ((int) numbers[i] > pivot) {
					i++;
				}
				while ((int) numbers[j] < pivot) {
					j--;
				}

			}
			if (i <= j) {
				exchange(i, j);
				i++;
				j--;
			}
		}
		if (low < j)
			quicksort(low, j);
		if (i < high)
			quicksort(i, high);
	}

	private static void exchange(int i, int j) {
		int temp = (int) numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}