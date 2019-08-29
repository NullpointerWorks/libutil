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
 * Contains static members to assist in file reading, writing and streaming. Writing a text file can be done with an {@code String[]} object or with an instance of a {@code TextFile} object.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class TextFileParser 
{
	/**
	 * Save text file to disc with an override safeguard. Set overwrite to true to delete and rewrite the file. This method does not use the {@code getName()} method of the {@code TextFile} to determine its writing path.
	 * @param path - the path to write to
	 * @param file - the {@code TextFile} to write
	 * @param override - set {@code true} to enable a file to be over written
	 * @return {@code true} if the writing went successful
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static boolean write(String path, TextFile file, boolean override) throws IOException
	{
		if (override) delete(path);
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save text file to disc. If the file already exists, it will be unsafely overridden. This method does not use the {@code getName()} method of the {@code TextFile} to determine its writing path.
	 * @param path - the path to write to
	 * @param file - the {@code TextFile} to write
	 * @return {@code true} if the writing went successful
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static boolean write(String path, TextFile file) throws IOException
	{
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save the given array of strings to a file. If the file already exists, it will be unsafely overridden. Returns {@code true} if the writing went successful.
	 * @param path - the path to write to
	 * @param lines - the array of {@code Strings} to write
	 * @param encoding - 
	 * @return {@code true} if the writing went successful
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public static boolean write(String path, String[] lines, final String encoding) throws IOException
	{
		if (path==null)
		{
			Log.err("TextFileParser: The given path string is null. Cancelling.");
			return false;
		}
		
		if (lines==null)
		{
			Log.err("TextFileParser: The given lines array is null. Cancelling.");
			return false;
		}
		
		if (encoding==null)
		{
			Log.err("TextFileParser: The given encoding is null. Cancelling.");
			return false;
		}
		
		String enc;
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
			Log.err("TextFileParser: Invalid encoding. Defaulting to UTF-16.");
			enc = "UTF-16";
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
	 * Returns the content of the given file as text in a new instance of the {@code TextFile} object. If an {@code IOException} occurs during reading, the text that was read is returned in the returned {@code TextFile}.
	 * @param path - the file path
	 * @return the content of the given file as text in a new instance of the {@code TextFile} object
	 * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading
	 * @since 1.0.0
	 */
	public static TextFile file(String path) throws FileNotFoundException
	{
		final File f = new File(path);
	    final InputStream is = new DataInputStream(new FileInputStream(f));
		return stream(is);
	}
	
	/**
	 * Returns the content of the given file as text in a new instance of the {@code TextFile} object. If an {@code IOException} occurs during reading, the text that was read is returned in the returned {@code TextFile}.
	 * @param is - the file {@code InputStream} object
	 * @return the content of the given file as text in a new instance of the {@code TextFile} object
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
	 * Deletes the given file or directory. A directory must be empty before it can be deleted. Returns {@code true} if the deletion was successful.
	 * @param path - the path to be deleted
	 * @return {@code true} if the deletion was successful
	 * @since 1.0.0
	 */
	public static boolean delete(String path)
	{
		File f = new File(path);
		if (f.exists()) return false;
		return f.delete();
	}
}
