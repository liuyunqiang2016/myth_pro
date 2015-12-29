package biz.common.format;

public abstract class AbsCommFormat
{
	private String splitChar="";
	public String getSplitChar()
	{
		return splitChar;
	}
	public void setSplitChar(String splitChar)
	{
		this.splitChar = splitChar;
	}
}
