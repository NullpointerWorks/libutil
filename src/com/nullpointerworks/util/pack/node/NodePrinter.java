/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack.node;

import com.nullpointerworks.util.pattern.Iterator;

/**
 * An abstract class to read all the end point values of a {@code Node<T>} structure.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public abstract class NodePrinter<T>
{
	private String seperator = "/";
	
	/**
	 * The abstract method that determines what to do with the given path and value object.
	 * @param path - the node path
	 * @param value - the object to handle
	 * @since 1.0.0
	 */
	public abstract void onPrint(String path, T value);
	
	/**
	 * Sets the node branch separator marker.
	 * @param mark - the node branch separator marker
	 * @since 1.0.0
	 */
	public void setFolderMarker(String mark)
	{
		seperator = mark;
	}
	
	/**
	 * Returns the node branch separator marker.
	 * @return the node branch separator marker
	 * @since 1.0.0
	 */
	public String getFolderMarker()
	{
		return seperator;
	}
	
	/**
	 * Print the content of the given root {@code Node<T>}.
	 * @param root - the node to start scanning for content
	 * @since 1.0.0
	 */
	public void print(Node<T> root)
	{
		print(root, root.getName());
	}
	
	/**
	 * Print the content of the given root {@code Node<T>} with a custom prefix.
	 * @param root - the node to start scanning for content
	 * @param prefix - the text to add in front of each branch
	 * @since 1.0.0
	 */
	public void print(Node<T> root, String prefix)
	{
		Iterator<Node<T>> it = root.getIterator();
		while(it.hasNext())
		{
			Node<T> node = it.getNext();
			if (!node.isEmpty())
			{
				print(node, prefix + seperator + node.getName());
				return;
			}
		}
		
		String name = root.getName();
		if (name!=null)
		{
			if (root.getValue()!=null) onPrint(name, root.getValue());
		}
	}
}
