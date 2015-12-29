package biz.common.Excep;

/**
 * 
 * @author 翟艳军 20100312
 * 字符串转异常类
 */
public class StringTransformException extends Exception {
	
	public StringTransformException(){
		super();
	}
	/**
	 * 得到异常信息
	 */
	public String getMessage() {
		return "字符串转换异常，异常信息为："+super.getMessage();
	}
}
