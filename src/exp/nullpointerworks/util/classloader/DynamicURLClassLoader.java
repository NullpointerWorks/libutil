/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.util.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 
 * @since 1.0.0
 */
public class DynamicURLClassLoader extends URLClassLoader
{
	/**
	 * 
	 * @since 1.0.0
	 */
	public DynamicURLClassLoader(File f) throws MalformedURLException
	{
		super( new URL[] { f.toURI().toURL() });
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Class<?> loadClassByPath(String classname) throws ClassNotFoundException
	{
		Class<?> testclazz = null;
		testclazz = Class.forName(classname, true, this);
		return testclazz;
	}
	
	/**
	 * 
	 * @since 1.0.0
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