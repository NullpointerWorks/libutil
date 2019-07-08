package com.nullpointerworks.util.threading.event;

public abstract class EventParticipant<E>
{
	public abstract void onEvent(E e);
}
