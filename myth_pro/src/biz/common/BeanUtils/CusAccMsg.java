package biz.common.BeanUtils;

import biz.common.Excep.CusNotFoundException;

/**
 * 客户信息验证类
 * 
 * @author 翟艳军 20100317 客户信息查询类 包括客户账户名称查询 账户状态查询
 */
public class CusAccMsg {

	/**
	 * 根据客户账号查询客户姓名
	 * 
	 * @param cusAcc
	 *            客户账号
	 * @return 返回客户姓名 如果客户姓名有问题则返回字符串"err"+报文返回信息
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
	 * 根据客户账号查询客户账号状态
	 * 
	 * @param cusAcc
	 *            客户账号
	 * @return 返回客户账号状态 如果客户账号状态有问题则返回字符串"err"+报文返回信息
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
	 * 根据客户账号查询客户余额
	 * 
	 * @param cusAcc
	 *            客户账号
	 * @return 返回客户姓名 如果客户金额有问题则返回字符串"err"+报文返回信息
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
	 * 查询帐号余额
	 * @param 输入需查询帐号
	 * @return 返回账户余额
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
	 * @param accFrom 转出账户
	 * @param accTo 转入账户
	 * @param amt 转帐金额
	 * @return 返回流水号 如果转帐出现异常则返回空
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
