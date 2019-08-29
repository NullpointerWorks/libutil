/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.pack.node;

import java.util.ArrayList;
import java.util.List;

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
	private List<Node<T>> nodes=null;
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
	 * Creates a new node with the given name but without a value object or validation method.
	 * @param name - name of the node
	 * @since 1.0.0
	 */
	public Node(String name)
	{ 
		makeNode(name,null,null); 
	}
	
	/**
	 * Creates a new node with the given name and having the value specified with the given class, but without a validation method.
	 * @param name - name of the node
	 * @param value - the value object
	 * @since 1.0.0
	 */
	public Node(String name, T value)
	{ 
		makeNode(name,value,null); 
	}
	
	/**
	 * Creates a new node with the given name, having the value specified with the given class and a validation method.
	 * @param name - name of the node
	 * @param value - the value object
	 * @param valid - the validation method
	 * @since 1.0.0
	 */
	public Node(String name, T value, NodeValidation<T> valid)
	{ 
		makeNode(name,value,valid); 
	}

	/**
	 * Creates a new node with a list of node names where this node is to be created. The last node name is the name of the created node.
	 * @param path - the list of node names
	 * @param value - the value object
	 * @param valid - the validation method
	 * @since 1.0.0
	 */
	public Node(List<String> path, T value, NodeValidation<T> valid)
	{
		makeNode(path.get(0),null,valid);
		path.remove(0);
		createPath(path,value,valid);
	}
	
	/**
	 * Makes a new branch end
	 * @since 1.0.0
	 */
	private void makeNode(String name, T value, NodeValidation<T> valid)
	{
		nodes = new ArrayList<Node<T>>();
		this.name = name;
		if (valid!=null) this.validCheck = valid;
		this.setValue(value);
	}
	
	/**
	 * Returns the value object of the node at the specified index. If the node is not available, this method returns {@code null}.
	 * @param index - the index of the node
	 * @return the value object of the node at the specified index. If the node is not available, this method returns {@code null}
	 * @since 1.0.0
	 */
	public T findValue(int index)
	{
		if (index>=size()||index<0) return null;
		return nodes.get(index).getValue();
	}
	
	/**
	 * Locates the value of the node at the specified path. 
	 * @param path - the node path
	 * @return the value object of the node. If the node is not available, this method returns {@code null}
	 * @since 1.0.0
	 */
	public T findValue(String path)
	{
		return findValue( listPath(path, seperator) );
	}
	
	/**
	 * Locates the value of the node at the specified path in the form of a {@code List} object. Each list element specifies a node with the nast node being the target container. 
	 * @param path - the node path
	 * @return the value object of the node. If the node is not available, this method returns {@code null}
	 * @since 1.0.0
	 */
	public T findValue(List<String> path)
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
	 * Find the child node at the specified index if available, otherwise {@code null}
	 * @param index - the node index
	 * @return the node at the given index if available, otherwise {@code null}
	 * @since 1.0.0
	 */
	public Node<T> findNode(int index)
	{
		if (index < 0 || index>=nodes.size()) return null;
		return nodes.get(index);
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(String path)
	{
		return findNode( listPath(path, seperator) );
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(String path, String seperator)
	{
		return findNode( listPath(path, seperator) );
	}

	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<T> findNode(List<String> path)
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
	 * createPath(String, T) accepts a third optional parameter which is the Validation class.<br>
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
		createPath( listPath(path,seperator) , value, valid);
		return this;
	}
	
	/*
	 * @since 1.0.0
	 */
	private Node<T> createPath(List<String> path, T value, NodeValidation<T> valid)
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
	private List<String> listPath(String path, String sep)
	{
		String[] tokens = path.split(sep);
		List<String> list = new ArrayList<String>();
		for (String s:tokens) list.add(s);
		return list;
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
			if (validCheck==null) return true;
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
