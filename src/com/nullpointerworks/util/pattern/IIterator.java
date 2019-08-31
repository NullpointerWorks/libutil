/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public interface IIterator<C>
{	
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean hasNext();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public C getNext();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getSize();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getIndex();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void reset();
}