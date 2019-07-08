package com.nullpointerworks.util.pattern;

/**
 * callback utility for a visitor pattern
 */
public abstract class Callback 
{
	public abstract void onCall(int index);
	public void onReset() {}
	public boolean isComplete() {return false;}
}
