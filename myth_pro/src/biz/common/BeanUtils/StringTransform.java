package biz.common.BeanUtils;

import biz.common.Excep.StringTransformException;

/**
 * 
 * @author 翟艳军 20100312
 * 字符串转换类
 */
public class StringTransform {

	/**
	 * 字符串转换：其它类型转换为字符串
	 * @param obj 需要转换的对象
	 * @return 转换后的字符串
	 * @throws StringTransformException 如果传入的不是基本类型或者传入参数为空
	 *                                  就抛出字符串转换异常
	 */
	public static String changeString(Object obj) throws StringTransformException{
		if(obj!=null&&((obj instanceof String)||(obj instanceof Integer)
				||(obj instanceof Long)||(obj instanceof Double)
				||(obj instanceof Float)||(obj instanceof Character)
				||(obj instanceof Byte)||(obj instanceof Short)
						||(obj instanceof Boolean))){
			return obj.toString();
		}else{
			throw new StringTransformException();
		}
	}
	
	/**
	 * 字符串去空
	 * @param obj 需要去空的对象
	 * @return 去空后的字符串
	 * @throws StringTransformException 如果传入的不是基本类型或者传入参数为空
	 * 									就抛出字符串转换异常
	 */
	public static String strTrim(Object obj)throws StringTransformException{
		if(obj!=null&&((obj instanceof String)||(obj instanceof Integer)
				||(obj instanceof Long)||(obj instanceof Double)
				||(obj instanceof Float)||(obj instanceof Character)
				||(obj instanceof Byte)||(obj instanceof Short)
						||(obj instanceof Boolean))){
			return obj.toString().trim();
		}else{
			throw new StringTransformException();
		}
	}
	
}
