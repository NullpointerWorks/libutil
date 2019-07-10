package com.nullpointerworks.util.file.textfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.file.Loader;
import com.nullpointerworks.util.file.bytefile.ByteFile;
import com.nullpointerworks.util.file.bytefile.ByteFileParser;

public class TextFileParser 
{
	/**
	 * Save text file to disc. Set overwrite to true to delete and rewrite the file.
	 * @throws IOException 
	 */
	public static boolean write(String path, TextFile file, boolean overwrite) throws IOException
	{
		if (overwrite) delete(path);
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save text file to disc.
	 * @throws IOException 
	 */
	public static boolean write(String path, TextFile file) throws IOException
	{
		return write(path,file.getLines(),file.getEncoding());
	}
	
	/**
	 * Save the given array of strings to a file
	 * @throws IOException 
	 */
	public static boolean write(String path, String[] lines, final String encoding) throws IOException
	{
		if (path==null)
		{
			Log.err("TextFileParser: The given path string is null");
			return false;
		}
		
		// test encoding
		String enc = "UTF16";
		switch(encoding)
		{
		case "UTF8":
		case "UTF16":
		case "UTF32": 
			enc = encoding;
			break;
		default:
			Log.err("TextFileParser: Invalid encoding. Defaulting to UTF16");
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
	 * 
	 */
	public static TextFile file(String path)
	{
		FileReader fr;
		BufferedReader br = null;
		File f = new File(path);
		TextFile tf = new TextFile();
		tf.setName(f.getName());
		
		try 
		{
			fr = new FileReader(path);
			br = new BufferedReader(fr);
		} 
		catch (FileNotFoundException e) 
		{
			boolean b = create(path);
			if (b)
			{
				return file(path);
			}
		}
		
		// check if the writer exists
		if (br == null)
		{
			Log.err("TextFileParser: Filereader is null. Can't load file.");
			return tf;
		}
		
		String line;
		try 
		{
			while ( (line = br.readLine())!=null )
			{
				tf.addLine( line );
			}
			br.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return tf;
	}
	
	/**
	 * 
	 */
	public static TextFile resource(String path)
	{
		if (path==null)
		{
			Log.err("TextFileParser: The given path string is null");
			return new TextFile();
		}
		InputStream is = Loader.getResourceAsStream(path);
		return read(is);
	}

	/**
	 * 
	 */
	public static TextFile stream(InputStream is)
	{
		if (is==null)
		{
			Log.err("TextFileParser: The given inputstream is null");
		}
		return read(is);
	}
	
	/**
	 * 
	 */
	public static TextFile read(InputStream is)
	{
		if (is==null)
		{
			Log.err("TextFileParser: Reader inputstream is null.");
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
	 * create the file and directory for the given path. 
	 * returns true if creation succeeded, false otherwise
	 */
	public static boolean create(String path)
	{
		File f = new File(path);
		if (f.exists()) return false;
		try 
		{
			if (f.getParentFile()!=null) // make directories if they don't exist
				f.getParentFile().mkdirs();
			f.createNewFile();
			return true;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Deletes the given file or directory. A directory must be empty before it can be deleted.
	 */
	public static boolean delete(String path)
	{
		File f = new File(path);
		if (f.exists()) return false;
		return f.delete();
	}
}
