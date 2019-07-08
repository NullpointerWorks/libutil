package com.nullpointerworks.util.pack;

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
