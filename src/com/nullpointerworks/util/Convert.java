/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * Set of delegate methods to help convert values
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Convert 
{
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static int toInt(String s)			{return Integer.parseInt(s);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static long toLong(String s)			{return Long.parseLong(s);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static float toFloat(String s)		{return Float.parseFloat(s);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static double toDouble(String s)		{return Double.parseDouble(s);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static boolean toBoolean(String s)	{return Boolean.parseBoolean(s);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String toString(int i)		{return String.valueOf(i);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String toString(long l)		{return String.valueOf(l);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String toString(float f)		{return String.valueOf(f);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String toString(double d)		{return String.valueOf(d);}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static int parseHexToInt(String hex)
	{
		hex = hex.replace("0x", "");
		hex = hex.replace("0X", "");
		hex = hex.replace("#", "");
		return Integer.parseInt(hex,16);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static long parseHexToLong(String hex)
	{
		hex = hex.replace("0x", "");
		hex = hex.replace("0X", "");
		hex = hex.replace("#", "");
		return Long.parseLong(hex,16);
	}
}
