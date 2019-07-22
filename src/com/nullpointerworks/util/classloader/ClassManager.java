/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.util.classloader;

import com.nullpointerworks.util.pack.node.Node;

public class ClassManager
{
	private static ClassManager instance=null;
	public final static ClassManager getInstance()
	{
		initialize();
		return instance;
	}
	
	public final static void initialize()
	{
		if (instance==null) instance = new ClassManager();
	}

	// =============================================================
	
	private Node<Class<?>> rootNode = new Node<Class<?>>("ClassManager");
	private JarDirLoader jarLoader = new JarDirLoader();
	
	private ClassManager() { }
	
	public void loadJars(String path)
	{
		jarLoader.loadAllJars( path );
	}
	
	public void printNodes()
	{
		synchronized(rootNode)
		{
			rootNode.printContent();
		}
	}
	
	public void createEntry(String classPath, Class<?> clazz)
	{
		synchronized(rootNode)
		{
			rootNode.createPath(classPath, clazz);
		}
	}
	
	public Class<?> findClass(String classname)
	{
		synchronized(rootNode)
		{
			Node<Class<?>> clazz = rootNode.findNode(classname);
			if (!clazz.isNull()) return clazz.getValue();
		}
		return null;
	}
}
