/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.timing;

/**
 * A delay is used for real-time applications that use iterative time updates to prevent code from being executed too often. The reliability of the Delay object depends on the time step granularity of the loop used. Applications with large time step granularity will cause this delay to be much later than expected. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Delay 
{
	private float delayCount;
	private float delayMax;
	private int repeatCount;
	private int repeatMax;
	private boolean enabled = true;
	
	/**
	 * Creates a new delay with a specified trigger time. This constructor is identical to calling the following:<pre> new Delay(delay,0);</pre>
	 * @param delay - the time to update before it triggers
	 * @since 1.0.0
	 */
	public Delay(float delay)
	{
		this(delay,0);
	}
	
	/**
	 * Creates a new delay with a specified trigger time and repetition count. This delay will cease to function when the amount of triggers has exceeded the repeat count.
	 * @param delay - the time to update before it triggers
	 * @param repeat - the amount of times this delay may repeat
	 * @since 1.0.0
	 */
	public Delay(float delay, int repeat)
	{
		setDelay(delay);
		delayCount=0f;
		repeatMax=repeat;
		repeatCount=0;
	}
	
	/**
	 * Returns {@code true} if the time added to the counter has exceeded the preset timing and has not exceeded it's maximum repeat count, if set. Returns {@code false} otherwise, or if not enabled.
	 * @param time - the time to add before checking
	 * @return {@code true} if the time added to the counter has exceeded the preset timing and has not exceeded it's maximum repeat count
	 * @since 1.0.0
	 */
	public boolean onCheck(float time)
	{
		if (isEnabled())
		{
			delayCount+=time;
			
			if (repeatMax > 0)
			{
				if (repeatCount >= repeatMax) return false;
			}
			
			if (delayCount>=delayMax)
			{
				delayCount = 0f;
				if (repeatMax > 0) repeatCount++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns {@code true} if the time counter has exceeded the preset timing, but has not exceeded it's maximum repeat count, if set. Returns {@code false} otherwise, or if not enabled. Calling this method is similar to calling {@code onCheck(0f)}.
	 * @return {@code true} if the counter has exceeded the preset timing and has not exceeded it's maximum repeat count
	 * @since 1.0.0
	 */
	public boolean onCheck()
	{
		return onCheck(0f);
	}
	
	/**
	 * Returns {@code true} if the delay is enabled.
	 * @return {@code true} if the delay is enabled
	 * @since 1.0.0
	 */
	public boolean isEnabled() 
	{
		return enabled;
	}
	
	/**
	 * Sets the delay timing. This will not reset the internal delay count.
	 * @param time - the new maximum delay time
	 * @since 1.0.0
	 */
	public void setDelay(float time)
	{
		delayMax=time;
	}
	
	/**
	 * Enable or disable the delay object.
	 * @param enable - set {@code true} to enable the delay, {@code false} otherwise to disable
	 * @since 1.0.0
	 */
	public void setEnabled(boolean enable) 
	{
		this.enabled = enable;
	}
	
	/**
	 * Hard resets the delay by settings all counters back to 0. This will also enable the delay.
	 * @since 1.0.0
	 */
	public void reset()
	{
		delayCount = 0f;
		repeatCount = 0;
		enabled = true;
	}
}
