package com.nullpointerworks.util;

import java.util.Map;

public class SystemUtil 
{
	/**
	 * On Windows, returns the path to the Roaming folder
	 */
	public static String Roaming()
	{
		return System.getProperty("user.home") + "\\Appdata\\Roaming";
	}
	
	/**
	 * On Windows, returns the path to the hidden AppData folder
	 */
	public static String AppData()
	{
		return System.getProperty("user.home") + "\\Appdata";
	}
	
	/**
	 * 
	 */
	public static String Home()
	{
		return System.getProperty("user.home");
	}
	
	/**
	 * 
	 */
	public static String getProperty(String s)
	{
		return System.getProperty(s);
	}
	
	/**
	 * 
	 */
	public static void printProperties()
	{
		for (Map.Entry<?,?> e : System.getProperties().entrySet()) 
		{
		    Log.out(String.format("%s = %s", e.getKey(), e.getValue())); 
		}
	}
	
}
