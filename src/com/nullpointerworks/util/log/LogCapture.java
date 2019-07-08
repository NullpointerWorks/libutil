package com.nullpointerworks.util.log;

import java.util.ArrayList;

import com.nullpointerworks.util.pattern.Iterator;

public class LogCapture implements IConsumer
{
	private ArrayList<String> out;
	private int maxLines = 200;
	
	public LogCapture(int lines)
	{
		maxLines = lines;
	    out = new ArrayList<String>();
	}
	
	@Override
	public void appendText(String text) 
	{
		out.add(text);
		if (out.size() > maxLines) out.remove(0);
	}
	
	public Iterator<String> getIterator()
	{
		return new Iterator<String>(out);
	}
}
