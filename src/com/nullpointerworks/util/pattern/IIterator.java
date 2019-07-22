/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

public interface IIterator<C>
{	
	public boolean hasNext();
	public C getNext();
	public int getSize();
	public int getIndex();
	public void reset();
}