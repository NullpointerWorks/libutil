/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class FileUtil 
{
	/**
	 * Returns true if the given path points to an existing file
	 * @since 1.0.0
	 */
	public static boolean exists(String path)
	{
		if ( !path.matches("(\\.[a-z]+)$") )
		{
			return false;
		}
		
		File f = new File( path );
		return f.exists();
	}
	
	/**
	 * Returns the path to the source code of this executable jar. When placed over LAN, <br>
	 * @return a String with the URL
	 * @since 1.0.0
	 */
	public static String getSourceCodePath(Class<?> clazz)
	{
		String fpath = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
		fpath = fpath.replace("%20", " ");
		fpath = fpath.substring(1);
		
		if ( !fpath.endsWith(".jar") ) return fpath;
		
		String apath = "";
		String[] ts = fpath.split("/");
		int i=0;
		int l=ts.length-1;
		for (;i<l;i++)
		{
			String t = ts[i];
			apath += t+"/";
		}
		return apath;
	}
	
	/**
	 * Returns the last portion of the given path that indicates the name of a file
	 * @since 1.0.0
	 */
	public static String getFileNameFromPath(String path)
	{
		path = path.replace("\\", "/");
		String[] tok = path.split("/");
		String fname = tok[tok.length-1];
		return fname;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void copyDirectory(File sourceDir , File targetDir) throws IOException 
	{
		if (sourceDir.isDirectory()) 
		{
            if (!targetDir.exists()) 
            {
                targetDir.mkdir();
            }

            String[] children = sourceDir.list();
            for (int i=0; i<children.length; i++) 
            {
                copyDirectory(new File(sourceDir, children[i]), new File(targetDir, children[i]));
            }
        } 
        else 
        {
            copyFile(sourceDir, targetDir);
        }
    }
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void copyFile(File sourceFile, File destFile) throws IOException 
	{
	    if (!sourceFile.exists()) 
	    {
	        return;
	    }
	    
	    if (!destFile.exists()) 
	    {
	        destFile.createNewFile();
	    }
	    
	    InputStream in = new FileInputStream(sourceFile);
        OutputStream out = new FileOutputStream(destFile);
        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) 
        {
            out.write(buf, 0, len);
        }
        in.close();
        out.close();
	}
	
	/**
	 * Creates a file and directory structure at the given path. 
	 * @param path - the path with the file location
	 * @return {@code true} if creation succeeded, {@code false} otherwise
	 * @since 1.0.0
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
	 * Swap the extension of the given string with another.
	 * @since 1.0.0
	 */
	public static String swapExtension(String fileName, String newExt) 
	{
		String[] tok = fileName.split("\\.");
		String res = StringUtil.compile(tok, ".", 0, tok.length-1);
		return addFileExtension(res, newExt);
	}
	
	/**
	 * Parses a file extension behind the given text if there is no file extension added.
	 * @since 1.0.0
	 */
	public static String getFileExtension(String str)
	{
		String[] tokens = str.split("\\.");
		if (tokens.length < 2) return null;
		String text = tokens[tokens.length-1];
		return text;
	}
	
	/**
	 * Parses a file extension behind the given text if there is no file extension added.
	 * @since 1.0.0
	 */
	public static String addFileExtension(String text, String pend)
	{
		if (text.endsWith("."))
		{
			text = text+pend;
		}
		
		if (!text.endsWith( "."+pend ))
		{
			text = text+"."+pend;
		}
		
		return text;
	}
}
