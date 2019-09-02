/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.pool;

import java.util.List;
import java.util.ArrayList;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.threading.ReusableThread;
import com.nullpointerworks.util.threading.Threading;
import com.nullpointerworks.util.threading.UnavailablePoolException;

/**
 * The {@code ThreadPool} is an object that manages asynchronous execution of {@code Runnable} instances. It contains a fixed size of threads that execute from an unbounded queue. Any {@code Runnable} added to the queue will be executed as soon as a thread has completed its previous task and is next in line to be executed.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ThreadPool extends Thread
{
	private int SLEEP_MILLI = 1;
	private int threads 	= 0;
	private Boolean isRunning = true;
	private ReusableThread[] pool;
	private List<Runnable> pending;
	
	/**
	 * Creates a new threading pool with instances of the {@code ReusableThread} class. It's minimum number of running threads is 1 and has no maximum initialization value.
	 * @param maxThreads - the maximum number of simultaneously running threads
	 * @since 1.0.0
	 * @see ReusableThread
	 */
	public ThreadPool(int maxThreads)
	{
		threads = (maxThreads<1)?1:maxThreads;
		pending = new ArrayList<Runnable>();
		pool 	= new ReusableThread[threads];
		
		for (int i=0; i<threads; i++)
		{
			ReusableThread r = new ReusableThread();
			r.start();
			pool[i] = r;
		}
		this.start();
	}
	
	@Override
	public void start()
	{
		if (!this.isAlive()) super.start();
		else Log.err("ThreadPool double start detected - ID:"+this.getId());
	}
	
	/**
	 * Add a Runnable to the execution queue. It will be executed after all preceding Runnable objects have been executed.
	 * @param r - the runnable to add to the queue
	 * @throws UnavailablePoolException when the primary thread is not(yet) operational
	 * @since 1.0.0
	 */
	public void execute(Runnable r) throws UnavailablePoolException
	{
		if (isRunning) throw new UnavailablePoolException();
		synchronized(pending)
		{
			pending.add( r );
		}
	}
	
	/**
	 * Raise a termination flag for the thread pool. At the next cycle of the threading pool check, the primary Thread will be terminated. All Runnable objects will we removed from the execution queue. All Threads currently running a Runnable will continue to run until their active code has ended.
	 * @since 1.0.0
	 */
	public void terminate() 
	{
		isRunning = false;
	}
	
	/**
	 * Returns {@code true} if all threads have completed and the main thread has been terminated.
	 * @return {@code true} if all threads have completed and the main thread has been terminated
	 * @since 1.0.0
	 */
	public boolean isTerminated() 
	{
		if (isRunning) return false;
		return isCompleted();
	}
	
	/**
	 * Returns {@code true} if all threads are idle and the execution queue is empty.
	 * @return {@code true} if all threads are idle and the execution queue is empty
	 * @since 1.0.0
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
	
	@Override
	public void run()
	{
		while(isRunning)
		{
			synchronized(pool)
			{
				for (int i=0; i<threads; i++)
				{
					ReusableThread t = pool[i];
					if (!t.hasRunnable())
					{
						synchronized(pending)
						{
							if (pending.isEmpty()) continue;
							Runnable r = pending.get(0);
							t.setRunnable(r);
							pending.remove(0);
						}
					}
				}
			}
			Threading.sleep(SLEEP_MILLI, 0);
		}
	}
	
	/**
	 * Clears the execution queue and sets all running threads in the termination status. The pool can not be used after calling this method.
	 * @since 1.0.0
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
