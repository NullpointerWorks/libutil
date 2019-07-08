package exp.nullpointerworks.util.file;

import java.awt.Component;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * File chooser to select DEK files
 */
public class FileChooser
{
	private JFileChooser fc;
	private File f;
	private boolean hasFile;
	private static String retention = ".";
	
	public FileChooser()
	{
		fc = new JFileChooser();
		retention = (retention.equalsIgnoreCase(""))?".":retention;
		fc.setCurrentDirectory( new File(retention) );
		hasFile = false;
	}
	
	/**
	 * 
	 */
	public void setFileFilter(FileNameExtensionFilter fne)
	{
		fc.setFileFilter(fne);
	}
	
	/**
	 * Open a save dialog and get a saving path directory as a File object
	 */
	public void openSaveDialog(Component parent) 
	{
		hasFile = false;

		fc.setDialogTitle( "Save file.." );
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setCurrentDirectory( new File(retention) );
		int i = fc.showSaveDialog(parent);
		
		switch(i)
		{
		case JFileChooser.APPROVE_OPTION:
			hasFile = true;
			File r = fc.getSelectedFile();
			retention = (r==null)? ".": r.getParent();
			break;
		case JFileChooser.CANCEL_OPTION:
		case JFileChooser.ERROR_OPTION:
			break;
		}
	}
	
	/**
	 * Open a search dialog pop-up waiting on the event train of the given component
	 */
	public void openLoadDialog(Component parent) 
	{
		hasFile = false;
		
		fc.setDialogTitle( "Load file.." );
		fc.setCurrentDirectory( new File(retention) );
		int i = fc.showOpenDialog(parent);
		
		switch(i)
		{
		case JFileChooser.APPROVE_OPTION:
			hasFile = true;
			File r = fc.getSelectedFile();
			retention = (r==null)? ".": r.getParent();
			break;
			
		case JFileChooser.CANCEL_OPTION:
		case JFileChooser.ERROR_OPTION:
			break;
		}
	}
	
	/**
	 * Returns true if the FileChooser has a file in memory
	 */
	public boolean isFileSelected() 
	{
		return hasFile;
	}
	
	/**
	 * Returns the File object held by the FileChooser
	 */
	public File getSelectedFile()
	{
		f = fc.getSelectedFile();
		return f;
	}
}
