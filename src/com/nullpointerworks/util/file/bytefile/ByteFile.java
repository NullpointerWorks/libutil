/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.util.Log;

/**
 * A byte array container to be used for reading and writing raw binary files. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ByteFile
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
			addByte(b);
		}
	}
	
	/**
	 * Append a subset of bytes from an array ranging from the offset for a given length.
	 * @param chunk - the byte array to read from
	 * @param offset - starting element in the array
	 * @param length - amount of element to add from the start
	 * @since 1.0.0
	 */
	public void addBytes(byte[] chunk, int offset, int length) 
	{
		offset = (offset<0)?0:offset;
		length = ((chunk.length-offset) >= length)?(chunk.length-offset-1):length;
		for (int i=offset, l=offset+length; i<l; i++)
		{
			addByte( chunk[i] );
		}
	}
	
	/**
	 * Returns all the bytes stored in the file. The returned array is reference free.
	 * @return all the bytes stored in the file
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
	 * Places a byte of data at the end of the file array.
	 * @param data - the byte to place
	 * @since 1.0.0
	 */
	public void addByte(byte data) 
	{
		this.data.add(data);
	}
	
	/**
	 * Places a short (2 bytes) of data at the end of the file array. The MSB of the short are places first in the array.
	 * @param data - the short to place
	 * @since 1.0.1
	 */
	public void addShort(short data) 
	{
		addByte( (byte)((data>>8)&0xFF) );
		addByte( (byte)(data&0xFF) );
	}
	
	/**
	 * Places an integer (4 bytes) of data at the end of the file array. The MSB of the integer are places first in the array.
	 * @param data - the integer to place
	 * @since 1.0.1
	 */
	public void addInteger(int data) 
	{
		addByte( (byte)((data>>24)&0xFF) );
		addByte( (byte)((data>>16)&0xFF) );
		addByte( (byte)((data>>8)&0xFF) );
		addByte( (byte)(data&0xFF) );
	}
	
	/**
	 * Places a long (8 bytes) of data at the end of the file array. The MSB of the long are places first in the array.
	 * @param data - the long to place
	 * @since 1.0.1
	 */
	public void addLong(long data) 
	{
		addByte( (byte)((data>>56)&0xFF) );
		addByte( (byte)((data>>48)&0xFF) );
		addByte( (byte)((data>>40)&0xFF) );
		addByte( (byte)((data>>32)&0xFF) );
		addByte( (byte)((data>>24)&0xFF) );
		addByte( (byte)((data>>16)&0xFF) );
		addByte( (byte)((data>>8)&0xFF) );
		addByte( (byte)(data&0xFF) );
	}
	
	/**
	 * Returns the byte in the data file at the given index. If the index is negative, or out of bounds, this method returns {@code 0}.
	 * @param index - the data array index
	 * @return the byte at the given index
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
	 * Returns a {@code ByteFileReader} to assist with reading its content.
	 * @return a {@code ByteFileReader} to assist with reading
	 * @since 1.0.0
	 */
	public ByteFileReader getReader()
	{
		return new ByteFileReader(this);
	}
	
	/**
	 * Clears and nulls the data array. After this method call no more data can be stored or read.
	 * @since 1.0.0
	 */
	public void free() 
	{
		data.clear();
		data=null;
	}
}
