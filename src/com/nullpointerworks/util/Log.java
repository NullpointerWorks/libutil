package com.nullpointerworks.util;

import java.io.PrintStream;

import com.nullpointerworks.util.log.LogCapture;
import com.nullpointerworks.util.log.StreamCapturer;

public class Log 
{
	private static LogCapture captureOut;
	private static LogCapture captureErr;
	
	public static void capture()
	{
		capture(200);
	}
	
	public static void capture(int maxlines)
	{
		captureOut = new LogCapture(maxlines);
		captureErr = new LogCapture(maxlines);
		System.setOut( new PrintStream( new StreamCapturer(captureOut, System.out) ) ); // print output
		System.setErr( new PrintStream( new StreamCapturer(captureErr, System.err) ) ); // print errors
	}
	
	public static LogCapture getOutputStream()
	{
		return captureOut;
	}
	
	public static LogCapture getErrorStream()
	{
		return captureErr;
	}
	
	public static void out(String text)
	{
		System.out.println(text);
	}
	
	public static void err(String text)
	{
		System.err.println(text);
	}
}
