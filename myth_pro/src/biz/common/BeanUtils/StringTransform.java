package biz.common.BeanUtils;

import biz.common.Excep.StringTransformException;

/**
 * 
 * @author ���޾� 20100312
 * �ַ���ת����
 */
public class StringTransform {

	/**
	 * �ַ���ת������������ת��Ϊ�ַ���
	 * @param obj ��Ҫת���Ķ���
	 * @return ת������ַ���
	 * @throws StringTransformException �������Ĳ��ǻ������ͻ��ߴ������Ϊ��
	 *                                  ���׳��ַ���ת���쳣
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
	 * �ַ���ȥ��
	 * @param obj ��Ҫȥ�յĶ���
	 * @return ȥ�պ���ַ���
	 * @throws StringTransformException �������Ĳ��ǻ������ͻ��ߴ������Ϊ��
	 * 									���׳��ַ���ת���쳣
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
