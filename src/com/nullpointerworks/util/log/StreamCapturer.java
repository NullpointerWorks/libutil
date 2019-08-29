/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.log;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Provides the function of capturing the input of a print stream and relays the text. All input is send to an object that implements {@code IConsumer} and, optionally, to another print stream. 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class StreamCapturer extends OutputStream 
{
    private final StringBuilder buffer = new StringBuilder(128);
    private IConsumer consumer = null;
    private PrintStream old = null;
    
	/**
	 * Creates an {@code PrintStream} capture object. 
	 * @param consumer - a reference object that can be written to
	 * @since 1.0.0
	 */
    public StreamCapturer(IConsumer consumer) 
    {
        this.consumer = consumer;
    }
	
	/**
	 * Creates an {@code PrintStream} capture object. 
	 * @param consumer - a reference object that can be written to
	 * @param printstream - the old print stream that is being replaced
	 * @since 1.0.0
	 */
    public StreamCapturer(IConsumer consumer, PrintStream printstream) 
    {
    	this(consumer);
        this.old = printstream;
    }
    
    @Override
    public void write(int b) throws IOException 
    {
        char c = (char) b;
        String value = Character.toString(c);
        buffer.append(value);
        if (value.equals("\n")) 
        {
	        consumer.appendText( buffer.toString() );
	        buffer.delete(0, buffer.length());
        }
        if (old!=null) old.print(c);
    }
}