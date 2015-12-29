package biz.common.net.tcp;

import java.util.Map;

public class TCPIPServerModel
{
	private String key;

	private String value;

	private String beanName;

	private String opName;

	private boolean auto = false;

	private Map inputList;

	private Map outputList;

	private String inputClass;

	private String outPutClass;

	public String getInputClass()
	{
		return inputClass;
	}

	public void setInputClass(String inputClass)
	{
		this.inputClass = inputClass;
	}

	public Map getInputList()
	{
		return inputList;
	}

	public void setInputList(Map inputList)
	{
		this.inputList = inputList;
	}

	public String getOutPutClass()
	{
		return outPutClass;
	}

	public void setOutPutClass(String outPutClass)
	{
		this.outPutClass = outPutClass;
	}

	public Map getOutputList()
	{
		return outputList;
	}

	public void setOutputList(Map outputList)
	{
		this.outputList = outputList;
	}

	public String getBeanName()
	{
		return beanName;
	}

	public void setBeanName(String beanName)
	{
		this.beanName = beanName;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getOpName()
	{
		return opName;
	}

	public void setOpName(String opName)
	{
		this.opName = opName;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public boolean isAuto()
	{
		return auto;
	}

	public void setAuto(boolean auto)
	{
		this.auto = auto;
	}
}
