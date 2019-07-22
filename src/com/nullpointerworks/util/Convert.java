/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * Set of delegate methods to help convert values
 */
public class Convert 
{
	public static int toInt(String s)		{return Integer.parseInt(s);}
	public static long toLong(String s)		{return Long.parseLong(s);}
	public static float toFloat(String s)	{return Float.parseFloat(s);}
	public static double toDouble(String s)	{return Double.parseDouble(s);}
	
	public static String toString(int i)	{return String.valueOf(i);}
	public static String toString(long l)	{return String.valueOf(l);}
	public static String toString(float f)	{return String.valueOf(f);}
	public static String toString(double d)	{return String.valueOf(d);}

	public static int parseHex(String hex) 	
	{
		hex = hex.replace("0x", "");
		hex = hex.replace("0X", "");
		hex = hex.replace("#", "");
		return Integer.parseInt(hex,16);
	}
	
}
