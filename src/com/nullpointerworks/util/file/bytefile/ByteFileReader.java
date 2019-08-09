/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import com.nullpointerworks.util.pattern.Nullable;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ByteFileReader implements Nullable 
{
	private ByteFile file = null;
	private int stride = 0;
	private int size = 0;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public ByteFileReader() { }
	
	/**
	 * 
	 * @param 
	 * @since 1.0.0
	 */
	public ByteFileReader(ByteFile file)
	{
		setFile(file);
	}
	
	/**
	 * 
	 * @param 
	 * @since 1.0.0
	 */
	public void setFile(ByteFile file)
	{
		isNull(false);
		this.file=file;
		size = file.getBytes().length;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void resetStride()
	{
		stride = 0;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getStride()
	{
		return stride;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * 
	 * @param 
	 * @since 1.0.0
	 */
	public byte[] getBytes(int amount) throws EndOfFileException
	{
		byte[] b = new byte[amount];
		
		int i=0;
		while(i<amount)
		{
			b[i] = getByte();
			i++;
		}
		return b;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public byte getByte() throws EndOfFileException
	{
		if (stride >= size) throw new EndOfFileException(size - stride);
		return file.getByte(stride++);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public short getShort() throws EndOfFileException
	{
		if ((stride+1) >= size) throw new EndOfFileException(size - stride);
		byte b1 = getByte();
		byte b2 = getByte();
		return toShort(b1,b2);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getInteger() throws EndOfFileException
	{
		if ((stride+3) >= size) throw new EndOfFileException(size - stride);
		byte b1 = getByte();
		byte b2 = getByte();
		byte b3 = getByte();
		byte b4 = getByte();
		return toInt(b1,b2,b3,b4);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public long getLong() throws EndOfFileException
	{
		if ((stride+7) >= size) throw new EndOfFileException(size - stride);
		byte b1 = getByte();
		byte b2 = getByte();
		byte b3 = getByte();
		byte b4 = getByte();
		byte b5 = getByte();
		byte b6 = getByte();
		byte b7 = getByte();
		byte b8 = getByte();
		return toLong(b1,b2,b3,b4,b5,b6,b7,b8);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public boolean reachedEOF()
	{
		return stride >= size;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void free()
	{
		file.free();
		file=null;
	}
	
	private final short toShort(byte a, byte b)
	{
		return (short) ( a<<8 | b );
	}
		
	private final int toInt(byte a, byte b,byte c,byte d)
	{
		return (int)( a<<24 | b<<16 | c<<8 | d );
	}
	
	private final long toLong(byte a, byte b,byte c,byte d, byte e, byte f,byte g,byte h)
	{
		return (long)( a<<56 | b<<48 | c<<40 | d<<32 | e<<24 | f<<16 | g<<8 | h );
	}
	
	private boolean isnull = true;
	@Override
	public boolean isNull() {return isnull;}
	
	@Override
	public void isNull(boolean n) {isnull=n;}
}
