package com.nullpointerworks.util.sorting;

public interface Compare<T>
{
	/*
	 * for ascending sorting let a < b.
	 */
	public abstract boolean compare(T a, T b);
}
