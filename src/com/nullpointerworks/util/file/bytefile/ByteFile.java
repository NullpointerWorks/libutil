/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import java.util.ArrayList;

import com.nullpointerworks.util.Log;

public class ByteFile
{
	private ArrayList<Byte> data;
	private String name = "";
	
	public ByteFile() 
	{
		data = new ArrayList<Byte>();
	}
	
	public ByteFile(byte[] d) 
	{
		data = new ArrayList<Byte>();
		if (d!=null)
		for (byte b : d)
		{
			data.add(b);
		}
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public void clear()
	{
		data.clear();
	}

	public void addBytes(byte[] d) 
	{
		for (byte b : d)
		{
			data.add(b);
		}
	}
	
	public void addBytes(byte[] chunk, int offset, int length) 
	{
		for (int i=offset, l=offset+length; i<l; i++)
		{
			data.add( chunk[i] );
		}
	}

	public byte[] getBytes()
	{
		int l = data.size();
		byte[] bytes = new byte[l];
		for (int i=0; i<l; i++)
		{
			bytes[i] = data.get(i);
		}
		return bytes;
	}
	
	public void addByte(byte d) 
	{
		data.add(d);
	}
	
	public byte getByte(int index)
	{
		if (index<0 || index >= data.size()) 
		{
			Log.err("ArrayIndexOutOfBounds: The index provided ("+index+") is out of the bounds of the ByteFile");
			return 0;
		}
		
		return data.get(index);
	}
	
	public ByteFileReader getReader()
	{
		return new ByteFileReader(this);
	}
	
	public void free() 
	{
		data.clear();
		data=null;
	}

	public boolean isNull()
	{
		return data.size() < 1;
	}
}
