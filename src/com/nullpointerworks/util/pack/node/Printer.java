package com.nullpointerworks.util.pack.node;

public abstract class Printer<NameType,DataType>
{
	public abstract void onPrint(NameType name, DataType value);
}
