/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.event;

public abstract class EventParticipant<E>
{
	public abstract void onEvent(E e);
}
