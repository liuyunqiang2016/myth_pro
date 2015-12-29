package biz.common.BeanUtils;

import biz.common.Excep.CusNotFoundException;

/**
 * �ͻ���Ϣ��֤��
 * 
 * @author ���޾� 20100317 �ͻ���Ϣ��ѯ�� �����ͻ��˻����Ʋ�ѯ �˻�״̬��ѯ
 */
public class CusAccMsg {

	/**
	 * ���ݿͻ��˺Ų�ѯ�ͻ�����
	 * 
	 * @param cusAcc
	 *            �ͻ��˺�
	 * @return ���ؿͻ����� ����ͻ������������򷵻��ַ���"err"+���ķ�����Ϣ
	 */
	public static String getCusAccName(String cusAcc)
			throws CusNotFoundException {
		try {
			String custAccName = "";
			if (custAccName.indexOf("") == -1) {
				return "err" + custAccName;
			}
			return custAccName;
		} catch (Exception e) {
			throw new CusNotFoundException();
		}

	}

	/**
	 * ���ݿͻ��˺Ų�ѯ�ͻ��˺�״̬
	 * 
	 * @param cusAcc
	 *            �ͻ��˺�
	 * @return ���ؿͻ��˺�״̬ ����ͻ��˺�״̬�������򷵻��ַ���"err"+���ķ�����Ϣ
	 */
	public static String getCusAccStatus(String cusAcc)
			throws CusNotFoundException {
		try {
			String custAccStatus = "";
			if (custAccStatus.indexOf("") == -1) {
				return "err" + custAccStatus;
			}
			return custAccStatus;
		} catch (Exception e) {
			throw new CusNotFoundException();
		}

	}

	/**
	 * ���ݿͻ��˺Ų�ѯ�ͻ����
	 * 
	 * @param cusAcc
	 *            �ͻ��˺�
	 * @return ���ؿͻ����� ����ͻ�����������򷵻��ַ���"err"+���ķ�����Ϣ
	 */
	public static String getCusAccMoney(String cusAcc)
			throws CusNotFoundException {
		try {
			String custAccMoney = "";
			if (custAccMoney.indexOf("") == -1) {
				return "err" + custAccMoney;
			}
			return custAccMoney;
		} catch (Exception e) {
			throw new CusNotFoundException();
		}

	}
	
	
	/**
	 * ��ѯ�ʺ����
	 * @param �������ѯ�ʺ�
	 * @return �����˻����
	 */
	public static Double getCusAccBala(String accNo){
		try {
			Double custAccMoney = 0.00;
			String res = "";
			if (res.indexOf("") == -1) {
				return null;
			}
			return custAccMoney;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 
	 * @param accFrom ת���˻�
	 * @param accTo ת���˻�
	 * @param amt ת�ʽ��
	 * @return ������ˮ�� ���ת�ʳ����쳣�򷵻ؿ�
	 */
	public static String balaMoney(String accFrom,String accTo,Double amt){
		String flowNo = "";
		try{
			flowNo = "";
			String res="";
			if (res.indexOf("") == -1) {
				return "err"+res;
			}else{
				return flowNo;
			}
		}catch(Exception e){
			return null;
		}
	}
}
