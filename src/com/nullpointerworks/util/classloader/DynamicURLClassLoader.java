package com.nullpointerworks.util.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DynamicURLClassLoader extends URLClassLoader
{
	public DynamicURLClassLoader(File f) throws MalformedURLException
	{
		super( new URL[] { f.toURI().toURL() });
	}
	
	// called by the ClassManager
	public Class<?> loadClassByPath(String classname)
	{
		Class<?> testclazz = null;
		try
		{
			testclazz = Class.forName(classname, true, this);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return testclazz;
	}
	
	/**
	 * custom class searching. 
	 */
	@Override
	protected Class<?> findClass(String classname) throws ClassNotFoundException
	{
    	// search our manually loaded classes first
		Class<?> result = (ClassManager.getInstance()).findClass(classname);
    	if (result != null) return result;
    	
    	// search java database next
    	result = super.findClass(classname);
    	if (result != null) return result;
	    
    	// oops
    	throw new ClassNotFoundException(classname);
	}
}