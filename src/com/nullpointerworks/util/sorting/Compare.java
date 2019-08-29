/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.sorting;

/**
 * An interface to implements a compare method for a QuickSort algorithm.
 * @since 1.0.0
 * @see QuickSort
 * @author Michiel Drost - Nullpointer Works
 */
public interface Compare<T>
{
	/**
	 * A comparable method to implement. Returns {@code true} to sort object a before object b. For ascending order sorting let a < b.
	 * @param a - object a to compare with b
	 * @param b - object b to determine a's place
	 * @return {@code true} to sort object a before object b
	 * @since 1.0.0
	 */
	public abstract boolean compare(T a, T b);
}
