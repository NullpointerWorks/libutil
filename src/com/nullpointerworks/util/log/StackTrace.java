/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Calendar;

import com.nullpointerworks.util.FileUtil;

/**
 * This class serves as a utility to write the content of an exception to a {@code *.log} file at a specified directory.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class StackTrace 
{
	/**
	 * Write an exception error log to a {@code *.log} file with the system time and date as its filename.
	 * @param ex - the exception to trace
	 * @param dir - the directory to write to
	 * @since 1.0.0
	 */
	public static void write(Exception ex, String dir)
	{
		int yr = Calendar.getInstance().get(Calendar.YEAR);
		int mn = Calendar.getInstance().get(Calendar.MONTH);
		int dy = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int hh = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		int mm = Calendar.getInstance().get(Calendar.MINUTE);
		int ss = Calendar.getInstance().get(Calendar.SECOND);
		
		if (!dir.endsWith("/")) dir = dir+"/";
		
		String path = dir+yr
					+"_"+mn
					+"_"+dy
					+"_"+hh
					+"_"+mm
					+"_"+ss
					+".log";
		
		if (FileUtil.create(path))
		{
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
		else
		{
			// either the file already exists or something went wrong during writing
		}
	}
}
