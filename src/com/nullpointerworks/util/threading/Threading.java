package com.nullpointerworks.util.threading;

import com.nullpointerworks.util.threading.pool.ThreadList;
import com.nullpointerworks.util.threading.pool.ThreadPool;

public class Threading 
{

	public static ThreadPool newPool(int threads)
	{
		return new ThreadPool(threads);
	}
	
	public static ThreadList newList(int threads)
	{
		return new ThreadList(threads);
	}
	
	public static Thread newThread(boolean run)
	{
		Thread t = new Thread();
		if (run) t.start();
		return t;
	}
	
	public static void sleep(long milli)
	{
		try 
		{
			Thread.sleep(milli);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void sleep(long milli, int nano)
	{
		try 
		{
			Thread.sleep(milli, nano);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
}
