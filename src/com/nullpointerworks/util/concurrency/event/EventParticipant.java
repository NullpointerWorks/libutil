/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.concurrency.event;

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
