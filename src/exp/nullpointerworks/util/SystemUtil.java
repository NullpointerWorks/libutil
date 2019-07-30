/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.util;

import java.util.Map;

import com.nullpointerworks.util.Log;

/**
 * 
 * @since 1.0.0
 */
public class SystemUtil 
{
	/**
	 * On Windows, returns the path to the Roaming folder.
	 * @since 1.0.0
	 */
	public static String Roaming()
	{
		return System.getProperty("user.home") + "\\Appdata\\Roaming";
	}
	
	/**
	 * On Windows, returns the path to the hidden AppData folder.
	 * @since 1.0.0
	 */
	public static String AppData()
	{
		return System.getProperty("user.home") + "\\Appdata";
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String Home()
	{
		return System.getProperty("user.home");
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static String getProperty(String s)
	{
		return System.getProperty(s);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void printProperties()
	{
		for (Map.Entry<?,?> e : System.getProperties().entrySet()) 
		{
		    Log.out(String.format("%s = %s", e.getKey(), e.getValue())); 
		}
	}
}
