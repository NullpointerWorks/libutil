/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.log;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import com.nullpointerworks.util.Log;

public class Clipboard 
{
	/**
	 * retrieves a string of text from the clipboard
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
	 * paste a string into the system's clipboard
	 */
	public static void setString(String text)
	{
		StringSelection s = new StringSelection(text);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		java.awt.datatransfer.Clipboard cb = toolkit.getSystemClipboard();
	    cb.setContents(s, s);
	}
}
