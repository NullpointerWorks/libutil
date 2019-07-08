package com.nullpointerworks.util.threading.pool;

import com.nullpointerworks.util.Log;

public class ReusableThread extends Thread 
{
	private Runnable r = null;
	private Boolean hasRunnable = false;
	private Boolean isAlive = false;

	public ReusableThread()
	{
		super();
	}
	
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
	 * return true if the thread has a runnable to execute.
	 */
	public boolean hasRunnable() 
	{
		synchronized(hasRunnable)
		{
			return hasRunnable;
		}
	}
	
	/**
	 * execute the given runnable
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
	
	/**
	 * 
	 */
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
	
	/*
	 * 
	 */
	public void free()
	{
		synchronized(hasRunnable)
		{
			hasRunnable = false;
			r = null;
		}
	}
}
