/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.textfile;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.file.bytefile.ByteFile;
import com.nullpointerworks.util.file.bytefile.ByteFileParser;

/**
 * 
 * @since 1.0.0
 */
public class TextFileParser 
{
	/**
	 * Save text file to disc. Set overwrite to true to delete and rewrite the file.
	 * @throws IOException 
	 * @since 1.0.0
	 */
	public static boolean write(String path, TextFile file, boolean overwrite) throws IOException
	{
		if (overwrite) delete(path);
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save text file to disc.
	 * @throws IOException 
	 * @since 1.0.0
	 */
	public static boolean write(String path, TextFile file) throws IOException
	{
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save the given array of strings to a file
	 * @throws IOException 
	 * @since 1.0.0
	 */
	public static boolean write(String path, String[] lines, final String encoding) throws IOException
	{
		if (path==null)
		{
			Log.err("TextFileParser: The given path string is null");
			return false;
		}
		
		// test encoding
		String enc = "UTF-16";
		switch(encoding)
		{
		case "UTF8":
		case "UTF16":
		case "UTF32": 
			enc = "UTF-"+encoding.substring(3);
			break;
		case "UTF-8":
		case "UTF-16":
		case "UTF-32": 
			enc = encoding;
			break;
		default:
			Log.err("TextFileParser: Invalid encoding. Defaulting to UTF-16");
			return false;
		}

		// write to file
		ByteFile bf = new ByteFile();
		int i=0;
		int l=lines.length;
		while (i<l)
		{
			String line = lines[i];
			bf.addBytes( (line).getBytes(enc) );
			i++;
		}
		
		ByteFileParser.write(path, bf);
		return true;
	}
	
	/**
	 * @throws FileNotFoundException 
	 * @since 1.0.0
	 */
	public static TextFile file(String path) throws FileNotFoundException
	{
		final File f = new File(path);
	    final InputStream is = new DataInputStream(new FileInputStream(f));
		return stream(is);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static TextFile stream(InputStream is)
	{
		if (is==null)
		{
			Log.err("TextFileParser: Reader inputstream is null.");
			return new TextFile();
		}
		
		InputStreamReader isr = new InputStreamReader( is );
		BufferedReader br = new BufferedReader(isr);
		TextFile tf = new TextFile();
		
		String line;
		try 
		{
			while ((line = br.readLine()) != null) 
			{
				tf.addLine( line );
			}
			br.close();
			isr.close();
			is.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tf;
	}
	
	/**
	 * Deletes the given file or directory. A directory must be empty before it can be deleted.
	 * @since 1.0.0
	 */
	public static boolean delete(String path)
	{
		File f = new File(path);
		if (f.exists()) return false;
		return f.delete();
	}
}
