/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

/**
 * A callback utility template for a visitor pattern. The template class is used as a reference for custom data transfers.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public abstract class Callback<T>
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public abstract void onCall(T ref);
	
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
