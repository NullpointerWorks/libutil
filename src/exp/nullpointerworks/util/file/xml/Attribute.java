package exp.nullpointerworks.util.file.xml;

public class Attribute
{
	private String name = "";
	private String value = "";
	
	public Attribute() {}
	
	public Attribute(String n)
	{
		setName(n);
	}
	
	public Attribute(String n, String v)
	{
		setName(n);
		setValue(v);
	}
	
	public void setName(String n)
	{
		if (n.equalsIgnoreCase("")) return;
		name = ""+n;
	}

	public void setValue(String v)
	{
		value = ""+v;
	}
	
	public String getName()
	{
		return name;
	}

	public String getValue()
	{
		return value;
	}

	public boolean hasValue()
	{
		return !value.equalsIgnoreCase("");
	}
}
