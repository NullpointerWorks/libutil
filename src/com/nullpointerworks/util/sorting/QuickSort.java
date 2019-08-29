/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.sorting;

/**
 * Contains an implementation of the Quick Sort algorithm. This abstract class will force the creation of a {@code compare(T a, T b)} method.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public abstract class QuickSort<T> implements Compare<T>
{
	/**
	 * Sort the given array with using the QuickSort algorithm.
	 * @param A - the array to sort
	 * @since 1.0.0
	 */
	public void sort(T[] A)
	{
		sort(A, 0, A.length - 1);
	}
	
	/**
	 * start the quick-sort algorithm
	 * @since 1.0.0
	 */
	private void sort(T[] A, int lo, int hi)
	{
		if (lo < hi)
		{
			int p = partition(A, lo, hi);
			sort(A, lo, p-1);
			sort(A, p+1, hi);
		}
	}
	
	/**
	 * test a subdivision in the array
	 * @since 1.0.0
	 */
	private int partition(T[] A, int lo, int hi)
	{
		T pivot = A[hi];
		int i = lo - 1;
		
		for (int j = lo; j<hi; j++)
		{
			if (compare(A[j], pivot)) //if (A[j] < pivot)
			if (j != i)
			{
				i++;
				swap(A,i,j);
			}
		}
		
		i++;
		swap(A,i,hi);
		return i;
	}
	
	/**
	 * swap two values in the array
	 * @since 1.0.0
	 */
	private void swap(T[] a, int i1, int i2)
	{
		T temp = a[i1];
		a[i1] = a[i2];
		a[i2] = temp;
	}
}
