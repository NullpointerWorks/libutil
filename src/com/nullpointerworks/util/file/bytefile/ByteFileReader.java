package com.nullpointerworks.util.file.bytefile;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.pattern.Nullable;

public class ByteFileReader implements Nullable 
{
	private ByteFile file = null;
	private int stride = 0;
	private int size = 0;
	
	public ByteFileReader()
	{
		
	}
	
	ByteFileReader(ByteFile file)
	{
		isNull(false);
		setFile(file);
	}
	
	public void setFile(ByteFile file)
	{
		this.file=file;
		size = file.getBytes().length;
	}
	
	private byte error() 
	{
		Log.err("Read Error: ByteFile reader has reached the end of the file.");
		return -1;
	}
	
	public void resetStride()
	{
		stride = 0;
	}
	
	public int getStride()
	{
		return stride;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public byte[] getBytes(int amount)
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
	
	public byte getByte()
	{
		if (stride >= size) return error();
		return file.getByte(stride++);
	}
	
	public short getShort()
	{
		if ((stride+2) >= size) return error();

		int b1 = getByte() &0xFF;
		int b2 = getByte() &0xFF;
		
		return (short)( (b2<<8) | b1);
	}
	
	public int getInteger()
	{
		if ((stride+4) >= size) return error();
		
		int s1 = getShort() &0xFF;
		int s2 = getShort() &0xFF;
		
		return ((s2<<16) | (s1));
	}
	
	public boolean reachedEOF()
	{
		return stride >= size;
	}
	
	public void free()
	{
		file.free();
		file=null;
	}
	
	// =====================================
	
	private boolean isnull = true;
	@Override
	public boolean isNull() {return isnull;}
	@Override
	public void isNull(boolean n) {isnull=n;}
}
