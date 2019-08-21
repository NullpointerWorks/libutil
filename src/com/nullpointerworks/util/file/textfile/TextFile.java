/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.textfile;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.file.Encoding;
import com.nullpointerworks.util.pack.Array;

/**
 * A text file consisting of a list of String objects that represent each line of text.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class TextFile
{
	private String encoding = Encoding.UTF16;
	private String name = "";
	private Array<String> lines;
	
	/**
	 * Creates an empty {@code TextFile} with UTF-16 encoding.
	 * @since 1.0.0
	 */
	public TextFile()
	{
		lines = new Array<String>();
	}
	
	/**
	 * Creates an empty {@code TextFile} with the specified encoding.
	 * @param encoding - the encoding string, like {@code "UTF-8"}, {@code "UTF-16"}, etc
	 * @since 1.0.0
	 * @see Encoding
	 */
	public TextFile(final String encoding)
	{
		this();
		setEncoding(encoding);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String getEncoding() 
	{
		return encoding;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setEncoding(String encoding) 
	{
		this.encoding = encoding;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void addLine(String line)
	{
		lines.add(line);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String getLine(int index)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error retrieving line from TextFile. Returning an empty line instead.");
			return "";
		}
		return lines.get(index);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setLine(int index, String line)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error overwriting line in TextFile. Cancelling edit.");
			return;
		}
		lines.set(index, line);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String[] getLines() 
	{
		return lines.toArray(new String[0]);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getSize() 
	{
		return lines.size();
	}
	
	/**
	 * Removes all lines of text from the {@code TextFile}.
	 * @since 1.0.0
	 */
	public void clear()
	{
		lines.clear();
	}
	
	/**
	 * Frees all allocated memory for garbage collection and making this object unusable.
	 * @since 1.0.0
	 */
	public void free()
	{
		clear();
		name = null;
		encoding = null;
	}
}
