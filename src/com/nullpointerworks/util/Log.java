/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

/**
 * A delegate class for making the {@code System.out} and {@code System.err} calls less verbose. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Log 
{
	/**
	 * Prints the given text to the {@code System.out} PrintStream. Calling this method is similar to calling {@code System.out.println(text)}.
	 * @param text - the text to print
	 * @since 1.0.0
	 */
	public static void out(String text)
	{
		System.out.println(text);
	}
	
	/**
	 * Prints the given text to the {@code System.err} PrintStream. Calling this method is similar to calling {@code System.err.println(text)}.
	 * @param text - the text to print
	 * @since 1.0.0
	 */
	public static void err(String text)
	{
		System.err.println(text);
	}
	
	/**
	 * Prints the given array of Strings to the {@code System.out} PrintStream. 
	 * @param text - the array of strings to print
	 * @since 1.0.0
	 */
	public static void out(String... text)
	{
		for (String m : text) out(m);
	}
	
	/**
	 * Prints the given array of Strings to the {@code System.err} PrintStream. 
	 * @param text - the array of strings to print
	 * @since 1.0.0
	 */
	public static void err(String... text)
	{
		for (String m : text) err(m);
	}
}
