/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * Contains a set of static methods to concatenate or copy primitive type arrays.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class ArrayUtil
{
	/** 
	 * Concatenate the given byte arrays. The returned array will contains all the values of the first array (arr0) followed by the content of the second (arr1).
	 * @param arr0 - the first array
	 * @param arr1 - the second array
	 * @return a new combined array
	 * @since 1.0.0
	 */
	public static byte[] concatenate(byte[] arr0, byte[] arr1)
	{
		byte[] totaldata = new byte[arr0.length + arr1.length];
		
		int i=0;
		for (byte b : arr0)
		{
			totaldata[i] = b;
			i++;
		}
		for (byte b : arr1)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Concatenate the given short arrays. The returned array will contains all the values of the first array (arr0) followed by the content of the second (arr1).
	 * @param arr0 - the first array
	 * @param arr1 - the second array
	 * @return a new combined array
	 * @since 1.0.0
	 */
	public static short[] concatenate(short[] arr0, short[] arr1)
	{
		short[] totaldata = new short[arr0.length + arr1.length];
		
		int i=0;
		for (short b : arr0)
		{
			totaldata[i] = b;
			i++;
		}
		for (short b : arr1)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Concatenate the given integer arrays. The returned array will contains all the values of the first array (arr0) followed by the content of the second (arr1).
	 * @param arr0 - the first array
	 * @param arr1 - the second array
	 * @return a new combined array
	 * @since 1.0.0
	 */
	public static int[] concatenate(int[] arr0, int[] arr1)
	{
		int[] totaldata = new int[arr0.length + arr1.length];
		
		int i=0;
		for (int b : arr0)
		{
			totaldata[i] = b;
			i++;
		}
		for (int b : arr1)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Concatenate the given long arrays. The returned array will contains all the values of the first array (arr0) followed by the content of the second (arr1).
	 * @param arr0 - the first array
	 * @param arr1 - the second array
	 * @return a new combined array
	 * @since 1.0.0
	 */
	public static long[] concatenate(long[] arr0, long[] arr1)
	{
		long[] totaldata = new long[arr0.length + arr1.length];
		
		int i=0;
		for (long b : arr0)
		{
			totaldata[i] = b;
			i++;
		}
		for (long b : arr1)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	// ===============================================
	
	/** 
	 * Copy the given byte array. 
	 * @param arr - the array to copy
	 * @return a new array
	 * @since 1.0.0
	 */
	public static byte[] copy(byte[] arr)
	{
		byte[] totaldata = new byte[arr.length];
		int i=0;
		for (byte b : arr)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Copy the given short array.
	 * @param arr - the array to copy
	 * @return a new array 
	 * @since 1.0.0
	 */
	public static short[] copy(short[] arr)
	{
		short[] totaldata = new short[arr.length];
		int i=0;
		for (short b : arr)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Copy the given integer array.
	 * @param arr - the array to copy
	 * @return a new array 
	 * @since 1.0.0
	 */
	public static int[] copy(int[] arr)
	{
		int[] totaldata = new int[arr.length];
		int i=0;
		for (int b : arr)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
	
	/** 
	 * Copy the given long array.
	 * @param arr - the array to copy
	 * @return a new array 
	 * @since 1.0.0
	 */
	public static long[] copy(long[] arr)
	{
		long[] totaldata = new long[arr.length];
		int i=0;
		for (long b : arr)
		{
			totaldata[i] = b;
			i++;
		}
		return totaldata;
	}
}
