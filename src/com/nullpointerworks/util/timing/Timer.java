/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.timing;

public class Timer 
{
	private float time = 0f;
	private float delay = 1f;
	private Runnable call;
	
	public Timer(Runnable cb, float de)
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
			call.run();
			time -= delay;
		}
	}
	
	/**
	 * Reset the counter and cumulative time
	 */
	public void reset()
	{
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
			Thread t = new Thread(call);
			t.start();
			time -= delay;
		}
	}
}
