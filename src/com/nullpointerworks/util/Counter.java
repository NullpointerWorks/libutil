/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * 
 * @since 1.0.0
 */
public class Counter
{
	private Integer count = 0;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Counter() {}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Counter(int v) 
	{
		synchronized(count)
		{
			count = v;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void increment()
	{
		synchronized(count)
		{
			count += 1;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void decrement()
	{
		synchronized(count)
		{
			count -= 1;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int value()
	{
		synchronized(count)
		{
			return count;
		}
	}
}
