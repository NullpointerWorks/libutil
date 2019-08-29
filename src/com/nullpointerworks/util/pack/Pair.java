/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack;

/**
 * A template container for two objects of which their classes may vary. The class A can be accessed with the public field {@code Pair.first} and class B with the public field {@code Pair.second}.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Pair<A,B>
{
	/**
	 * The object reference of class A.
	 * @since 1.0.0
	 */
	public A first; 
	
	/**
	 * The object reference of class B.
	 * @since 1.0.0
	 */
	public B second;
	
	/**
	 * Creates a new object pair with the classes specified in the template definition.
	 * @param a - the first object
	 * @param b - the second object
	 * @since 1.0.0
	 */
	public Pair(A a, B b)
	{
		this.first=a;
		this.second=b;
	}
}
