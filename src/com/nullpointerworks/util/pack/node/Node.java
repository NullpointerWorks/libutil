/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack.node;

import java.util.Vector;

import com.nullpointerworks.util.pattern.Iterator;

/**
 * The {@code Node<T>} object acts like an item in a folder/tree structure. It may have a value associated with it, or another list of node branches. All nodes have a name that specifies its location in the node tree, and a value object of the template class {@code T}.
 * @author Michiel Drost - Nullpointer Works
 * @since 1.0.0
 */
public class Node<T>
{
	private String seperator = "/";
	private T nodeValue=null;
	private String name=null;
	private Vector<Node<T>> nodes=null;
	private NodeValidation<T> validCheck = new NodeValidation<T>()
	{
		@Override
		public boolean isValid(T input)
		{
			if (input!=null) return true;
			return false;
		}
	};
	
	/**
	 * Null constructor. Don't use unless you know what you're doing.
	 * @since 1.0.0
	 */
	public Node() { }
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Node(String name)
	{ 
		makeNode(name,null,null); 
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Node(String name, T value)
	{ 
		makeNode(name,value,null); 
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Node(String name, T value, NodeValidation<T> valid)
	{ 
		makeNode(name,value,valid); 
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public T findValue(int index)
	{
		if (index>=size()||index<0) return null;
		return nodes.get(index).getValue();
	}
	
	/**
	 * Find a value using a given path. The default separator is '/'.<br>
	 * Use 'findValue(String path, String separator)' to use a custom separator
	 * @since 1.0.0
	 */
	public T findValue(String path)
	{
		return findValue(path,seperator);
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public T findValue(String path, String seperator)
	{
		return findValue( makeVectorPath(path, seperator) );
	}

	/**
	 * Find a child node using an index or path.
	 * @since 1.0.0
	 */
	public Node<T> findNode(int index)
	{
		if (index>=size()||index<0)
			return new Node<T>();
		return nodes.get(index);
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(String path)
	{
		return findNode( makeVectorPath(path, seperator) );
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(String path, String seperator)
	{
		return findNode( makeVectorPath(path, seperator) );
	}
	
	/**
	 * Find a value using a path in the form of a Vector. Each element is a segment of the absolute path to the item. Example: root/folder/anotherFolder/myValue where myValue is the target value, not a folder.
	 * @since 1.0.0
	 */
	public T findValue(Vector<String> path)
	{
		if (path.size()<1)
		{
			return nodeValue;
		}
		
		String nextNode = path.get(0);
		path.remove(0);
		for (Node<T> n : nodes)
		{
			if (n.getName().equals(nextNode))
			{
				return n.findValue( path );
			}
		}
		return null;
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(Vector<String> path)
	{
		if (path.size()<1)
		{
			return this;
		}
		
		String nextNode = path.get(0);
		path.remove(0);
		
		for (Node<T> n : nodes)
		{
			if (n.getName().equals(nextNode))
			{
				return n.findNode( path );
			}
		}
		
		return new Node<T>();
	}
	
	/**
	 * Add a path to a child node with a given value. Returns the absolute 
	 * parent node so you can chain multiple creatPath() together.<br>
	 * Example: root.createPath("first",1).createPath("second",2).createPath("third",3)<br>
	 * <br>
	 * createPath(String, C) accepts a third optional parameter which is the Validation class.<br>
	 * Validation allows values to be set if they pass validation and are otherwise discarded.
	 * @since 1.0.0
	 **/
	public Node<T> createPath(String path, T value)
	{
		createPath(path, value, validCheck);
		return this;
	}

	/**
	 * Add a path to a child node with a given value. Returns the absolute 
	 * parent node so you can chain multiple creatPath() together.<br>
	 * Example: root.createPath("first",1).createPath("second",2).createPath("third",3)<br>
	 * <br>
	 * Using the default Validation class checks if a value is null and discards the input if true.
	 * @since 1.0.0
	 **/
	public Node<T> createPath(String path, T value, NodeValidation<T> valid)
	{
		createPath( makeVectorPath(path,seperator) , value, valid);
		return this;
	}

	/**
	 * @since 1.0.0
	 */
	public Node(Vector<String> path, T value, NodeValidation<T> valid)
	{
		makeNode(path.get(0),null,valid);
		path.remove(0);
		createPath(path,value,valid);
	}
	
	/*
	 * @since 1.0.0
	 */
	private Node<T> createPath(Vector<String> path, T value, NodeValidation<T> valid)
	{
		if (path.size()<1)
		{
			if (validCheck==null) this.validCheck=valid;
			if(!validCheck.isValid(value)) return this;
			makeNode(name,value,valid);
			return this;
		}
		
		String thisNode = path.get(0);
		for (Node<T> n : nodes)
		{
			if (n.getName().equals(thisNode))
			{
				path.remove(0);
				n.createPath(path , value, valid);
				return this;
			}
		}
		nodes.add( new Node<T>(path, value, valid) );
		return this;
	}

	/**
	 * @since 1.0.0
	 */
	private Vector<String> makeVectorPath(String path, String sep)
	{
		String[] tokens = path.split(sep);
		Vector<String> vectorPath = new Vector<String>();
		for (String s:tokens)
			vectorPath.add(s);
		return vectorPath;
	}
	
	/**
	 * Makes a node end point
	 * @since 1.0.0
	 */
	private void makeNode(String name, T value, NodeValidation<T> valid)
	{
		nodes = new Vector<Node<T>>();
		this.name = name;
		if (valid!=null) this.validCheck = valid;
		this.setValue(value);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String getName() 
	{ 
		return name; 
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public T getValue()
	{ 
		return nodeValue; 
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public void setValue(T value) 
	{ 
		nodeValue = value; 
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void setFolderMarker(String mark)
	{
		seperator = mark;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public String getFolderMarker()
	{
		return seperator;
	}
	
	/**
	 * Returns the number of Nodes stored.
	 * @since 1.0.0
	 */
	public int size()
	{
		if (isNull()) return 0;
		return nodes.size(); 
	}
	
	/**
	 * Returns true if the node has no child nodes.
	 * @since 1.0.0
	 */
	public boolean isEmpty()
	{ 
		return (size()==0)?true:false; 
	}
	
	/**
	 * Returns true if either the name, nodes or validation is null
	 * @since 1.0.0
	 */
	public boolean isNull()
	{
		if (name==null) return true;
		if (nodes==null)
		{
			if (nodeValue==null)
			{
				return true;
			}
			else
			{
				if (validCheck==null) return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Iterator<Node<T>> getIterator()
	{
		return new Iterator<Node<T>>(nodes);
	}
}
