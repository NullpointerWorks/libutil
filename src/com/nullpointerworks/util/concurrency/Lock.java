/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.concurrency;

/**
 * This class is meant to help as a boolean lock for multi-threaded environments. It's a simple boolean switch with which all methods are synchronized to its internal state.
 * @since 1.0.0
 */
public class Lock 
{
	private Boolean locked = false;
	
	/**
	 * Creates a concurrent lock defaulted to {@code false}.
	 * @since 1.0.0
	 */
	public Lock() {}
	
	/**
	 * Creates a concurrent lock set to the given state.
	 * @param l - the initial lock state
	 * @since 1.0.0
	 */
	public Lock(boolean l) 
	{
		setLock(l);
	}
	
	/**
	 * Locks the state.
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
	 * Unlocks the state.
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
	 * Sets the state to the given boolean.
	 * @param l - the state to set to
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
	 * Returns the state of the lock.
	 * @return the state of the lock
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
