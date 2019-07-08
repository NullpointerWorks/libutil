package com.nullpointerworks.util.timing;

public class Delay 
{
	private float delayCount;
	private float delayMax;
	private boolean enabled = true;
	
	private int repeatCount;
	private int repeatMax;
	private boolean repeatBool = false;
	
	public Delay(float max)
	{
		delayMax=max;
		delayCount=0f;
		repeatMax=repeatCount=0;
	}
	
	public Delay(float max, int repeat, boolean repeatedBool)
	{
		delayMax=max;
		delayCount=0f;
		repeatMax=repeat;
		repeatCount=0;
		repeatBool=repeatedBool;
	}
	
	public boolean mayRun(float time)
	{
		add(time);
		return mayRun();
	}
	
	public void add(float time)
	{
		if (isEnabled())
			delayCount+=time;
	}
	
	public boolean mayRun()
	{
		if (isEnabled())
		{
			if (repeatMax > 0) // if we are setting repetition
			{
				if (repeatCount >= repeatMax)
					return repeatBool;
			}
			
			if (delayCount>=delayMax)
			{
				delayCount = 0f;
				repeatCount++;
				return true;
			}
		}
		return false;
	}
	
	public boolean isEnabled() 
	{
		return enabled;
	}

	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}
	
	public void reset()
	{
		delayCount = 0f;
		repeatCount = 0;
	}
	
	public void setDelay(float time)
	{
		delayMax=time;
	}
}
