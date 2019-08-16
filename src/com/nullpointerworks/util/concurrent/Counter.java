/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.concurrent;

/**
 * This class is meant to help count in multi-threaded environments. It's a simple integer counter with all concurrent methods.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Counter
{
	private Integer count = 0;
	
	/**
	 * Initialize a {@code Counter} object and default it to {@code 0}.
	 * @since 1.0.0
	 */
	public Counter() {}
	
	/**
	 * Initialize a {@code Counter} object and default it to the given value.
	 * @param v - the initialization value
	 * @since 1.0.0
	 */
	public Counter(int v) 
	{
		set(v);
	}
	
	/**
	 * Increments the counter by one. There is no coded maximum value aside from the maximum value set by the JVM.
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
	 * Decrements the counter by one. There is no coded minimum value aside from the minimum value set by the JVM.
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
	 * Sets the value of the counter to the given value.
	 * @param v - the value to set
	 * @since 1.0.0
	 */
	public void set(int v) 
	{
		synchronized(count)
		{
			count = v;
		}
	}
	
	/**
	 * Returns the counter value.
	 * @return the counter value
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
