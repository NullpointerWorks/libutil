/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Pair<A,B>
{
	public A first; 
	public B second;
	public Pair(A a, B b)
	{
		this.first=a;
		this.second=b;
	}
}
