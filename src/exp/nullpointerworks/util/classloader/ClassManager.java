/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.util.classloader;

import com.nullpointerworks.util.pack.node.Node;

/**
 * 
 * @since 1.0.0
 */
public class ClassManager
{
	private static ClassManager instance=null;
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public final static ClassManager getInstance()
	{
		newInstance();
		return instance;
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public final static void newInstance()
	{
		if (instance==null) instance = new ClassManager();
	}
	
	private Node<Class<?>> rootNode = new Node<Class<?>>("ClassManager");
	private JarLoader jarLoader = new JarLoader();
	
	/**
	 * 
	 * @since 1.0.0
	 */
	private ClassManager() { }
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void loadAllJars(String path)
	{
		jarLoader.loadAllJars( path );
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void loadJarFile(String path)
	{
		jarLoader.loadJarFile( path );
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void printNodes()
	{
		synchronized(rootNode)
		{
			rootNode.printContent();
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public void createEntry(String classPath, Class<?> clazz)
	{
		synchronized(rootNode)
		{
			rootNode.createPath(classPath, clazz);
		}
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
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
