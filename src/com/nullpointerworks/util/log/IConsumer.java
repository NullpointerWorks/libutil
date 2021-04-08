/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
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