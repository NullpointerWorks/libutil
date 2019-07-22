/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.pool;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.pack.Array;
import com.nullpointerworks.util.threading.Threading;

public class ThreadPool extends Thread
{
	private final int SLEEP_MILLI = 1;
	private int threads = 0;
	private Boolean isRunning = true;
	private ReusableThread[] pool;
	private Array<Runnable> pending;
	
	public ThreadPool(int t)
	{
		threads = (t<1)?1:t;
		pending = new Array<Runnable>();
		pool 	= new ReusableThread[threads];
		
		for (int i=0; i<threads; i++)
		{
			ReusableThread r = new ReusableThread();
			r.start();
			pool[i] = r;
		}
		this.start();
	}
	
	/**
	 * 
	 */
	@Override
	public void start()
	{
		if (!this.isAlive()) super.start();
		else Log.err("ThreadPool double start detected - ID:"+this.getId());
	}
	
	/**
	 * Add a Runnable to the execution list.
	 */
	public void execute(Runnable r)
	{
		synchronized(pending)
		{
			pending.add( r );
		}
	}
	
	/**
	 * Raise a termination flag for the thread pool
	 */
	public void terminate() 
	{
		isRunning = false;
	}
	
	/**
	 * Returns true if all threads have completed and the main thread has been terminated
	 */
	public boolean isTerminated() 
	{
		if (isRunning) return false;
		return isCompleted();
	}
	
	/**
	 * Returns true if all threads have completed
	 */
	public boolean isCompleted() 
	{
		synchronized(pending)
		{
			if (!pending.isEmpty()) return false;
		}
		
		synchronized(pool)
		{
			for (int i=0; i<threads; i++)
			{
				ReusableThread t = pool[i];
				if (t.hasRunnable()) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public void run()
	{
		while(isRunning)
		{
			// check states in the pool
			synchronized(pool)
			{
				for (int i=0; i<threads; i++)
				{
					ReusableThread t = pool[i];
					if (!t.hasRunnable())
					{
						synchronized(pending)
						{
							// if no runnable available, skip
							if (pending.isEmpty()) continue;
							Runnable r = pending.get(0);
							t.setRunnable(r);
							pending.remove(0);
						}
					}
				}
			}
			
			Threading.sleep(SLEEP_MILLI, 0);
			
		}//end while
	}
	
	/*
	 * clean the pool
	 */
	public void free()
	{
		pending.clear();
		pending = null;
		for (int i=0; i<threads; i++)
		{
			ReusableThread t = pool[i];
			t.free();
			pool[i] = null;
		}
		pool = null;
		isRunning = null;
	}
}
