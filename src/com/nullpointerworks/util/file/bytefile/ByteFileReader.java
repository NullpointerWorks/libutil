/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

/**
 * A wrapper class to help read the bytes stores in an 8-bit file generated by the {@code ByteFileParser} class.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ByteFileReader
{
	private ByteFile file = null;
	private int stride = 0;
	private int size = 0;
	
	/**
	 * Creates a new {@code ByteFileReader} object that scans the data in the given {@code ByteFile} object.
	 * @param file - the {@code ByteFile} to read
	 * @since 1.0.0
	 */
	public ByteFileReader(ByteFile file)
	{
		setFile(file);
	}
	
	/**
	 * Make the {@code ByteFileReader} object that scans the data in the given {@code ByteFile} object.
	 * @param file - the {@code ByteFile} to read
	 * @since 1.0.0
	 */
	public void setFile(ByteFile file)
	{
		resetStride();
		this.file=file;
		size = file.getBytes().length;
	}
	
	/**
	 * Resets the scan marker back to the beginning.
	 * @since 1.0.0
	 */
	public void resetStride()
	{
		stride = 0;
	}
	
	/**
	 * Returns the current length of the scanning stride.
	 * @return the current length of the scanning stride
	 * @since 1.0.0
	 */
	public int getStride()
	{
		return stride;
	}
	
	/**
	 * Returns the total length of the {@code ByteFile} object that's being read.
	 * @return the total length of the {@code ByteFile} object that's being read
	 * @since 1.0.0
	 */
	public int getSize()
	{
		return size;
	}
	
	/**
	 * Parses a number of available bytes in the {@code ByteFile} and returns it as an primitive-type array. The bytes returned are counted from the position of the reading stride. 
	 * @param amount - the amount of bytes to read
	 * @return an array of bytes read from the {@code ByteFile}
	 * @throws EndOfFileException when the end of the file has been reached before the end of this method
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
	 * Returns the next byte to be read from the {@code ByteFile}.
	 * @return the next byte to be read from the {@code ByteFile}
	 * @throws EndOfFileException when the end of the file has been reached before the end of this method
	 * @since 1.0.0
	 */
	public byte getByte() throws EndOfFileException
	{
		if (stride >= size) throw new EndOfFileException(size - stride);
		return file.getByte(stride++);
	}
	
	/**
	 * Returns the next two bytes to be read from the {@code ByteFile} as a {@code short} primitive type.
	 * @return the next two bytes to be read from the {@code ByteFile} as a {@code short} primitive type
	 * @throws EndOfFileException when the end of the file has been reached before the end of this method
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
	 * Returns the next two bytes to be read from the {@code ByteFile} as a {@code integer} primitive type.
	 * @return the next two bytes to be read from the {@code ByteFile} as a {@code integer} primitive type
	 * @throws EndOfFileException when the end of the file has been reached before the end of this method
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
	 * Returns the next two bytes to be read from the {@code ByteFile} as a {@code long} primitive type.
	 * @return the next two bytes to be read from the {@code ByteFile} as a {@code long} primitive type
	 * @throws EndOfFileException when the end of the file has been reached before the end of this method
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
	 * Returns {@code true} of the reading stride has reached the end of the file.
	 * @return {@code true} of the reading stride has reached the end of the file
	 * @since 1.0.0
	 */
	public boolean reachedEOF()
	{
		return stride >= size;
	}
	
	/**
	 * Clears the {@code ByteFile} content and removes its reference from this {@code ByteFileReader} object.
	 * @since 1.0.0
	 */
	public void free()
	{
		file.free();
		file=null;
	}
	
	private final short toShort(byte a, byte b)
	{
		return (short) ( ((a<<8)) | (b&0xFF) );
	}
		
	private final int toInt(byte a, byte b,byte c,byte d)
	{
		return (int)( (a<<24) | (b<<16) | (c<<8) | (d&0xFF) );
	}
	
	private final long toLong(byte a, byte b,byte c,byte d, byte e, byte f,byte g,byte h)
	{
		return (long)( (a<<56) | (b<<48) | (c<<40) | (d<<32) | (e<<24) | (f<<16) | (g<<8) | (h&0xFF) );
	}
}
