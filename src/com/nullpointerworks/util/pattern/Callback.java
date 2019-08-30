/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

/**
 * callback utility for a visitor pattern
 * @since 1.0.0
 */
public abstract class Callback 
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public abstract void onCall(int index);
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void onReset() {}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean isComplete() {return false;}
}
