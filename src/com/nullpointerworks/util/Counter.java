/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

public class Counter
{
	private Integer count = 0;
	
	public Counter() {}
	public Counter(int v) 
	{
		synchronized(count)
		{
			count = v;
		}
	}
	
	public void increment()
	{
		synchronized(count)
		{
			count += 1;
		}
	}
	
	public void decrement()
	{
		synchronized(count)
		{
			count -= 1;
		}
	}
	
	public int value()
	{
		synchronized(count)
		{
			return count;
		}
	}
}
