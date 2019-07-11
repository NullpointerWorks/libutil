package com.nullpointerworks.util;

public class StringUtil 
{
	public static final boolean NO_TRIM = false;
	public static final boolean TRIM 	= true;
	
	/**
	 * Returns an array of String objects containing the parts separated 
	 * with the given identifier.
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
	 * Returns an array of String objects containing the parts separated 
	 * with the given identifier.
	 */
	public static String[] tokenize(String line, String sep)
	{
		return tokenize(line,sep,true);
	}
	
	/**
	 * Returns a reference free clone of the given array
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
	 */
	public static boolean isInteger(String str)
	{
		return str.matches(INTEGER);
	}
	
	/**
	 * Returns true if the given String can be parsed as an hexadecimal number.
	 */
	public static boolean isHexadec(String str)
	{
		return str.matches(HEXADEC);
	}
	
	/**
	 * Returns true if the given String can be parsed as a floating point number.
	 */
	public static boolean isFloating(String str)
	{
		return str.matches(FLOATING);
	}
	
	/**
	 * Parse the given String to a double primitive.<br>
	 * Returns -1.0 if an error occurs.
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
	 * Swap the extension of the given string with another.
	 */
	public static String swapExtension(String fileName, String newExt) 
	{
		String[] tok = fileName.split("\\.");
		String res = StringUtil.compile(tok, ".", 0, tok.length-1);
		return addFileExtension(res, newExt);
	}
	
	/**
	 * Parses a file extension behind the given text if there is no file extension added.
	 */
	public static String getFileExtension(String str)
	{
		String[] tokens = str.split("\\.");
		String text = tokens[tokens.length-1];
		return text;
	}
	
	/**
	 * Parses a file extension behind the given text if there is no file extension added.
	 */
	public static String addFileExtension(String text, String pend)
	{
		if (text.endsWith("."))
		{
			text = text+pend;
		}
		
		if (!text.endsWith( "."+pend ))
		{
			text = text+"."+pend;
		}
		
		return text;
	}
	
	/**
	 * Replaces string representation of the given integer from the back of the given string.
	 */
	public static String fillWithNumber(String filler, int i) 
	{
		String number = String.valueOf(i);
		return filler.substring(number.length()) + number;
	}
	
	/**
	 * Returns the message with added characters until a given length has been reached. <br>
	 * Clips the end of the string if it passes the length.
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
	 */
	public static String compactSpaces(String line)
	{
		return line.trim().replaceAll("\\s{2,}", " ");
	}
	
	/**
	 * Strip the leading characters from the given string until the terminator has been found.<br>
	 * The terminator character will not be included in the result.
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
	 * Scan characters from the start of the given string until the terminator has been found.<br>
	 * The result will not include the terminator character.
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
