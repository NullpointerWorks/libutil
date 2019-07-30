/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * 
 * @since 1.0.0
 */
public class ArrayUtil
{
	/** 
	 * Concatenate the given byte arrays. 
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
	 * Copy the given byte array. 
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
}
