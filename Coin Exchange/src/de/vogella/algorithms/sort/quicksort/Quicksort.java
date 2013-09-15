package de.vogella.algorithms.sort.quicksort;


/**
 * 
 * Slightly modified typical q-sort. Sorting is implemented for int, long,
 * float, double and any object which implements {@link Comparable}
 * 
 * @author Lars Vogel
 * @author Alpen Ditrix
 * 
 */
@SuppressWarnings({ "javadoc", "rawtypes" })
public class Quicksort {

	@SuppressWarnings("unchecked")
	private static void quicksortComp(Comparable<Comparable>[] objects,
			int low, int high, boolean direction) {
		int i = low, j = high;
		Comparable<Comparable> pivot = objects[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while (objects[i].compareTo(pivot) == -1) {
					i++;
				}
				while (objects[j].compareTo(pivot) == 1) {
					j--;
				}
			} else {
				while (objects[i].compareTo(pivot) == 1) {
					i++;
				}
				while (objects[j].compareTo(pivot) == -1) {
					j--;
				}
			}
			if (i <= j) {
				Comparable temp = objects[i];
				objects[i] = objects[j];
				objects[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksortComp(objects, low, j, direction);
		if (i < high)
			quicksortComp(objects, i, high, direction);
	}

	private static void quicksortDouble(double[] doubles, int low, int high,
			boolean direction) {
		int i = low, j = high;
		double pivot = doubles[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while (doubles[i] < pivot) {
					i++;
				}
				while (doubles[j] > pivot) {
					j--;
				}
			} else {
				while (doubles[i] > pivot) {
					i++;
				}
				while (doubles[j] < pivot) {
					j--;
				}

			}
			if (i <= j) {
				double temp = doubles[i];
				doubles[i] = doubles[j];
				doubles[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksortDouble(doubles, low, j, direction);
		if (i < high)
			quicksortDouble(doubles, i, high, direction);
	}

	private static void quicksortFloat(float[] floats, int low, int high,
			boolean direction) {
		int i = low, j = high;
		float pivot = floats[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while (floats[i] < pivot) {
					i++;
				}
				while (floats[j] > pivot) {
					j--;
				}
			} else {
				while (floats[i] > pivot) {
					i++;
				}
				while (floats[j] < pivot) {
					j--;
				}

			}
			if (i <= j) {
				float temp = floats[i];
				floats[i] = floats[j];
				floats[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksortFloat(floats, low, j, direction);
		if (i < high)
			quicksortFloat(floats, i, high, direction);
	}

	private static void quicksortInt(int[] ints, int low, int high,
			boolean direction) {
		int i = low, j = high;
		int pivot = ints[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while (ints[i] < pivot) {
					i++;
				}
				while (ints[j] > pivot) {
					j--;
				}
			} else {
				while (ints[i] > pivot) {
					i++;
				}
				while (ints[j] < pivot) {
					j--;
				}

			}
			if (i <= j) {
				int temp = ints[i];
				ints[i] = ints[j];
				ints[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksortInt(ints, low, j, direction);
		if (i < high)
			quicksortInt(ints, i, high, direction);
	}

	private static void quicksortLong(long[] longs, int low, int high,
			boolean direction) {
		int i = low, j = high;
		long pivot = longs[low + (high - low) / 2];

		while (i <= j) {
			if (direction) {
				while (longs[i] < pivot) {
					i++;
				}
				while (longs[j] > pivot) {
					j--;
				}
			} else {
				while (longs[i] > pivot) {
					i++;
				}
				while (longs[j] < pivot) {
					j--;
				}

			}
			if (i <= j) {
				long temp = longs[i];
				longs[i] = longs[j];
				longs[j] = temp;
				i++;
				j--;
			}
		}
		if (low < j)
			quicksortLong(longs, low, j, direction);
		if (i < high)
			quicksortLong(longs, i, high, direction);
	}

	public static void sort(Comparable<Comparable>[] values, boolean ascending) {
		if (values == null || values.length == 0) {
			return;
		}
		quicksortComp(values, 0, values.length - 1, ascending);
	}

	public static void sort(double[] values, boolean ascending) {
		if (values == null || values.length == 0) {
			return;
		}
		quicksortDouble(values, 0, values.length - 1, ascending);
	}

	public static void sort(float[] values, boolean ascending) {
		if (values == null || values.length == 0) {
			return;
		}
		quicksortFloat(values, 0, values.length - 1, ascending);
	}

	public static void sort(int[] values, boolean ascending) {
		if (values == null || values.length == 0) {
			return;
		}
		quicksortInt(values, 0, values.length - 1, ascending);
	}

	public static void sort(long[] values, boolean ascending) {
		if (values == null || values.length == 0) {
			return;
		}
		quicksortLong(values, 0, values.length - 1, ascending);
	}

}