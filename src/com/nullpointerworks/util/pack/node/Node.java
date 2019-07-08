package com.nullpointerworks.util.pack.node;

import java.util.Vector;

import com.nullpointerworks.util.pattern.IIterator;

public class Node<DataType>
{
	
	/**
	 **/
	public void setPrinter(Printer<String,DataType> p)
	{ 
		printer = p; 
	}

	/**
	 **/
	public void printContent()
	{
		if (!nodes.isEmpty())
		{
			for (Node<DataType> n : nodes)
			{
				n.setPrinter(printer);
				n.printContent( n.getName() );
			}
			return;
		}
		
		if (name!=null)
		{
			if (nodeValue!=null)
				printer.onPrint(name, nodeValue);
		}	
	}
	
	/**
	 **/
	private void printContent(String prefix)
	{
		if (!nodes.isEmpty())
		{	
			for (Node<DataType> n : nodes)
			{
				n.setPrinter(printer);
				n.printContent(prefix + "/" + n.getName());
			}
			return;
		}
		
		if (name!=null)
		{
			if (nodeValue!=null)
				printer.onPrint(prefix, nodeValue);
		}
	}
	
	// ------------------------------------------------------
	// ------------------------------------------------------
	
	private String name=null;
	private DataType nodeValue=null;
	private Vector<Node<DataType>> nodes=null;
	private Validation<DataType> validCheck = new Validation<DataType>()
	{
		public boolean isValid(DataType input)
		{
			if (input!=null)
				return true;
			return false;
		}
	};
	private Printer<String,DataType> printer = new Printer<String,DataType>()
	{
		public void onPrint(String name,DataType value)
		{
			System.out.println(name+" "+value);
		}
	};
	
	// ------------------------------------------------------
	// ------------------------------------------------------

	/**
	 **/
	private Node<DataType> createPath(Vector<String> path, DataType value, Validation<DataType> valid)
	{
		if (path.size()<1)
		{
			if (validCheck==null) this.validCheck=valid;
			if(!validCheck.isValid(value)) return this;
			makeNode(name,value,valid);
			return this;
		}
		
		String thisNode = path.get(0);
		for (Node<DataType> n : nodes)
		{
			if (n.getName().equals(thisNode))
			{
				path.remove(0);
				n.createPath(path , value, valid);
				return this;
			}
		}
		nodes.add( new Node<DataType>(path, value, valid) );
		return this;
	}
	
	// ------------------------------------------------------
	// ------------------------------------------------------

	/**
	 **/
	public DataType findValue(int index)
	{
		if (index>=size()||index<0) return null;
		return nodes.get(index).getValue();
	}
	
	/**
	 * Find a value using a given path. The default seperator is '/'.<br>
	 * Use 'findValue(String path, String seperator)' to use a custom seperator
	 */
	public DataType findValue(String path)
	{
		return findValue(path,"/");
	}

	/**
	 **/
	public DataType findValue(String path, String seperator)
	{
		return findValue( makeVectorPath(path, seperator) );
	}

	/**Find a child node using an index or path.
	 **/
	public Node<DataType> findNode(int index)
	{
		if (index>=size()||index<0)
			return new Node<DataType>();
		return nodes.get(index);
	}

	/**
	 **/
	public Node<DataType> findNode(String path)
	{
		return findNode( makeVectorPath(path, "/") );
	}

	/**
	 **/
	public Node<DataType> findNode(String path, String seperator)
	{
		return findNode( makeVectorPath(path, seperator) );
	}
	
	/**
	 * Find a value using a path in the form of a Vector. Each element is a segment<br>
	 * of the absolute path to the item. Example: root/folder/anotherFolder/myValue<br>
	 * where myValue is the target value, not a folder.
	 */
	public DataType findValue(Vector<String> path)
	{
		if (path.size()<1)
		{
			return nodeValue;
		}
		
		String nextNode = path.get(0);
		path.remove(0);
		for (Node<DataType> n : nodes)
		{
			if (n.getName().equals(nextNode))
			{
				return n.findValue( path );
			}
		}
		return null;
	}

	/**
	 **/
	public Node<DataType> findNode(Vector<String> path)
	{
		if (path.size()<1)
		{
			return this;
		}
		
		String nextNode = path.get(0);
		path.remove(0);
		
		for (Node<DataType> n : nodes)
		{
			if (n.getName().equals(nextNode))
			{
				return n.findNode( path );
			}
		}
		
		return nullNode();
	}
	
	// ------------------------------------------------------
	// ------------------------------------------------------
	
	/**
	 * Null constructor. Don't use unless you know what you're doing.<br>
	 * Pass a name for the root node.<br>
	 * For example: new Node< String >("root");
	 * @warning
	 */
	public Node() { }

	/**
	 **/
	public Node<DataType> nullNode()
	{
		return new Node<DataType>()
		{
			public boolean isNull() 
				{ return true; }
		};
	}

	/**
	 **/
	public Node(String name)
	{ 
		makeNode(name,null,null); 
	}

	/**
	 **/
	public Node(String name, DataType value)
	{ 
		makeNode(name,value,null); 
	}

	/**
	 **/
	public Node(String name, DataType value, Validation<DataType> valid)
	{ 
		makeNode(name,value,valid); 
	}

	/**Add a path to a child node with a given value. Returns the absolute 
	 * parent node so you can chain multiple creatPath() together.<br>
	 * Example: root.createPath("first",1).createPath("second",2).createPath("third",3)<br>
	 * <br>
	 * createPath(String, C) accepts a third optional parameter which is the Validation class.<br>
	 * Validation allows values to be set if they pass validation and are otherwise discarded.
	 **/
	public Node<DataType> createPath(String path, DataType value)
	{
		createPath(path, value, validCheck);
		return this;
	}

	/**Add a path to a child node with a given value. Returns the absolute 
	 * parent node so you can chain multiple creatPath() together.<br>
	 * Example: root.createPath("first",1).createPath("second",2).createPath("third",3)<br>
	 * <br>
	 * Using the default Validation class checks if a value is null and discards the input if true.
	 **/
	public Node<DataType> createPath(String path, DataType value, Validation<DataType> valid)
	{
		createPath( makeVectorPath(path,"/") , value, valid);
		return this;
	}

	/**
	 **/
	public Node(Vector<String> path, DataType value, Validation<DataType> valid)
	{
		makeNode(path.get(0),null,valid);
		path.remove(0);
		createPath(path,value,valid);
	}

	/**
	 **/
	private Vector<String> makeVectorPath(String path, String sep)
	{
		String[] tokens = path.split(sep);
		Vector<String> vectorPath = new Vector<String>();
		for (String s:tokens)
			vectorPath.add(s);
		return vectorPath;
	}
	
	/**
	 * Makes a node endpoint
	 */
	private void makeNode(String name, DataType value, Validation<DataType> valid)
	{
		nodes = new Vector<Node<DataType>>();
		this.name = name;
		if (valid!=null) this.validCheck = valid;
		this.setValue(value);
	}
	
	// ------------------------------------------------------
	// ------------------------------------------------------
	
	/**Returns the node's content as a string representation.
	 **/
	@Override
	public String toString()
	{
		return 	"Node:"+getName()+
				" - contains "+this.size()+" nodes"+
				" - value:"+getValue().toString();
	}
	
	/**
	 **/
	public String getName() 
	{ return name; }

	/**
	 **/
	public DataType getValue()
	{ return nodeValue; }

	/**
	 **/
	public void setValue(DataType value) 
	{ nodeValue = value; }
	
	/**Returns the number of Nodes stored.
	 **/
	public synchronized int size()
	{
		if (isNull()) return 0;
		return nodes.size(); 
	}
	
	/**Returns true if the node has no child nodes.
	 **/
	public boolean isEmpty()
	{ 
		return (size()==0)?true:false; 
	}
	
	/**Returns true if either the name, nodes or validation is null
	 **/
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
				if (validCheck==null)
					return true;
			}
		}
		return false;
	}
	
	/**
	 **/
	public NodeIterator getIterator()
	{
		return new NodeIterator();
	}
	
	/**
	 **/
	public class NodeIterator implements IIterator<Node<DataType>>
	{
		private int index=0;
		@Override
		public boolean hasNext()
		{
			if (index>=size())
				return false;
			return true;
		}

		@Override
		public Node<DataType> getNext()
		{
			return findNode(index);
		}

		@Override
		public int getIndex()
		{
			return index;
		}

		@Override
		public int getSize()
		{
			return size();
		}

		@Override
		public void reset() 
		{
			index = 0;
		}
	}
}
