/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util;

import java.io.PrintStream;

import com.nullpointerworks.util.log.LogCapture;
import com.nullpointerworks.util.log.StreamCapturer;

/**
 * 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class Log 
{
	private static LogCapture captureOut;
	private static LogCapture captureErr;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void capture()
	{
		capture(200);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void capture(int maxlines)
	{
		captureOut = new LogCapture(maxlines);
		captureErr = new LogCapture(maxlines);
		System.setOut( new PrintStream( new StreamCapturer(captureOut, System.out) ) ); // print output
		System.setErr( new PrintStream( new StreamCapturer(captureErr, System.err) ) ); // print errors
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static LogCapture getOutputStream()
	{
		return captureOut;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static LogCapture getErrorStream()
	{
		return captureErr;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void out(String text)
	{
		System.out.println(text);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public static void err(String text)
	{
		System.err.println(text);
	}
}
