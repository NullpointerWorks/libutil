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
 * Contains an implementation of the {@code TextFile} object that serves as a settings parser. The default comment line marker is semicolon({@code ;}) and the header:value separation marker is the {@code =} sign.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Settings 
{
	private boolean insertUnknown = false;
	private ArrayList<String[]> settings;
	private String REM = ";";
	private String SEP = "=";
	private String path = null;
	
	/**
	 * Creates a new settings instance. 
	 * @param 
	 * @since 1.0.0
	 */
	public Settings()
	{
		settings = new ArrayList<String[]>();
	}
	
	/**
	 * Creates a new settings instance with an initial path to read and write from.
	 * @param path - the settings file path
	 * @since 1.0.0
	 */
	public Settings(String path)
	{
		this();
		this.path = path;
	}
	
	/**
	 * Add a default header and value into the settings register.
	 * @param header - the header to add
	 * @param value - the initial value
	 * @since 1.0.0
	 */
	public void addDefault(String header, String value)
	{
		settings.add( new String[] {header,value} );
	}
	
	/**
	 * Add a header to read from the settings file. The value of the header is set empty by default.
	 * @param header - the header to add to the settings register
	 * @since 1.0.0
	 */
	public void addHeader(String header)
	{
		addDefault(header,"");
	}
	
	/**
	 * Add a comment line to the settings file.
	 * @param msg - the comment message to add
	 * @since 1.0.0
	 */
	public void addComment(String msg) 
	{
		addDefault(REM,msg);
	}
	
	/**
	 * Insert a value into the settings collection. If the header is already present, then the value will be overridden, otherwise it's created as a new entry if the {@code unknown} flag has been set.
	 * @param header - the setting header to locate
	 * @param value - the value string to place
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
	 * Returns the value associated with the given header if available. Returns {@code null} otherwise.
	 * @param header - the setting header to locate
	 * @return the value associated with the given header
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
		return null;
	}
	
	/**
	 * Records can be added of which their header is not already present in the register. These records are not added by default. Set to {@code true} to enable this feature.
	 * @param unknown - boolean to enable adding unknown headers
	 * @since 1.0.0
	 */
	public void setUnknownFillFlag(boolean unknown)
	{
		insertUnknown = unknown;
	}
	
	/**
	 * Set the character to be ignored be the parser
	 * @param rem - the commentary character
	 * @since 1.0.0
	 */
	public void setCommentMarker(String rem)
	{
		REM = rem;
	}
	
	/**
	 * Set the {@code header:value} separator character
	 * @param sep - the separation character
	 * @since 1.0.0
	 */
	public void setSeparationMarker(String sep)
	{
		SEP = sep;
	}
	
	/**
	 * Returns the comment line marker.
	 * @return the comment line marker
	 * @since 1.0.0
	 */
	public String getCommentMarker()
	{
		return REM;
	}
	
	/**
	 * Returns the {@code header:value} separation marker.
	 * @return the {@code header:value} separation marker
	 * @since 1.0.0
	 */
	public String getSeparationMarker()
	{
		return SEP;
	}
	
	/**
	 * Load a file which will be interpreted as settings. The used path is specified in the {@code Settings} constructor. The assumed file encoding is "UTF-16". If there was no path set during construction, this method will return.
	 * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading
	 * @since 1.0.0
	 */
	public void load() throws FileNotFoundException
	{
		if (path==null) return;
		load(path);
	}
	
	/**
	 * Load a file which will be interpreted as settings. The assumed file encoding is "UTF-16".
	 * @param path - the path to load from
	 * @throws FileNotFoundException if the file does not exist, is a directory rather than a regular file, or for some other reason cannot be opened for reading
	 * @since 1.0.0
	 */
	public void load(String path) throws FileNotFoundException
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
	 * Save the current settings at the specified path given in the {@code Settings} constructor. Default file encoding is "UTF-16". If there was no path set during construction, this method will return.
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public void save() throws IOException
	{
		if (path==null) return;
		save(path);
	}
	
	/**
	 * Save the current settings to a file. Default file encoding is "UTF-16".
	 * @param path - the path to save to
	 * @throws IOException if an I/O error occurs
	 * @since 1.0.0
	 */
	public void save(String path) throws IOException
	{
		TextFile file = new TextFile();
		for (int i=0,l=settings.size(); i<l; i++)
		{
			String[] sett = settings.get(i);
			
			if (sett[0].equalsIgnoreCase(REM))
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
}
