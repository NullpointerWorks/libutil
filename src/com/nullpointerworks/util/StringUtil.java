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
public class StringUtil 
{
	public static final boolean NO_TRIM = false;
	public static final boolean TRIM 	= true;
	
	/**
	 * Returns an array of String objects containing the parts separated with the given identifier.
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
	 * Returns an array of String objects containing the parts separated with the given identifier.
	 * @since 1.0.0
	 */
	public static String[] tokenize(String line, String sep)
	{
		return tokenize(line,sep,true);
	}
	
	/**
	 * Returns a reference free clone of the given array
	 * @since 1.0.0
	 */
	public static String[] clone(String[] a)
	{
		String[] ret = new String[a.length];
		for (int i=0,l=a.length; i<l; i++)
		{
			ret[i] = ""+a[i];
		}
		return ret;
	}
	
	/*
	 * Regular expressions for string checking.
	 */
	private static final String INTEGER 	= "[+-]?\\d+";
	private static final String HEXADEC 	= "^(0x|0X|#).[0-9A-Fa-f]+";
	private static final String FLOATING 	= "[+-]?((\\d+\\.?\\d*)|(\\.\\d+))";

	/**
	 * Returns true if the given String can be parsed as an integer number.
	 * @since 1.0.0
	 */
	public static boolean isInteger(String str)
	{
		return str.matches(INTEGER);
	}
	
	/**
	 * Returns true if the given String can be parsed as an hexadecimal number.
	 * @since 1.0.0
	 */
	public static boolean isHexadec(String str)
	{
		return str.matches(HEXADEC);
	}
	
	/**
	 * Returns true if the given String can be parsed as a floating point number.
	 * @since 1.0.0
	 */
	public static boolean isFloating(String str)
	{
		return str.matches(FLOATING);
	}
	
	/**
	 * Parse the given String to a double primitive.<br>
	 * @return -1.0 if an error occurs.
	 * @since 1.0.0
	 */
	public static double toDouble(String v) 
	{
		if (isFloating(v))
		{
			return Double.parseDouble(v);
		}
		else 
		{
			return -1.0;
		}
	}
	
	/**
	 * Replaces string representation of the given integer from the back of the given string.
	 * @since 1.0.0
	 */
	public static String fillWithNumber(String filler, int i) 
	{
		String number = String.valueOf(i);
		return filler.substring(number.length()) + number;
	}
	
	/**
	 * Returns the message with added characters until a given length has been reached. Clips the end of the string if it passes the length.
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
	 * Turn the string array into a string with the given separator, start- and end-index.
	 * @since 1.0.0
	 */
	public static String compile(String[] t, String sep, int start, int end)
	{
		String str = "";
		int i = start;
		int l = end-1;
		for (; i<=l; i++)
		{
			str = str + ((i==start)?"":sep) + t[i];
		}
		return str;
	}
	
	/**
	 * Replaces all duplicate spaces with a single space.
	 * @param line
	 * @return the given text with all duplicate spaces removed
	 * @since 1.0.0
	 */
	public static String compactSpaces(String line)
	{
		return line.trim().replaceAll("\\s{2,}", " ");
	}
	
	/**
	 * Strip the leading characters from the given string until the terminator has been found. The terminator character will not be included in the result.
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
	 * Scan characters from the start of the given string until the terminator has been found. The result will not include the terminator character.
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
