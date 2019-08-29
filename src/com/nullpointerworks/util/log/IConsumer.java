/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.log;

/**
 * An interface for input/output capture streams. Contains a method to append a new string of text.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public interface IConsumer 
{
    /**
     * A text trigger for an input/output stream.
     * @param text - the text to append
     * @since 1.0.0
     */   
    public void appendText(String text);
}