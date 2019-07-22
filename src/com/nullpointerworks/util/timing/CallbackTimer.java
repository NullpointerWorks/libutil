/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.timing;

import com.nullpointerworks.util.pattern.Callback;

public class CallbackTimer 
{
	private int counter = 0;
	private float time = 0f;
	private float delay = 1f;
	private Callback call;
	
	public CallbackTimer(Callback cb, float de)
	{
		call = cb;
		delay = de;
	}
	
	/**
	 * Update the timer and execute a callback in the same thread
	 */
	public void update(float dt)
	{
		time += dt;
		if (time >= delay)
		{
			call.onCall(counter);
			counter++;
			time -= delay;
		}
	}
	
	/**
	 * Reset the counter and cumulative time
	 */
	public void reset()
	{
		counter = 0;
		time = 0f;
	}
	
	/**
	 * Update the timer, if the callback is triggered, it's executed in a new thread
	 */
	public void updateThreaded(float dt)
	{
		time += dt;
		if (time >= delay)
		{
			Runnable r = new Runnable()
			{
				public void run() 
				{
					call.onCall(counter);
					counter++;
				}
			};
			Thread t = new Thread(r);
			t.start();
			
			time -= delay;
		}
	}
}
