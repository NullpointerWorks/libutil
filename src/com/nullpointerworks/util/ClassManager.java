/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ClassManager
{
	private static ClassManager inst = null;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static ClassManager getInstance()
	{
		if (inst == null) inst = new ClassManager();
		return inst;
	}
	
	// =============================================================
	
	private Map<String, Class<?>> loaded;
	
	private ClassManager()
	{
		loaded	= new HashMap<String, Class<?>>();
	}
	
	/**
	 * Returns the {@code Class} object associated with the class or interface with the given string name.
	 * @param classname - the fully qualified name of the desired class.
     * @return the {@code Class} object for the class with the specified name.
	 * @since 1.0.0
	 * @see Class
	 */
	public Class<?> forName(String classname) 
			throws ClassNotFoundException
	{
		if (loaded.containsKey(classname))	
		{
			return loaded.get(classname);
		}
		else
		{
			return Class.forName(classname);
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public List<Class<?>> findClassBySuperClass(Class<?> superclass)
	{
		List<Class<?>> list = new ArrayList<Class<?>>();
		Iterator<Entry<String, Class<?>>> it = loaded.entrySet().iterator();
	    while (it.hasNext())
	    {
	        Entry<String, Class<?>> pair = (Entry<String, Class<?>>) it.next();
	        Class<?> c = pair.getValue();
	        if (c.getSuperclass() == superclass)
		    {
	        	list.add(c);
		    }
	    }
	    return list;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void loadJavaArchive(String pathToJar) 
			throws IOException
	{
		if (!pathToJar.endsWith(".jar")) return;
		
		JarFile jarFile 			= new JarFile(pathToJar);
		URL[] urls 					= { new URL("jar:file:" + pathToJar+"!/") };
		URLClassLoader cl 			= new URLClassLoader(urls);
		Enumeration<JarEntry> e 	= jarFile.entries();
		while (e.hasMoreElements()) 
		{
		    JarEntry je = e.nextElement();
		    if(je.isDirectory() || !je.getName().endsWith(".class")) continue;
		    String className 	= je.getName().substring(0,je.getName().length()-6);
		    className 			= className.replace('/', '.'); // all classes are stored like; "path.to.class.MyClassName"
		    
		    if (className.equalsIgnoreCase("module-info")) continue;
		    
		    Class<?> c = null;
		    try
			{
				c = cl.loadClass(className);
			} 
			catch (ClassNotFoundException ex)
			{
				//ex.printStackTrace();
				System.err.println("ClassManager: Cannot find the class file for \""+className+"\".");
				continue;
			}
		    
		    loaded.put(className, c);
		}
		
		cl.close();
		jarFile.close();
	}
}
