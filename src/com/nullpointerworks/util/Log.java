/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Log 
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void out(String... text)
	{
		for (String m : text)
		{
			out(m);
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void err(String... text)
	{
		for (String m : text)
		{
			err(m);
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void out(String text)
	{
		System.out.println(text);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void err(String text)
	{
		System.err.println(text);
	}
}
