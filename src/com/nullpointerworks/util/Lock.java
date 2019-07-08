package com.nullpointerworks.util;

public class Lock 
{
	private boolean locked = false;
	public Lock() {}
	public Lock(boolean l) {locked = l;}
	public void lock() {locked = true;}
	public void unlock() {locked = false;}
	public void setLock(boolean l) {locked = l;}
	public boolean isLocked() {return locked;}
}
