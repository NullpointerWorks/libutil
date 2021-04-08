/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.file.textfile;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.file.Encoding;
import com.nullpointerworks.util.pack.Array;

/**
 * Contains the information for a text file consisting of a list of {@code String} objects that represent each line of text. The default encoding is set to "UTF-16" but can be changed if needed.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 * @see Encoding
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
	 * Returns the encoding string.
	 * @return the encoding string
	 * @since 1.0.0
	 */
	public String getEncoding() 
	{
		return encoding;
	}
	
	/**
	 * Set the file encoding when writing to disc is desired. The JVM default encoding is {@code "UTF-16"}.
	 * @param encoding - the encoding string, like {@code "UTF-8"}, {@code "UTF-16"}, etc
	 * @since 1.0.0
	 * @see Encoding
	 */
	public void setEncoding(String encoding) 
	{
		this.encoding = encoding;
	}
	
	/**
	 * Returns the name of the file if available.
	 * @return the name of the file if available
	 * @since 1.0.0
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Set the name of the text file. This method is used when reading or writing to a storage device.
	 * @param name - name of the text file
	 * @since 1.0.0
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * Add a line of text to the file. This line is added to the bottom of the text.
	 * @param line - the text to place
	 * @since 1.0.0
	 */
	public void addLine(String line)
	{
		lines.add(line);
	}
	
	/**
	 * Inserts the specified {@code String} at the given index in this list. The {@code String} at the given index is shifted down the list.
	 * @param index - the specified index to place to
	 * @param line - the text to insert
	 * @since 1.0.0
	 */
	public void insertLine(int index, String line)
	{
		lines.add(index, line);
	}
	
	/**
	 * Returns the text at the given index if available, {@code null} otherwise.
	 * @param index - the index to read from
	 * @return the text at the given index if available, {@code null} otherwise
	 * @since 1.0.0
	 */
	public String getLine(int index)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error retrieving line from TextFile. Returning null.");
			return null;
		}
		return lines.get(index);
	}
	
	/**
	 * Set the text at the given index to the text provided. If the index is invalid, the edit is cancelled.
	 * @param index - the index to write to
	 * @param line - the text to write
	 * @since 1.0.0
	 */
	public void setLine(int index, String line)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error overwriting index out of bounds. Cancelling edit.");
			return;
		}
		lines.set(index, line);
	}
	
	/**
	 * Returns an array of {@code String} objects with the content of the text file.
	 * @return an array of {@code String} objects with the content of the text file 
	 * @since 1.0.0
	 */
	public String[] getLines() 
	{
		return lines.toArray(new String[0]);
	}
	
	/**
	 * Returns the amount of lines available in the text file.
	 * @return the amount of lines available in the text file
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
