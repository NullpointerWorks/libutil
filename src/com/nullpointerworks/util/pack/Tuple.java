/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack;

/**
 * A container for three objects of which their classes may vary. The class A can be accessed with the public field {@code Tuple.first}, class B with the public field {@code Tuple.second} and class C with the public field {@code Tuple.third}.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Tuple<A,B,C>
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
	 * The object reference of class C.
	 * @since 1.0.0
	 */
	public C third;
	
	/**
	 * Creates a new object tuple with the classes specified in the template definition.
	 * @param a - the first object
	 * @param b - the second object
	 * @param c - the third object
	 * @since 1.0.0
	 */
	public Tuple(A a, B b, C c)
	{
		this.first=a;
		this.second=b;
		this.third=c;
	}
}
