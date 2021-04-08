/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.util.concurrency;

/**
 * The {@code UnavailablePoolException} is thrown when a method tries to fire an event through the {@code EventManager} while it's primary thread is not operational. 
 * @since 1.0.0
 * @author Michiel Drost - Nullpointer Works
 */
public class UnavailablePoolException extends Exception 
{
	private static final long serialVersionUID = -6916268870500628292L;
}
