package exp.nullpointerworks.util.file.xml.prolog;

public class HTMLProlog implements Prolog
{
	private String additional = "";
	
	public HTMLProlog() { }
	
	public HTMLProlog(String add)
	{
		additional = " "+add;
	}
	
	@Override
	public String getString()
	{
		return "<!DOCTYPE html"+additional+">\n"; // html5
	}
	
	@Override
	public String getEncoding()
	{
		return "UTF8";
	}
}
