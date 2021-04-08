/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.pattern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the IIterator interface. It's constructors accept {@code List}, {@code Map<?,T>}, {@code Collection<T>} and {@code <T>[]} objects.
 * @since 1.0.0
 * @see IIterator
 * @author Michiel Drost - Nullpointer Works
 */
public class Iterator<T> implements IIterator<T>
{
	private int index = 0;
	private int size = 0;
	private List<T> lines;
	
	/**
	 * Creates an iterator bases on the content of the provided {@code <T>[]} object.
	 * @param l - the array consisting of {@code <T>} objects 
	 * @since 1.0.0
	 */
	public Iterator(T[] l)
	{
		lines = new ArrayList<T>();
		for (T t : l) lines.add(t);
		size = l.length;
	}
	
	/**
	 * Creates an iterator bases on the content of the provided {@code List<T>} object.
	 * @param l - the {@code List<T>} of {@code <T>} objects 
	 * @since 1.0.0
	 */
	public Iterator(List<T> l)
	{
		lines = l;
		size = l.size();
	}
	
	/**
	 * Creates an iterator bases on the content of the provided {@code Collection<T>} object.
	 * @param c - the {@code Collection<T>} of {@code <T>} objects 
	 * @since 1.0.0
	 */
	public Iterator(Collection<T> c)
	{
		this( new ArrayList<T>(c) );
	}
	
	/**
	 * Creates an iterator bases on the content of the provided {@code Map<?,T>} object.
	 * @param m - the {@code Map<?,T>} with {@code <T>} value objects 
	 * @since 1.0.0
	 */
	public Iterator(Map<?,T> m)
	{
		this( m.values() );
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
