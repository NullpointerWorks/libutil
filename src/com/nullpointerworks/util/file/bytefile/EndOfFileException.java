/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.file.bytefile;

/**
 * An End-Of-File extension that gets thrown when the {@code ByteFile} reader has reached the end of the file when trying to read past its length. This exception contains the number of bytes still available in the file.
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class EndOfFileException extends Exception 
{
	private static final long serialVersionUID = 1L;
	private int endoffile = -1;
	
	/**
	 * Creates a new End-Of-File exception with information on how many bytes were still available before trying to read from the {@code ByteFile}.
	 * @param oef - bytes left to read
	 * @since 1.0.0
	 */
	public EndOfFileException(int eof)
	{
		endoffile = eof;
	}
	
	/**
	 * Returns the number of bytes still available to read from the file.
	 * @return the number of bytes still available to read from the file
	 * @since 1.0.0
	 */
	public int getBytesTillEOF()
	{
		return endoffile;
	}
}
