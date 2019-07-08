package exp.nullpointerworks.util.file.xml;

public interface Content<C>
{
	public String parse();
	public String getName();
	public boolean isEmpty();
	public Element getParent();
	public C setParent(Element element);
}
