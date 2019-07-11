package com.nullpointerworks.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;

import com.nullpointerworks.util.FileUtil;
import com.nullpointerworks.util.Log;

public class StackTrace 
{
	/**
	 * Print an exception error log to a file
	 */
	public static void write(Exception ex)
	{
		int year 	= Calendar.getInstance().get(Calendar.YEAR);
		int mont 	= Calendar.getInstance().get(Calendar.MONTH);
		int day 	= Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int hh 	= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int mm 	= Calendar.getInstance().get(Calendar.MINUTE);
		int ss 	= Calendar.getInstance().get(Calendar.SECOND);
		
		String path = "log/"+year
					+"_"+mont
					+"_"+day
					+"_"+hh
					+"_"+mm
					+"_"+ss
					+".log";
		
		FileUtil.create(path);
		File file = new File(path);
		try 
		{
			PrintStream ps = new PrintStream(file);
			ex.printStackTrace(ps);
			ps.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Print an exception error log to a file. Print the given error message
	 */
	public static void write(Exception ex, String err)
	{
		Log.err(err);
		write(ex);
	}
}
