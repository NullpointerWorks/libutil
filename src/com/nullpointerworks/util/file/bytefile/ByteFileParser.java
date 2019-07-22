/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.nullpointerworks.util.Log;

public class ByteFileParser 
{
	/**
	 * 
	 */
	public static void write(String path, ByteFile file) throws IOException
	{
		write(path, file.getBytes());
	}
	
	/**
	 * 
	 */
	public static void write(String path, byte[] data) throws IOException
	{
		if (path == null)
		{
			Log.err("NullPointer: The given path string is null");
			return;
		}
		
		if (data == null)
		{
			Log.err("NullPointer: The data provided points to null");
			return;
		}
		
		FileOutputStream fos;
		fos = new FileOutputStream(path);
		fos.write(data);
		fos.close();
		fos=null;
	}
	
	/**
	 * @throws IOException 
	 */
	public static ByteFile file(String path) throws IOException
	{
		if (path==null)
		{
			Log.err("NullPointer: The given path string is null");
			return new ByteFile();
		}
		final File f = new File(path);
	    final InputStream is = new DataInputStream(new FileInputStream(f));
		return stream(is);
	}
	
	/**
	 * 
	 */
	public static ByteFile stream(InputStream is)
	{
		if (is==null)
		{
			Log.err("NullPointer: The inputstream is null");
			return new ByteFile();
		}
		
		ByteFile bf = new ByteFile();
		try 
		{
			byte[] byteChunk = new byte[4096];
			int n;
			while ( (n = is.read(byteChunk)) > 0 )
			{
				bf.addBytes(byteChunk, 0, n);
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (is != null) 
			{ 
				try 
				{
					is.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
			}
			is = null;
		}
		return bf;
	}
}
