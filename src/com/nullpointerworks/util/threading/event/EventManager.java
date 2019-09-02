/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.threading.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.Vector;

/**
 * The {@code EventManager<E>} is a multi-threaded event system to pass information to each {@code EventParticipant<E>} added to the manager object. The template {@code E} class can be anything that contains event specific information. Like participant targets, transferring data structures, etc.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 * @see EventParticipant
 */
public class EventManager<E>
{
	private List<EventParticipant<E>> participants;
	private List<E> eventlist;
	private ExecutorService executor;
	private boolean run = true;
	
	/**
	 * Creates a new {@code EventManager} object. The creation of this object automatically starts the manager thread.
	 * @param maxThreads - the maximum number of simultaneously running thread
	 * @since 1.0.0
	 */
	public EventManager(int maxThreads)
	{
		participants = new Vector<EventParticipant<E>>();
		eventlist = new Vector<E>();
		executor = Executors.newFixedThreadPool(maxThreads);
		run = true;
		startEventManager();
	}
	
	/**
	 * 
	 * @param 
	 * @since 1.0.0
	 */
	public void addParticipant(EventParticipant<E> p)
	{
		participants.add(p);
	}
	
	/**
	 * 
	 * @param 
	 * @since 1.0.0
	 */
	public void fireEvent(E e)
	{
		eventlist.add(e);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void stop()
	{
		run = false;
	}
	
	/**
	 * Starts the manager thread.
	 * @since 1.0.0
	 */
	private void startEventManager()
	{
		Thread r = new Thread()
		{
			@Override
			public void run()
			{
				while(run)
				{
					if (eventlist.size()>0)
					{
						E e = eventlist.get(0);
						for (EventParticipant<E> ep : participants)
						{
							Runnable t = new Thread()
							{
								@Override
								public void run()
								{
									ep.onEvent(e);
								}
							};
							executor.execute(t);
						}
						eventlist.remove(0);
					}
					else
					{
						try
						{
							Thread.sleep(100);
						} 
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
					}
				}
				executor.shutdown();
			    while (!executor.isTerminated()) { }
			}
		};
		r.start();
	}
}
