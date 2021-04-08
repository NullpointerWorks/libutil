/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.timing;

/**
 * This implementation of a timer is meant to operate in a run-time loop that provides delta-time values per update. The accuracy of the timer depends on the granularity of the loop used.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Timer 
{
	private float time = 0f;
	private float delay = 0f;
	private Runnable call;
	
	/**
	 * Creates a timer object that executes the code present in the provided Runnable. The update delay is the time to have passed before the Runnable is called.
	 * @param cb - the callback Runnable
	 * @param de - delay time
	 * @since 1.0.0
	 */
	public Timer(Runnable cb, float de)
	{
		call = cb;
		delay = de;
	}
	
	/**
	 * Updates the timer and triggers the callback if the delay time has been reached.
	 * @param dt - the cumulative time to add
	 * @param newThread - set {@code true} if the callback needs to be executed in a new thread
	 * @since 1.0.0
	 */
	public void update(float dt, boolean newThread)
	{
		time += dt;
		if (time >= delay)
		{
			if (newThread)
			{Thread t = new Thread(call); t.start();}
			else call.run();
			time -= delay;
		}
	}
	
	/**
	 * Updates the timer and triggers the callback if the delay time has been reached. Calling this method is similar to calling {@code update(dt,false)}.
	 * @param dt - the cumulative time to add
	 * @since 1.0.0
	 */
	public void update(float dt)
	{
		update(dt,false);
	}
	
	/**
	 * Resets the timer count.
	 * @since 1.0.0
	 */
	public void reset()
	{
		time = 0f;
	}
}
