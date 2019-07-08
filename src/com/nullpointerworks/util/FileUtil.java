package com.nullpointerworks.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil 
{
	/**
	 * Returns true if the given path points to an existing file
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
	 * it returns a String with the URL
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
	 * Returns the last potion of the given path that indicates the name of a file
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
}
