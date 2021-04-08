/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.log;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * The Console class serves as an implementation of a {@code JPanel} that captures the {@code System.out/err} print streams and displays them in an text area. This can be handy if the console output is not available at run-time, but there may be valuable information to display.
 * @see JPanel
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Console extends JPanel implements IConsumer 
{
	private static final long serialVersionUID = 1L;
	
	private static Console instance=null;
    
    /**
     * Returns an instance of the singleton {@code Console} object.
     * @return an instance of the singleton {@code Console} object
     * @since 1.0.0
     */
	public static Console getInstance()
	{
		if (instance==null) instance = new Console();
		return instance;
	}
	
	private static Color BACKGROUND = new Color(0.2f,0.2f,0.2f,1.0f);
	private static Color FOREGROUND = new Color(0.9f,0.9f,0.9f,1.0f);
	
	// ==============================================
	
	private JTextArea output;
	
    /**
     * Creates a new {@code Console} panel instance. Captures the {@code System.ou}t and {@code System.err} {@code PrintStream} objects and appends them to an internal JTextArea. 
     * @since 1.0.0
     */
    public Console() 
    {
    	super();
    	setLayout( new BorderLayout() );
        output = new JTextArea();
        add( new JScrollPane(output) );
        setLooks();
        
        PrintStream po = System.out;
        PrintStream pe = System.err;
        
        System.setOut( new PrintStream( new StreamCapturer(this, po) ) ); // print output
        System.setErr( new PrintStream( new StreamCapturer(this, pe) ) ); // print errors
        setFont( new Font( "Courier New" , Font.PLAIN ,10 ) );
    }
    
    @Override
    public void appendText(final String text) 
    {
        if (EventQueue.isDispatchThread()) 
        {
            output.append(text);
            output.setCaretPosition(output.getText().length());
        } 
        else 
        {
            EventQueue.invokeLater(new Runnable() 
            {
                @Override
                public void run() 
                {
                    appendText(text);
                }
            });
        }
    }
    
	private void setLooks()
	{
		output.setBackground( BACKGROUND );
		output.setForeground( FOREGROUND );
		output.setFont( new Font( "Courier New" , Font.PLAIN , 12 ) );
		output.setCaretColor( FOREGROUND );
	}
}
