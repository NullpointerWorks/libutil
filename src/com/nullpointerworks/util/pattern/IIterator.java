/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

/**
 * An iterator pattern interface for safe loop iteration over lists of objects.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public interface IIterator<C>
{	
	/**
	 * Returns {@code true} if there's another element in the list to iterate over, {@code false} otherwise.
	 * @return {@code true} if there's another element in the list to iterate over, {@code false} otherwise
	 * @since 1.0.0
	 */
	public boolean hasNext();
	
	/**
	 * Returns the next template object in the list iteration.
	 * @return the next template object in the list iteration
	 * @since 1.0.0
	 */
	public C getNext();
	
	/**
	 * Returns the size of the list.
	 * @return the size of the list
	 * @since 1.0.0
	 */
	public int getSize();
	
	/**
	 * Returns the current index location. The index adds 1 every time the {@code getNext()} method is called.
	 * @return the current index location
	 * @since 1.0.0
	 */
	public int getIndex();
	
	/**
	 * Resets the iterator index back to 0, if implemented.
	 * @since 1.0.0
	 */
	public void reset();
}