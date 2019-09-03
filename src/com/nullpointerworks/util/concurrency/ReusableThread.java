/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.concurrency;

import com.nullpointerworks.util.Log;

/**
 * A {@code ReusableThread} is a thread implementation that is meant to memory friendly. Normal {@code Thread} instances can not be reset after their main code has finished execution. To execute the code again, a new thread object needs to be instantiated. This threading subclass runs a provided Runnable instance without killing the main thread when finished.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ReusableThread extends Thread 
{
	private Runnable r = null;
	private Boolean hasRunnable = false;
	private Boolean isAlive = false;
	
	/**
	 * Creates a new Thread object without a set runnable code to execute. 
	 * @since 1.0.0
	 */
	public ReusableThread()
	{
		super();
	}
	
	/**
	 * Creates a new Thread object with an initial runnable code to execute. 
	 * @param r - the runnable to initialize with
	 * @since 1.0.0
	 */
	public ReusableThread(Runnable r)
	{
		super();
		setRunnable(r);
	}
	
	@Override
	public void start()
	{
		synchronized(isAlive)
		{
			if (!this.isAlive()) 
			{
				isAlive = true; 
				super.start();
			}
			else 
			{
				Log.err("ThreadPool double start detected - ID:"+this.getId());
			}
		}
	}
	
	@Override
	public void interrupt()
	{
		synchronized(isAlive)
		{
			isAlive = false;
			super.interrupt();
		}
	}
	
	/**
	 * Returns {@code true} if the thread has a runnable to execute.
	 * @return {@code true} if the thread has a runnable to execute
	 * @since 1.0.0
	 */
	public boolean hasRunnable() 
	{
		synchronized(hasRunnable)
		{
			return hasRunnable;
		}
	}
	
	/**
	 * Sets a reference of a runnable to run at the next iteration of the threading loop.
	 * @param run - the runnable to run
	 * @since 1.0.0
	 */
	public void setRunnable(Runnable run)
	{
		synchronized(hasRunnable)
		{
			free();
			r = run;
			hasRunnable = true;
		}
	}
	
	@Override
	public void run()
	{
		while(isAlive)
		{
			/*
			 * if we have code to execute, just do it!!
			 */
			synchronized(hasRunnable)
			{
				if (hasRunnable)
				{
					r.run();
					hasRunnable = false;
				}
			}
			
			try 
			{
				Thread.sleep(10,0);
			} 
			catch (InterruptedException e) 
			{
				Log.err("Thread pool sleep has been interrupted!");
				e.printStackTrace();
			}
		}
		
		free();
	}
	
	/**
	 * Clears the thread of it's currently executing runnable and sets the kill flag to end the thread.
	 * @since 1.0.0
	 */
	public void free()
	{
		synchronized(hasRunnable)
		{
			hasRunnable = false;
			r = null;
			isAlive = false;
		}
	}
}
