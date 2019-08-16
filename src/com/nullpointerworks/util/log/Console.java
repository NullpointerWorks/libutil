/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
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
 * 
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Console implements IConsumer 
{
	private static Console instance=null;
    
    /**
     * 
     * @since 1.0.0
     */
	public static Console getInstance()
	{
		if (instance==null) instance = new Console();
		return instance;
	}
	
	private JPanel panel;
	private JTextArea output;
	private static Color BACKGROUND = new Color(0.2f,0.2f,0.2f,1.0f);
	private static Color FOREGROUND = new Color(0.9f,0.9f,0.9f,1.0f);
    
    /**
     * 
     * @since 1.0.0
     */
    public Console() 
    {
    	panel = new JPanel();
    	panel.setLayout( new BorderLayout() );
        output = new JTextArea();
        panel.add( new JScrollPane(output) );
        setLooks();

        PrintStream po = System.out;
        PrintStream pe = System.err;
        
        System.setOut( new PrintStream( new StreamCapturer(this, po) ) ); // print output
        System.setErr( new PrintStream( new StreamCapturer(this, pe) ) ); // print errors
        panel.setFont( new Font( "Courier New" , Font.PLAIN ,10 ) );
    }
    
    /**
     * 
     * @since 1.0.0
     */
    public JPanel getInterface()
    {
    	return panel;
    }
    
    /**
     * 
     * @since 1.0.0
     */
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
