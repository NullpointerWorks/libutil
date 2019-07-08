package com.nullpointerworks.util.threading.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Vector;

public class EventManager<E>
{
	private Vector<EventParticipant<E>> participants = new Vector<EventParticipant<E>>();
	private Vector<E> eventlist = new Vector<E>();
	private ExecutorService executor;
	private boolean run = true;
	
	public EventManager(String maxThreads)
	{
		setEventManager(maxThreads);
	}
	
	public EventManager(int maxThreads)
	{
		setEventManager(maxThreads);
	}
	
	public void setEventManager(String maxThreads)
	{
		int max = Integer.parseInt(maxThreads);
		setEventManager(max);
	}
	
	public void setEventManager(int maxThreads)
	{
		executor = Executors.newFixedThreadPool(maxThreads);
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
	
	public void addParticipant(EventParticipant<E> p)
	{
		participants.add(p);
	}
	
	public void fireEvent(E e)
	{
		eventlist.add(e);
	}
	
	public void stopEventManager()
	{
		run = false;
	}
}
