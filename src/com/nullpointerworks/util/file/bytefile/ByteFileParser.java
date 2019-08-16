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

/**
 * Contains static members to write and read a {@code ByteFile} object.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ByteFileParser 
{
	/**
	 * Write the content of the given {@code ByteFile} to local storage.
	 * @param path - the path to write to
	 * @param file - the {@code ByteFile} object to write
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static void write(String path, ByteFile file) throws IOException
	{
		write(path, file.getBytes());
	}
	
	/**
	 * Write the content of the given {@code byte[]} to local storage.
	 * @param path - the path to write to
	 * @param data - the {@code byte[]} to write
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static void write(String path, byte[] data) throws IOException
	{
		if (path == null)
		{
			Log.err("ByteFileParser nullpointer: The given path string is null");
			return;
		}
		
		if (data == null)
		{
			Log.err("ByteFileParser nullpointer: The data provided points to null");
			return;
		}
		
		FileOutputStream fos;
		fos = new FileOutputStream(path);
		fos.write(data);
		fos.close();
		fos=null;
	}
	
	/**
	 * Load the bytes of a file at the specified path into a {@code ByteFile} object.
	 * @param path - the path to the file
	 * @return a {@code ByteFile} with the file content
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static ByteFile file(String path) throws IOException
	{
		if (path==null)
		{
			Log.err("ByteFileParser nullpointer: The given path string is null");
			return new ByteFile();
		}
		final File f = new File(path);
	    final InputStream is = new DataInputStream(new FileInputStream(f));
		return stream(is);
	}
	
	/**
	 * Load the bytes of a file from the given {@code InputStream} into a {@code ByteFile} object. This method reads byte-for-byte to get the highest reading accuracy. 
	 * @param is - the {@code InputStream} to read from
	 * @return a {@code ByteFile object with the content of the stream
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static ByteFile stream(InputStream is) throws IOException
	{
		if (is==null)
		{
			Log.err("ByteFileParser nullpointer: The inputstream is null");
			return new ByteFile();
		}
		
		ByteFile bf = new ByteFile();
		int n;
		
		while ( (n=is.read()) >= 0 )
		{
			bf.addByte( (byte)(n&0xff) );
		}
		
		if (is != null) 
		{ 
			is.close();
		}
		
		is = null;
		return bf;
	}
}
