/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.sorting;

public interface Compare<T>
{
	/*
	 * for ascending sorting let a < b.
	 */
	public abstract boolean compare(T a, T b);
}
