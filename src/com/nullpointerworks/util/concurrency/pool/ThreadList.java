/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.concurrency.pool;

import java.util.List;

import com.nullpointerworks.util.concurrency.ReusableThread;
import com.nullpointerworks.util.concurrency.UnavailablePoolException;

import java.util.ArrayList;

/**
 * The {@code ThreadList} is a subclass of the {@code ThreadPool} class with the additional feature of restocking the pool a fixed list of stored Runnable references. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 * @see ThreadPool
 */
public class ThreadList extends ThreadPool
{
	private List<Runnable> list;
	private int THREADS_STORED = 0;
	
	/**
	 * Creates a new threading pool with instances of the {@code ReusableThread} class. It's minimum number of running threads is 1 and has no maximum initialization value.
	 * @param maxThreads - the maximum number of simultaneously running threads
	 * @since 1.0.0
	 * @see ReusableThread
	 * @see ThreadPool
	 */
	public ThreadList(int maxThreads) 
	{
		super(maxThreads);
		list = new ArrayList<Runnable>();
	}
	
	/**
	 * Restocks the ThreadPool with a new set of references to the ThreadPool queue. This method does not check for currently held duplicates of the stored Runnable objects. 
	 * @throws UnavailablePoolException when the primary thread is not(yet) operational
	 * @since 1.0.0
	 */
	public void restock() throws UnavailablePoolException
	{
		synchronized(list)
		{
			for (int i=0; i<THREADS_STORED; i++)
			{
				execute( list.get(i) );
			}
		}
	}
	
	/**
	 * Add a Runnable to the execution stockpile.
	 * @param r - the Runnable to store
	 * @since 1.0.0
	 */
	public void store(Runnable r)
	{
		synchronized(list)
		{
			list.add(r);
			THREADS_STORED++;
		}
	}
	
	@Override
	public void free()
	{
		super.free();
		list.clear();
		list = null;
	}
}
