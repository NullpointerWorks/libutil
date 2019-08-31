/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Iterator<T> implements IIterator<T>
{
	private int index = 0;
	private int size = 0;
	private List<T> lines;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Iterator(List<T> l)
	{
		lines = l;
		size = l.size();
	}
	
	@Override
	public boolean hasNext()
	{
		if (index<size) return true;
		return false;
	}
	
	@Override
	public T getNext()
	{
		if (size<1) return null;
		return lines.get(index++);
	}
	
	@Override
	public int getSize()
	{
		return lines.size();
	}

	@Override
	public int getIndex()
	{
		return index;
	}

	@Override
	public void reset()
	{
		index = 0;
	}
}