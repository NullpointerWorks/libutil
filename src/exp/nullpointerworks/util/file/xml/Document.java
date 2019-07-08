package exp.nullpointerworks.util.file.xml;

import java.io.IOException;
import java.util.List;

import com.nullpointerworks.util.file.textfile.TextFile;
import com.nullpointerworks.util.file.textfile.TextFileParser;

import exp.nullpointerworks.util.file.xml.prolog.Prolog;
import exp.nullpointerworks.util.file.xml.prolog.XMLProlog;

public class Document
{
	private Prolog prolog;
	private Element root;
	
	public Document()
	{
		prolog = new XMLProlog();
		root = new Element("root");
	}
	
	public void setRootElement(Element r)
	{
		root = r;
	}

	public void setProlog(Prolog pr)
	{
		prolog = pr;
	}
	
	public Element getRootElement()
	{
		return root;
	}
	
	// =======================================================================
	
	public void loadDocument(String path)
	{
		
		
		readElements(path);
	}
	
	private Element readElements(String path)
	{
		Element doc = null;
		
		return doc;
	}
	
	// =======================================================================
	
	public final TextFile toTextFile(String path)
	{
		// set file encoding
		TextFile tf = new TextFile();
		tf.setEncoding( prolog.getEncoding() );
		
		// document header
		tf.addLine(prolog.getString());
		
		// root element
		printElements(root, tf, 0);
		
		// return textfile
		return tf;
	}
	
	public final String toString(String path)
	{
		// set file encoding
		TextFile tf = new TextFile();
		tf.setEncoding( prolog.getEncoding() );
		
		// document header
		tf.addLine(prolog.getString());
		
		// root element
		printElements(root, tf, 0);
		
		// return textfile as string
		String[] lines = tf.getLines();
		String res = lines[0];
		for (int i=1,l=lines.length;i<l;i++)
		{
			String line = lines[i];
			res += "\n"+line;
		}
		return res;
	}
	
	public void saveDocument(String path) throws IOException
	{
		// get text file
		TextFile tf = toTextFile(path);
		
		// write to disc
		TextFileParser.write(path, tf);
	}
	
	private void printElements(Content<?> root, TextFile tf, int iteration)
	{
		String tab = tabs(iteration);
		
		if ((root instanceof Element) )
		{
			Element r = (Element)root;
			List<Content<?>> c = r.getChildren();
			List<Attribute> a = r.getAttributes();
			
			// if the tag has no childrent ags and just text, behave without tabs and newline
			boolean textOnly = r.hasText() && !r.hasElements(); 
			String postfix = (textOnly)?"":"\n";
			
			// if tag has children
			if (c.size() > 0)
			{
				// list attributes
				if (a.size() > 0)
				{
					String list = "";
					for (Attribute atr : a)
					{
						list = list + " "+atr.getName()+"=\"" + atr.getValue()+"\"";
					}
					tf.addLine(tab+"<"+r.getName()+list+">"+postfix);
				}
				else
				{
					tf.addLine(tab+"<"+r.getName()+">"+postfix);
				}
				
				// parse content
				for (Content<?> e : c)
				{
					printElements(e,tf,iteration+1);
				}
				
				tab = (textOnly)?"":tab;
				tf.addLine(tab+"</"+r.getName()+">"+"\n");
			}
			// else self-closing tag
			else
			{
				if (a.size() > 0)
				{
					String list = "";
					for (Attribute atr : a)
					{
						list = list + " "+atr.getName()+"=\"" + atr.getValue()+"\"";
					}
					tf.addLine(tab+"<"+r.getName()+list+"/>"+"\n");
				}
				else
				{
					tf.addLine(tab+"<"+r.getName()+"/>"+"\n");
				}
			}
		}
		else
		if ((root instanceof Text) )
		{
			Text r = (Text)root;
			boolean textOnly = !r.getParent().hasElements();
			String postfix = (textOnly)?"":"\n";
			tab = (textOnly)?"":tab;
			String txt = r.getText();
			tf.addLine(tab+txt+postfix);
		}
	}

	private String tabs(int iteration)
	{
		String t = "";
		for (;iteration>0;iteration--) t = t + "\u0009";
		return t;
	}
}
