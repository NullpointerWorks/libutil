/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.file.bytefile;

/**
 * 
 * @since 1.0.0
 */
public class EndOfFileException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private int endoffile = -1;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public EndOfFileException(int eof)
	{
		endoffile = eof;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public int getBytesTillEOF()
	{
		return endoffile;
	}
}
