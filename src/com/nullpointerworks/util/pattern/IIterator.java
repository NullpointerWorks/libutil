package com.nullpointerworks.util.pattern;

public interface IIterator<C>
{	
	public boolean hasNext();
	public C getNext();
	public int getSize();
	public int getIndex();
	public void reset();
}