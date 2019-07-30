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
public class Lock 
{
	private boolean locked = false;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Lock() {}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Lock(boolean l) {locked = l;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void lock() {locked = true;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void unlock() {locked = false;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setLock(boolean l) {locked = l;}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean isLocked() {return locked;}
}
