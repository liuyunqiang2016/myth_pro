package biz.common.Excep;

import biz.common.BeanUtils.StringTransform;

/**
 * 
 * @author ���޾� 20100312
 * ͨ�ñ�����Ϣ�����쳣��
 */
public class ReportParseException extends Exception {
	
	public ReportParseException(){
		super();
	}
	
	/**
	 * �õ��쳣��Ϣ
	 */
	public String getMessage() {
		try {
			return "ͨ�ñ��Ľ����쳣,�쳣��ϢΪ��"+StringTransform.strTrim(super.getMessage());
		} catch (StringTransformException e) {
			return "ͨ�ñ��Ľ����쳣,�쳣��ϢΪ��";
		}
	}
	
}
