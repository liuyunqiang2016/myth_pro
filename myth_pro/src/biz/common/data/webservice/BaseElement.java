package biz.common.data.webservice;

public class BaseElement
{
	private String dataName;
	
	private String type;

	private String index;

	private String lenght;

	private String charset;

	private String dataType;

	private String importClass;

	private String sysat;

	private String key;

	private boolean required = false;

	private boolean readOnly = false;

	public boolean isRequired()
	{
		return required;
	}

	public void setRequired(boolean required)
	{
		this.required = required;
	}

	public String getDataType()
	{
		return dataType;
	}

	public void setDataType(String dataType)
	{
		this.dataType = dataType;
	}

	public String getImportClass()
	{
		return importClass;
	}

	public void setImportClass(String importClass)
	{
		this.importClass = importClass;
	}

	public String getCharset()
	{
		return charset;
	}

	public void setCharset(String charset)
	{
		this.charset = charset;
	}

	public String getDataName()
	{
		return dataName;
	}

	public void setDataName(String dataName)
	{
		this.dataName = dataName;
	}

	public String getIndex()
	{
		return index;
	}

	public void setIndex(String index)
	{
		this.index = index;
	}

	public String getLenght()
	{
		return lenght;
	}

	public void setLenght(String lenght)
	{
		this.lenght = lenght;
	}

	public String getSysat()
	{
		return sysat;
	}

	public void setSysat(String sysat)
	{
		this.sysat = sysat;
	}

	public boolean isReadOnly()
	{
		return readOnly;
	}

	public void setReadOnly(boolean readOnly)
	{
		this.readOnly = readOnly;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
