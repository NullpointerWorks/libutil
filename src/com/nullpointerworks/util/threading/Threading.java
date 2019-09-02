/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading;

import com.nullpointerworks.util.threading.pool.ThreadList;
import com.nullpointerworks.util.threading.pool.ThreadPool;

/**
 * An ease-of-access method collection for threading utilities.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Threading 
{
	/**
	 * Creates a new {@code Thread} object from the provided {@code Runnable}. When {@code boot} is set {@code true} it will start the new thread.
	 * @param run - the {@code Runnable} to execute in a new thread
	 * @param boot - set {@code true} to start the new thread, {@code false} otherwise
	 * @return a new {@code Thread} object from the provided {@code Runnable}
	 * @since 1.0.0
	 */
	public static Thread New(Runnable run, boolean boot)
	{
		Thread t = new Thread(run);
		if (boot) t.start();
		return t;
	}
	
	/**
	 * Creates a new {@code ThreadPool} object with a specified maximum number of threads.
	 * @param threads - the maximum number of threads
	 * @return a new {@code ThreadPool} object with a specified maximum number of threads
	 * @since 1.0.0
	 * @see ThreadPool
	 */
	public static ThreadPool newPool(int threads)
	{
		return new ThreadPool(threads);
	}
	
	/**
	 * Creates a new {@code ThreadList} object with a specified maximum number of threads.
	 * @param threads - the maximum number of threads
	 * @return a new {@code ThreadList} object with a specified maximum number of threads
	 * @since 1.0.0
	 * @see ThreadList
	 */
	public static ThreadList newList(int threads)
	{
		return new ThreadList(threads);
	}
	
	/**
	 * Causes the currently executing thread to sleep (temporarily cease execution) for a number of milliseconds.
	 * @param milli - the amount of milliseconds to sleep
	 * @since 1.0.0
	 * @see Thread
	 */
	public static void sleep(long milli)
	{
		try 
		{Thread.sleep(milli);} 
		catch (InterruptedException e) 
		{e.printStackTrace();}
	}
	
	/**
	 * Causes the currently executing thread to sleep (temporarily cease execution) for a number of milliseconds and nanoseconds.
	 * @param milli - the amount of milliseconds to sleep 
	 * @param nano - from 0-999999 the additional amount of nanoseconds to sleep
	 * @since 1.0.0
	 * @see Thread
	 */
	public static void sleep(long milli, int nano)
	{
		try 
		{Thread.sleep(milli, nano);} 
		catch (InterruptedException e) 
		{e.printStackTrace();}
	}
}
