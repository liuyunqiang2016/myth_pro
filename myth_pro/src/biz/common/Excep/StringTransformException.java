package biz.common.Excep;

/**
 * 
 * @author ���޾� 20100312
 * �ַ���ת�쳣��
 */
public class StringTransformException extends Exception {
	
	public StringTransformException(){
		super();
	}
	/**
	 * �õ��쳣��Ϣ
	 */
	public String getMessage() {
		return "�ַ���ת���쳣���쳣��ϢΪ��"+super.getMessage();
	}
}
