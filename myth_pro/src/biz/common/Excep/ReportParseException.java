package biz.common.Excep;

import biz.common.BeanUtils.StringTransform;

/**
 * 
 * @author 翟艳军 20100312
 * 通用报文信息解析异常类
 */
public class ReportParseException extends Exception {
	
	public ReportParseException(){
		super();
	}
	
	/**
	 * 得到异常信息
	 */
	public String getMessage() {
		try {
			return "通用报文解析异常,异常信息为："+StringTransform.strTrim(super.getMessage());
		} catch (StringTransformException e) {
			return "通用报文解析异常,异常信息为：";
		}
	}
	
}
