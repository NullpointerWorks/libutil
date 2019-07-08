package com.nullpointerworks.util;

public class ByteUtil
{
	/** 
	 * Concatenate the given byte arrays. 
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
