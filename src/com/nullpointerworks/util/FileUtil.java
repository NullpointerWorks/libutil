/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Contains a collection of static methods to help with file manipulation. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class FileUtil 
{
	/**
	 * Returns the path to the source code of the specified class. This method returns an IP address when the application is called over LAN.
	 * @param clazz - the class to request its source from
	 * @return a String with the URL to the class
	 * @since 1.0.0
	 */
	public static String getSourceCodePath(Class<?> clazz)
	{
		String fpath = clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
		fpath = fpath.replace("%20", " ");
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
	 * Returns {@code true} if the given path points to an existing file.
	 * @param path - the path to the file
	 * @return {@code true} if the given path points to an existing file
	 * @since 1.0.0
	 */
	public static boolean exists(String path)
	{
		if ( !path.matches("(\\.[a-z]+)$") ) return false;
		File f = new File( path );
		return f.exists();
	}
	
	/**
	 * Returns the name of the file or folder of the specified path.
	 * @param path - the path to test
	 * @return the name of the file or folder of the specified path
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
	 * Copies the content of the supplied directory or file object to another target location specified.
	 * @param sourceDir - the source to copy
	 * @param targetDir - the copy destination
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
	 * Copies the content of the supplied file object to another target location specified.
	 * @param sourceDir - the source file to copy
	 * @param targetDir - the file copy destination
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
	 * Returns the provided string with a filename with the desired extension.
	 * @param fileName - the filename or path with a file
	 * @param newExt - the desired extension
	 * @return the provided string with a filename with the desired extension
	 * @since 1.0.0
	 */
	public static String swapExtension(String fileName, String newExt) 
	{
		String[] tok = fileName.split("\\.");
		String res = StringUtil.compile(tok, ".", 0, tok.length-2);
		return addFileExtension(res, newExt);
	}
	
	/**
	 * Returns the extension of the specified file name.
	 * @param file - the filename
	 * @return the extension of the specified file name
	 * @since 1.0.0
	 */
	public static String getFileExtension(String file)
	{
		String[] tokens = file.split("\\.");
		if (tokens.length < 2) return null;
		return tokens[tokens.length-1];
	}
	
	/**
	 * Adds a file extension to the provided name. If the given name already has a file extension, then it is swapped for the specified extension. Returns {@code <name>.<ext>}.
	 * @param name - the name to add the extension to
	 * @param ext - the extension to add
	 * @return {@code <name>.<ext>}
	 * @since 1.0.0
	 */
	public static String addFileExtension(String name, String ext)
	{
		if (name.endsWith(".")) return name+ext;
		if (!name.endsWith( "."+ext )) return name+"."+ext;
		return swapExtension(name, ext);
	}
}
