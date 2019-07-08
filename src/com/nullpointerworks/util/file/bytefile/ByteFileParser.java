package com.nullpointerworks.util.file.bytefile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	 * 
	 */
	public static ByteFile file(String path)
	{
		if (path==null)
		{
			Log.err("NullPointer: The given path string is null");
			return new ByteFile();
		}
		
		Path filepath = Paths.get(path);
		byte[] data = {};
		
		try 
		{
			data = Files.readAllBytes(filepath);
		} 
		catch (NoSuchFileException e) 
		{
			Log.err("File "+path+" does not exist.");
		}
		catch (IOException e) 
		{
			Log.err("Error while reading "+path+".");
			e.printStackTrace();
		}
		
		return new ByteFile(data);
	}
	
	/**
	 * 
	 */
	public static ByteFile resource(String path)
	{
		if (path==null)
		{
			Log.err("NullPointer: The given path string is null");
			return new ByteFile();
		}
		
		// check first char for a '/'
		char c = path.charAt(0);
		if (c == '/') path = path.substring(1, path.length());
		
		URL fileurl = ClassLoader.getSystemClassLoader().getResource(path);
		
		if (fileurl==null)
		{
			Log.err("NullPointer: The resource \""+path+"\" could not be found");
			return new ByteFile();
		}
		
		byte[] data = read(fileurl);
		return new ByteFile(data);
	}
	
	/**
	 * 
	 */
	public static byte[] read(URL url)
	{
		InputStream is = null;
		try 
		{
			is = url.openStream();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return read(is);
	}
	
	/**
	 * 
	 */
	public static byte[] read(InputStream is)
	{
		if (is==null)
		{
			Log.err("NullPointer in the "+ByteFileParser.class+" inputstream reader.");
		}
		
		ByteFile bf = new ByteFile(null);
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
		
		return bf.getBytes();
	}
}
