/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util;

/**
 * Provides a set of utility functions for manipulating Strings.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class StringUtil 
{
	public static final boolean TRIM 		= true;
	public static final boolean NO_TRIM 	= false;
	private static final String INTEGER 	= "[+-]?\\d+";
	private static final String HEXADEC 	= "^(0x|0X|#).[0-9A-Fa-f]+";
	private static final String FLOATING 	= "[+-]?((\\d+\\.?\\d*)|(\\.\\d+))";

	/**
	 * Returns {@code true} if the given String can be parsed as an integer number.
	 * @param str - the string to check
	 * @return {@code true} if the given String can be parsed as an integer number
	 * @since 1.0.0
	 */
	public static boolean isInteger(String str)
	{
		return str.matches(INTEGER);
	}
	
	/**
	 * Returns {@code true} if the given String can be parsed as an hexadecimal number.
	 * @param str - the string to check
	 * @return {@code true} if the given String can be parsed as an hexadecimal number
	 * @since 1.0.0
	 */
	public static boolean isHexadec(String str)
	{
		return str.matches(HEXADEC);
	}
	
	/**
	 * Returns {@code true} if the given String can be parsed as a floating point number.
	 * @param str - the string to check
	 * @return {@code true} if the given String can be parsed as a floating point number
	 * @since 1.0.0
	 */
	public static boolean isFloating(String str)
	{
		return str.matches(FLOATING);
	}
	
	/**
	 * Returns an array of String objects containing the parts separated with the given identifier.
	 * @param line - the line of text to split
	 * @param sep - the splitting separator pattern
	 * @param trim - trim each token?
	 * @return an array of String objects containing the parts separated with the given identifier
	 * @since 1.0.0
	 */
	public static String[] tokenize(String line, String sep, boolean trim)
	{
		String[] t = line.split(sep);
		if (trim)
		{
			for (int i=0,l=t.length; i<l; i++)
			{
				t[i] = t[i].trim();
			}
		}
		return t;
	}
	
	/**
	 * Returns an array of String objects containing the parts separated with the given identifier. Calling this method is similar to calling {@code tokenize(line,sep,true)}.
	 * @param line - the line of text to split
	 * @param sep - the splitting separator pattern
	 * @return an array of String objects containing the parts separated with the given identifier
	 * @since 1.0.0
	 */
	public static String[] tokenize(String line, String sep)
	{
		return tokenize(line,sep,true);
	}
	
	/**
	 * Returns the given filler text with the integer number's string representation.
	 * @param filler - the unfilled text to replace
	 * @param integer - the number to replace the text with
	 * @return the given filler text with the integer number's string representation
	 * @since 1.0.0
	 */
	public static String fillWithNumber(String filler, int integer) 
	{
		String number = String.valueOf(integer);
		return filler.substring(number.length()) + number;
	}
	
	/**
	 * Returns the message with added characters until a given length has been reached. If the provided string is longer than the specified length, then the end is clipped from the string.
	 * @param msg - the message to fill
	 * @param chr - the filler character
	 * @param leng - the length of the returning string
	 * @return the message with added characters until a given length has been reached
	 * @since 1.0.0
	 */
	public static String fill(String msg, String chr, int leng) 
	{
		String res = "";
		for (int s=0; s<leng; s++) res += chr;
		String concat = msg+res;
		return concat.substring(0, leng);
	}
	
	/**
	 * Turn the string array into a string with the given separator, start- and end-index. Returns the compiled array as a single string.
	 * @param tokens - the string tokens
	 * @param sep - the separator 
	 * @param start - the starting index in the array
	 * @param end - the ending index in the array
	 * @return the compiled array as a single string
	 * @since 1.0.0
	 */
	public static String compile(String[] tokens, String sep, int start, int end)
	{
		int i=start, e=end, l=tokens.length;
		i = (i<0)?0:i;
		e = (e<0)?0:e;
		i = (i>=l)?l-1:i;
		e = (e>=l)?l-1:e;
		String str = "";
		for (; i<=e; i++) str = str + ((i==start)?"":sep) + tokens[i];
		return str;
	}
	
	/**
	 * Returns the given text with all leading,ending and duplicate spaces removed.
	 * @param line - the text to parse
	 * @return the given text with all leading,ending and duplicate spaces removed
	 * @since 1.0.0
	 */
	public static String compact(String line)
	{
		return line.trim().replaceAll("\\s{2,}", " ");
	}
	
	/**
	 * Strip the leading characters from the given string until the terminator character has been found. The terminator character must be a single character, or nothing gets returned. Returns the string that follows after the first terminator character was found.
	 * @param line - the text to parse
	 * @param terminator - the terminator character
	 * @return the string that follows after the first terminator character was found
	 * @since 1.0.0
	 */
	public static String strip(String line, String terminator)
	{
		String remainder = line;
		int l = line.length();
		int i = 0;
		for (;i<l;i++)
		{
			String character = line.substring(i, i+1);
			if (character.equalsIgnoreCase(terminator))
			{
				remainder = line.substring( (i<l)?i+1:l ,l).trim();
				break;
			}
		}
		return remainder;
	}
	
	/**
	 * Scan characters from the start of the given string until the terminator has been found. Returns the scanned text before the first terminator was found. 
	 * @param line - the text to parse
	 * @param terminator - the terminator character
	 * @return the scanned text before the first terminator was found
	 * @since 1.0.0
	 */
	public static String scan(String line, String terminator)
	{
		String scanned = "";
		int l = line.length();
		int i = 0;
		for (;i<l;i++)
		{
			String character = line.substring(i, i+1);
			
			if (!character.equalsIgnoreCase(terminator))
			{
				scanned += character;
			}
			else
			{
				break;
			}
		}
		
		return scanned;
	}
}
