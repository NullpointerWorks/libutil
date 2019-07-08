package exp.nullpointerworks.util.file.xml.prolog;

import com.nullpointerworks.util.file.Encoding;

import exp.nullpointerworks.util.file.xml.Version;

public class XMLProlog implements Prolog
{
	private String version 	= Version.V10;
	private String encoding = Encoding.UTF8;
	private String standalone = "no";
	
	public XMLProlog() {}

	public XMLProlog(String e)
	{
		setEncoding(e);
	}
	
	public XMLProlog(String v, String e)
	{
		setVersion(v);
		setEncoding(e);
	}
	
	@Override
	public String getString()
	{
		return "<?xml version=\""+getVersion()+"\" encoding=\""+getEncoding()+"\"?>"+"\n";
	}
	
	public void setVersion(final String v)
	{
		version = ""+v;
	}
	
	public void setEncoding(final String e)
	{
		encoding = ""+e;
		encoding = encoding.replace("-", "");
	}
	
	public void setStandalone(final String s)
	{
		standalone = ""+s;
	}
	
	public String getVersion()
	{
		return version;
	}

	public String getEncoding()
	{
		return encoding;
	}

	public String getStandalone()
	{
		return standalone;
	}
}
