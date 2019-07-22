/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.classloader;

import java.io.File;
import java.io.IOException;

import com.nullpointerworks.util.file.JarLoader;
import com.nullpointerworks.util.pack.node.Node;
import com.nullpointerworks.util.pack.node.Printer;

/*
 * 
 */
public class JarDirLoader extends JarLoader
{
	/**
	 * Load all jar files in a directory.
	 */
	public void loadAllJars(String path)
	{
		File directory = new File( path );
		File[] files = directory.listFiles();
		if (files==null || files.length==0) return;
		
		String name;
	    for (File f: files)
	    {
	    	name = f.getName();
	    	if ( f.exists() )
	    	if ( name.endsWith(".jar") )
	    	{
	    		out("Loading file: "+name);
	    		
	    		try
				{
	    			DynamicURLClassLoader loader = new DynamicURLClassLoader( f );
	    			Node<String> classNames = this.loadJarFile( path+"/"+name );
	    			
	    			classNames.setPrinter(new Printer<String, String>()
	    			{
						@Override
						public void onPrint(String path, String name) 
						{
							Class<?> loadedClass = loader.loadClassByPath( path ); // node path includes the name
		    				(ClassManager.getInstance()).createEntry(path, loadedClass);
						}
	    			});
	    			
	    			classNames.printContent();
	    			loader.close();
				}
	    		catch (IOException e)
				{
					e.printStackTrace();
				}
	    	}
	    }
	}

	private void out(String x) 
	{
		System.out.println(x);
	}
}
