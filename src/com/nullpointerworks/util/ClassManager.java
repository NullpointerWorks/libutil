/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
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
 * A dynamic class loader for loading classes and files at run-time. This class is particularly useful when implementing plug-in capabilities to your project.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class ClassManager// extends ClassLoader
{
	private static ClassManager inst = null;
	
	/**
	 * Returns an instance of the singleton {@code ClassManager} object.
	 * @return an instance of the singleton {@code ClassManager} object
	 * @since 1.0.0
	 */
	public static ClassManager getInstance()
	{
		if (inst == null) inst = new ClassManager();
		return inst;
	}
	
	/**
	 * Returns a new instance of the {@code ClassManager} object.
	 * @return a new instance of the {@code ClassManager} object
	 * @since 1.1.0
	 */
	public static ClassManager newInstance()
	{
		return new ClassManager();
	}
	
	// =============================================================
	
	private Map<String, Class<?>> loaded;
	
	private ClassManager()
	{
		loaded	= new HashMap<String, Class<?>>();
	}
	
	/**
	 * Returns the {@code Class} object associated with the class or interface with the given string name. If the class can not be found in the {@code ClassManager} then this method will attempt to search the JVM class register.
	 * @param classname - the fully qualified name of the desired class.
     * @return the {@code Class} object for the class with the specified name
     * @throws ClassNotFoundException if the class cannot be located
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
	 * Return a {@code List} of classes that extend the given superclass. This list will be empty if none of the loaded classes extend the superclass.
	 * @param superclass - the expected superclass of the loaded classes
	 * @return a {@code List} of classes that extend the given superclass
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
	 * Opens a local jar file and loads the content of all {@code class} files. 
	 * @param path - path to the *.jar file
	 * @throws IOException if an I/O error has occurred
	 * @since 1.0.0
	 */
	public void loadJavaArchive(String path) 
			throws IOException
	{
		if (!path.endsWith(".jar")) return;
		
		URL[] urls 					= { new URL("jar:file:" + path+"!/") };
		URLClassLoader cl 			= new URLClassLoader(urls); //new URLClassLoader(urls, this);
		
		JarFile jarFile 			= new JarFile(path);
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
				System.err.println("ClassManager: Cannot find the *.class file for \""+className+"\".");
				continue;
			}
		    
		    loaded.put(className, c);
		}
		
		cl.close();
		jarFile.close();
	}
}
