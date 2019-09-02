/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.event;

/**
 * A multi-threading event callback interface meant to be used by the {@code EventManager<T>} class.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 * @see EventManager
 */
public interface EventParticipant<E>
{
	/**
	 * A event callback to be implemented. Each execution of this method by the {@code EventManager} class is threaded in a separate thread.
	 * @param e - the event information template object
	 * @since 1.0.0
	 */
	public void onEvent(E e);
}
