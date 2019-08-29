/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack.node;

/**
 * An interface to check for valid inputs when using the {@code Node<T>} system.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public interface NodeValidation<T>
{
	/**
	 * Returns {@code true} if the input object has been approved, {@code false} otherwise.
	 * @param input - the input object
	 * @return {@code true} if the input object has been approved, {@code false} otherwise.
	 * @since 1.0.0
	 */
	boolean isValid(T input);
}
