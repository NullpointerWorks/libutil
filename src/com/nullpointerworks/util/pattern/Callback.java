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
	 * An event trigger that passes an object for additional call information.
	 * @param ref - the reference object if implemented
	 * @since 1.0.0
	 */
	public abstract void onCall(T ref);
	
	/**
	 * An optional event trigger method that can reset the callback if implemented. This method does nothing if not overridden.
	 * @since 1.0.0
	 */
	public void onReset() {}
	
	/**
	 * An optional method to check for call completion. This method returns {@code false} by default if not overridden.
	 * @return {@code false} by default if not overridden
	 * @since 1.0.0
	 */
	public boolean isComplete() {return false;}
}
