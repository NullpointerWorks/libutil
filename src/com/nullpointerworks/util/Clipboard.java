/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Contains a few methods for placing data into, and from the clipboard.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Clipboard 
{
	/**
	 * Retrieves a string of text from the system's clipboard.
	 * @return a string of text from the system's clipboard
	 * @since 1.0.0
	 */
	public static String getString()
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.datatransfer.Clipboard clipboard = toolkit.getSystemClipboard();
		String result = "";
		try 
		{
			result = (String) clipboard.getData(DataFlavor.stringFlavor);
		} 
		catch (UnsupportedFlavorException e) 
		{
			Log.err("Could not retrieve a string from the clipboard.");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Places a string of text into the system's clipboard.
	 * @param string - the string to place into the clipboard
	 * @since 1.0.0
	 */
	public static void setString(String string)
	{
		StringSelection s = new StringSelection(string);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.datatransfer.Clipboard cb = toolkit.getSystemClipboard();
	    cb.setContents(s, s);
	}
}
