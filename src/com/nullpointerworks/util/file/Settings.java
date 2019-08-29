/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.nullpointerworks.util.StringUtil;
import com.nullpointerworks.util.file.textfile.TextFile;
import com.nullpointerworks.util.file.textfile.TextFileParser;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Settings 
{
	private boolean insertUnknown = false;
	private ArrayList<String[]> settings;
	private String REM = ";";
	private String SEP = "=";
	private String path;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Settings(String path)
	{
		settings = new ArrayList<String[]>();
		this.path = path;
	}
	
	/**
	 * add a header and value into the settings.
	 * @since 1.0.0
	 */
	public void addDefault(String header, String value)
	{
		settings.add( new String[] {header,value} );
	}
	
	/**
	 * add a header to read from the settings file
	 * @since 1.0.0
	 */
	public void addHeader(String header)
	{
		addDefault(header,"");
	}
	
	/**
	 * add a comment line to the settings
	 * @since 1.0.0
	 */
	public void addComment(String msg) 
	{
		addDefault(REM,msg);
	}
	
	/**
	 * adds records from file that do not match the set defaults
	 * @since 1.0.0
	 */
	public void setFillUnknown(boolean u)
	{
		insertUnknown = u;
	}
	
	/**
	 * Set the character to be ignored be the parser
	 * @since 1.0.0
	 */
	public void setCommentMarker(String r)
	{
		REM = r;
	}
	
	/**
	 * Set the header-value separator character
	 * @since 1.0.0
	 */
	public void setSeparator(String sep)
	{
		SEP = sep;
	}
	
	/**
	 * Returns the comment character
	 * @since 1.0.0
	 */
	public String getCommentMarker()
	{
		return REM;
	}
	
	/**
	 * Returns the separator marker
	 * @since 1.0.0
	 */
	public String getSeparator()
	{
		return SEP;
	}
	
	/**
	 * load the settings file
	 * @throws FileNotFoundException 
	 * @since 1.0.0
	 */
	public void loadFile() throws FileNotFoundException
	{
		TextFile file = TextFileParser.file(path);
		String[] lines = file.getLines();
		for (String s : lines)
		{
			String rem = s.substring(0, 0);
			if (!rem.equalsIgnoreCase(REM))
			{
				String[] t = StringUtil.tokenize(s, SEP, StringUtil.TRIM);
				insert(t[0], StringUtil.compile(t," ",1, t.length) );
			}
		}
	}
	
	/**
	 * save the current settings to file
	 * @throws IOException 
	 * @since 1.0.0
	 */
	public void saveToFile() throws IOException
	{
		TextFile file = new TextFile();
		for (int i=0,l=settings.size(); i<l; i++)
		{
			String[] sett = settings.get(i);
			
			if (sett[0].equalsIgnoreCase(REM)) // if comment
			{
				file.addLine(REM+" "+sett[1]);
			}
			else
			{
				file.addLine(sett[0]+SEP+sett[1]);
			}
		}
		TextFileParser.write(path, file);
	}
	
	/**
	 * insert a value into the settings collection
	 * @since 1.0.0
	 */
	public void insert(String header, String value)
	{
		for (int i=0,l=settings.size(); i<l; i++)
		{
			String[] sett = settings.get(i);
			if (sett[0].equalsIgnoreCase(header))
			{
				sett[1] = value;
				return;
			}
		}
		if (insertUnknown) addDefault(header, value);
	}

	/**
	 * returns the value associated with the given header
	 * @since 1.0.0
	 */
	public String getValue(String header)
	{
		for (int i=0,l=settings.size(); i<l; i++)
		{
			String[] sett = settings.get(i);
			if (sett[0].equalsIgnoreCase(header))
			{
				return sett[1];
			}
		}
		return "";
	}
}
