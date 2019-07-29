/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pattern;

/**
 * 
 * @since 1.0.0
 */
public interface Nullable 
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean isNull();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public default void isNull(boolean isnull) {}
}
