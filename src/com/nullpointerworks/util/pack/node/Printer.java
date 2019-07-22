/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack.node;

public abstract class Printer<NameType,DataType>
{
	public abstract void onPrint(NameType name, DataType value);
}
