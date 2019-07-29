/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import java.util.ArrayList;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.pattern.Nullable;

/**
 * 
 * @since 1.0.0
 */
public class ByteFile implements Nullable
{
	private ArrayList<Byte> data;
	private String name = "";
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public ByteFile() 
	{
		data = new ArrayList<Byte>();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public ByteFile(byte[] d) 
	{
		data = new ArrayList<Byte>();
		if (d!=null)
		for (byte b : d)
		{
			data.add(b);
		}
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
	public void clear()
	{
		data.clear();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void addBytes(byte[] d) 
	{
		for (byte b : d)
		{
			data.add(b);
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void addBytes(byte[] chunk, int offset, int length) 
	{
		for (int i=offset, l=offset+length; i<l; i++)
		{
			data.add( chunk[i] );
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
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
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void addByte(byte d) 
	{
		data.add(d);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public byte getByte(int index)
	{
		if (index<0 || index >= data.size()) 
		{
			Log.err("ArrayIndexOutOfBounds: The index provided ("+index+") is out of the bounds of the ByteFile");
			return 0;
		}
		
		return data.get(index);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public ByteFileReader getReader()
	{
		return new ByteFileReader(this);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void free() 
	{
		data.clear();
		data=null;
	}
	
	@Override
	public boolean isNull()
	{
		return data.size() < 1;
	}
}
