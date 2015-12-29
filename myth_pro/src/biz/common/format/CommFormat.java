package biz.common.format;

import java.util.Map;

import biz.common.IModel;

public interface CommFormat
{
	public IModel newPackageReceived(byte[] msg,String strClassName,Map map)throws Exception;
	
	public String getTransCode(byte[] msg);
	
	public String getTransCode();
	
	public byte[] getPackageSend(IModel model,String strClassName,Map map)throws Exception;
}
