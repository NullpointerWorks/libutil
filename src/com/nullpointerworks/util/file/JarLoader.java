/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.nullpointerworks.util.pack.node.Node;

public class JarLoader
{
	/**
	 * Unpacking a jar file.
	 */
	public Node<String> loadJarFile(String path)
	{
		try
		{
			Node<String> classNames		= new Node<String>("file");
			ZipInputStream zip 			= new ZipInputStream( new FileInputStream( path ) );
			ZipEntry entry;
			while( (entry=zip.getNextEntry()) != null)
			{
				if (!entry.isDirectory())
				if (entry.getName().endsWith(".class")) 
			    {
					onClassFile(entry, classNames);
			    }
			}
			zip.close();
			return classNames;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new Node<String>();
	}
	
	private void onClassFile(ZipEntry entry, Node<String> classNames)
	{
		String classentry = entry.getName();
		classentry = classentry.substring(0,classentry.length()-6); // remove ".class"
        String[] parts = classentry.split("/");
        String classpath = "";
        String className = parts[parts.length-1];
        
        for(int i=0; i<parts.length;i++)
        	classpath += parts[i]+((i<parts.length-1)?".":"");
        classNames.createPath(classpath, className);
	}
}
