/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.pattern.Nullable;

/**
 * A byte array container to be used for reading and writing raw binary files. 
 * @since 1.0.0
 */
public class ByteFile implements Nullable
{
	private List<Byte> data;
	private String name = "";
	
	/**
	 * Creates a new empty byte file.
	 * @since 1.0.0
	 */
	public ByteFile() 
	{
		this(null);
	}
	
	/**
	 * Creates a new byte file object initialized with the given {@code byte} array.
	 * @param data - the data to initialize with
	 * @since 1.0.0
	 */
	public ByteFile(byte[] data) 
	{
		this(data,"");
	}
	
	/**
	 * Creates a new byte file object initialized with the given {@code byte} array and a name for the file.
	 * @param data - the initializing data
	 * @param name - the name of the file without an extension
	 * @since 1.0.0
	 */
	public ByteFile(byte[] data, String name) 
	{
		setName(name);
		this.data = new ArrayList<Byte>();
		if (data!=null) addBytes(data);
	}
	
	/**
	 * Returns the name of the file.
	 * @return the name of the file
	 * @since 1.0.0
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Set a filename for this {@code ByteFile} object.
	 * @param name - the name of the file without an extension
	 * @since 1.0.0
	 */
	public void setName(String name) 
	{
		if (name!=null) this.name = name;
	}
	
	/**
	 * Removes all bytes from the file array.
	 * @since 1.0.0
	 */
	public void clear()
	{
		data.clear();
	}
	
	/**
	 * Append an array of bytes on the end of the existing byte array. 
	 * @param data - the array to append
	 * @since 1.0.0
	 */
	public void addBytes(byte[] data) 
	{
		for (byte b : data)
		{
			this.data.add(b);
		}
	}
	
	/**
	 * Append a subset of bytes from an array ranging from the offset for a given length.
	 * @param chunk - the byte array to read from
	 * @param offset - 
	 * @param length - 
	 * @since 1.0.0
	 */
	public void addBytes(byte[] chunk, int offset, int length) 
	{
		offset = (offset<0)?0:offset;
		length = ((chunk.length-offset) >= length)?(chunk.length-offset-1):length;
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
			Log.err("ArrayIndexOutOfBounds: The index provided ("+index+") is out of the bounds.");
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
