package com.nullpointerworks.util.pattern;

import java.util.List;

public class Iterator<T> implements IIterator<T>
{
	private int index = 0;
	private int size = 0;
	private List<T> lines;
	
	public Iterator(List<T> l)
	{
		lines = l;
		size = l.size();
	}
	
	public boolean hasNext()
	{
		if (index<size) return true;
		return false;
	}
	
	public T getNext()
	{
		if (size<1) return null;
		return lines.get(index++);
	}
	
	public int getSize()
	{
		return lines.size();
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public void reset()
	{
		index = 0;
	}
}