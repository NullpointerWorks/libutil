/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.pool;

import java.util.ArrayList;

public class ThreadList extends ThreadPool
{
	private ArrayList<Runnable> list;
	private int THREADS_STORED = 0;
	
	public ThreadList(int t) 
	{
		super(t);
		list = new ArrayList<Runnable>();
	}
	
	/**
	 * Restock the thread pool
	 */
	public void restock()
	{
		synchronized(list)
		{
			// put the list on the execution list
			for (int i=0; i<THREADS_STORED; i++)
			{
				execute( list.get(i) );
			}
		}
	}
	
	/**
	 * Add a Runnable to the execution list.
	 */
	public void store(Runnable r)
	{
		synchronized(list)
		{
			list.add(r);
			THREADS_STORED++;
		}
	}
	
	/**
	 * 
	 */
	public void free()
	{
		super.free();
		list.clear();
		list = null;
	}
}
