/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.concurrent;

/**
 * 
 * @since 1.0.0
 */
public class Lock 
{
	private Boolean locked = false;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Lock() {}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Lock(boolean l) 
	{
		setLock(l);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void lock() 
	{
		synchronized(locked)
		{
			locked = true;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void unlock() 
	{
		synchronized(locked)
		{
			locked = false;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setLock(boolean l) 
	{
		synchronized(locked)
		{
			locked = l;
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean isLocked() 
	{
		synchronized(locked)
		{
			return locked;
		}
	}
}
