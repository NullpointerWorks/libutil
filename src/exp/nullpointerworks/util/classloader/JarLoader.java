/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package exp.nullpointerworks.util.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.nullpointerworks.util.Log;
import com.nullpointerworks.util.pack.node.Node;
import com.nullpointerworks.util.pack.node.Printer;

/**
 * 
 * @since 1.0.0
 */
public class JarLoader
{
	/**
	 * Load all jar files in a directory.
	 * @since 1.0.0
	 */
	public void loadAllJars(String path)
	{
		File directory = new File( path );
		File[] files = directory.listFiles();
		if (files==null || files.length==0) return;
		
		String name;
	    for (File f: files)
	    {
	    	name = f.getName();
	    	if ( f.exists() )
	    	if ( name.endsWith(".jar") )
	    	{
	    		Log.out("Loading file: "+name);
	    		
	    		try
				{
	    			DynamicURLClassLoader loader = new DynamicURLClassLoader( f );
	    			Node<String> classNames = this.loadJarFile( path+"/"+name );
	    			
	    			classNames.setPrinter(new Printer<String, String>()
	    			{
						@Override
						public void onPrint(String path, String name) 
						{
							Class<?> loadedClass;
							try 
							{
								// node path includes the name
								loadedClass = loader.loadClassByPath( path );
			    				(ClassManager.getInstance()).createEntry(path, loadedClass);
							} 
							catch (ClassNotFoundException e) 
							{
								e.printStackTrace();
							} 
						}
	    			});
	    			
	    			classNames.printContent();
	    			loader.close();
				}
	    		catch (IOException e)
				{
					e.printStackTrace();
				}
	    	}
	    }
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	public Node<String> loadJarFile(String path)
	{
		try
		{
			Node<String> classNames		= new Node<String>("file");
			ZipInputStream zip 			= new ZipInputStream( new FileInputStream( path ) );
			ZipEntry entry;
			while( (entry=zip.getNextEntry()) != null)
			{
				if (!entry.isDirectory())
				if (entry.getName().endsWith(".class")) 
			    {
					onClassFile(entry, classNames);
			    }
			}
			zip.close();
			return classNames;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return new Node<String>();
	}
	
	/**
	 * 
	 * @since 1.0.0
	 */
	private void onClassFile(ZipEntry entry, Node<String> classNames)
	{
		String classentry = entry.getName();
		classentry = classentry.substring(0,classentry.length()-6); // remove ".class"
        String[] parts = classentry.split("/");
        String classpath = "";
        String className = parts[parts.length-1];
        
        for(int i=0; i<parts.length;i++)
        	classpath += parts[i]+((i<parts.length-1)?".":"");
        classNames.createPath(classpath, className);
	}
}
