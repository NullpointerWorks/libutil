/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.textfile;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.file.Encoding;
import com.nullpointerworks.util.pack.Array;

public class TextFile
{
	private String encoding = Encoding.UTF16;
	private String name = "";
	private Array<String> lines;
	
	public TextFile()
	{
		lines = new Array<String>();
	}
	
	public void clear()
	{
		lines.clear();
	}
	
	public String getEncoding() 
	{
		return encoding;
	}
	
	public void setEncoding(String encoding) 
	{
		this.encoding = encoding;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public void addLine(String line)
	{
		lines.add(line);
	}
	
	public String getLine(int index)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error retrieving line from TextFile. Returning an empty line instead.");
			return "";
		}
		return lines.get(index);
	}
	
	public void setLine(int index, String line)
	{
		if (index < 0 || index >= lines.size()) 
		{
			Log.err("Error overwriting line in TextFile. Cancelling edit.");
			return;
		}
		lines.set(index, line);
	}
	
	public String[] getLines() 
	{
		return lines.toArray(new String[0]);
	}

	public int getSize() 
	{
		return lines.size();
	}
}
