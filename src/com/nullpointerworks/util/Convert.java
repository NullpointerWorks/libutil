/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util;

/**
 * Contains a set of static delegate methods to help convert values.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Convert 
{
	/**
	 * Converts the content of a String into an integer value. 
	 * @param s - a string that represents the value to parse
	 * @throws NumberFormatException if the given string contains invalid characters
	 * @since 1.0.0
	 */
	public static int toInt(String s)
	throws NumberFormatException
	{return Integer.parseInt(s);}
	
	/**
	 * Converts the content of a String into a long integer value. 
	 * @param s - a string that represents the value to parse
	 * @throws NumberFormatException if the given string contains invalid characters
	 * @since 1.0.0
	 */
	public static long toLong(String s)
	throws NumberFormatException
	{return Long.parseLong(s);}
	
	/**
	 * Converts the content of a String into a float value. 
	 * @param s - a string that represents the value to parse
	 * @throws NumberFormatException if the given string contains invalid characters
	 * @since 1.0.0
	 */
	public static float toFloat(String s)
	throws NumberFormatException
	{return Float.parseFloat(s);}
	
	/**
	 * Converts the content of a String into a double value. 
	 * @param s - a string that represents the value to parse
	 * @throws NumberFormatException if the given string contains invalid characters
	 * @since 1.0.0
	 */
	public static double toDouble(String s)
	throws NumberFormatException
	{return Double.parseDouble(s);}
	
	/**
	 * Parses the value of the given string into a boolean value. Returns {@code true} if the string {@code s} contains the word {@code "true"} in either upper- or lower-case letters.
	 * @param s - the string to check
	 * @return {@code true} if the string {@code s} contains the word {@code "true"}
	 * @since 1.0.0
	 * @see Boolean.parseBoolean(String)
	 */
	public static boolean toBoolean(String s)
	{return "true".equalsIgnoreCase(s);}
	
	/**
	 * Turns the value of the given integer into a string representing that number.
	 * @param i - the integer to parse
	 * @since 1.0.0
	 */
	public static String toString(int i)		{return String.valueOf(i);}
	
	/**
	 * Turns the value of the given long into a string representing that number.
	 * @param i - the integer to parse
	 * @since 1.0.0
	 */
	public static String toString(long l)		{return String.valueOf(l);}
	
	/**
	 * Turns the value of the given float into a string representing that number.
	 * @param i - the integer to parse
	 * @since 1.0.0
	 */
	public static String toString(float f)		{return String.valueOf(f);}
	
	/**
	 * Turns the value of the given double into a string representing that number.
	 * @param i - the integer to parse
	 * @since 1.0.0
	 */
	public static String toString(double d)		{return String.valueOf(d);}
	
	/**
	 * Converts a hex value into an integer value. This method accepts variations of hexadecimal notations that use a leading '0x', '0X', '#' or ending with 'h' or 'H'. For an integer, which is 32-bit, each 8-bits represents two hexadecimal characters ranging from [0-9] and [A-F].
	 * @param hex - the hex code as a string
	 * @return the integer value of the given hex code
	 * @throws NumberFormatException if the amount of hexadecimal characters exceeds the maximum range
	 * @since 1.0.0
	 */
	public static int parseHexToInt(String hex)
	throws NumberFormatException
	{
		hex = hex.replace("0x", "");
		hex = hex.replace("0X", "");
		hex = hex.replace("#", "");
		hex = hex.replace("h", "");
		hex = hex.replace("H", "");
		if (hex.length() > 8) throw new NumberFormatException();
		return Integer.parseInt(hex,16);
	}
	
	/**
	 * Converts a hex value into an long integer value. This method accepts variations of hexadecimal notations that use a leading '0x', '0X', '#' or ending with 'h' or 'H'. For an integer, which is 32-bit, each 8-bits represents two hexadecimal characters ranging from [0-9] and [A-F].
	 * @param hex - the hex code as a string
	 * @return the integer value of the given hex code
	 * @throws NumberFormatException if the amount of hexadecimal characters exceeds the maximum range
	 * @since 1.0.0
	 */
	public static long parseHexToLong(String hex)
	{
		hex = hex.replace("0x", "");
		hex = hex.replace("0X", "");
		hex = hex.replace("#", "");
		hex = hex.replace("h", "");
		hex = hex.replace("H", "");
		if (hex.length() > 16) throw new NumberFormatException();
		return Long.parseLong(hex,16);
	}
}
