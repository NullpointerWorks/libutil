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
public class Tuple<A,B,C>
{
	public A first; 
	public B second;
	public C third;
	public Tuple(A a, B b, C c)
	{
		this.first=a;
		this.second=b;
		this.third=c;
	}
}
