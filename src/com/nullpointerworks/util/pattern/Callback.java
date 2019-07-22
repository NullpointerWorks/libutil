/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
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
