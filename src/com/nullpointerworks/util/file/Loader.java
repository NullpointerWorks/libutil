package com.nullpointerworks.util.file;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Loader 
{
	public static URL getResource(String path)
	{
		URL url = null;
		try 
		{
			url = new File(path).toURI().toURL();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		return url;
	}
	
	public static InputStream getResourceAsStream(String path)
	{
		InputStream is = null;
		try 
		{
			File f = new File(path);
			is = new DataInputStream(new FileInputStream(f));
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return is;
	}
}
