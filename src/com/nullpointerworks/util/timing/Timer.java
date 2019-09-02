/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.timing;


/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Timer 
{
	private float time = 0f;
	private float delay = 1f;
	private Runnable call;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Timer(Runnable cb, float de)
	{
		call = cb;
		delay = de;
	}
	
	/**
	 * Update the timer and execute a callback in the same thread
	 * @since 1.0.0
	 */
	public void update(float dt)
	{
		time += dt;
		if (time >= delay)
		{
			time -= delay;
			call.run();
		}
	}
	
	/**
	 * Reset the counter and cumulative time
	 * @since 1.0.0
	 */
	public void reset()
	{
		time = 0f;
	}
	
	/**
	 * Update the timer, if the callback is triggered, it's executed in a new thread
	 * @since 1.0.0
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
