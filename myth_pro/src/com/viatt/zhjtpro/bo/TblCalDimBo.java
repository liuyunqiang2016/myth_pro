package com.viatt.zhjtpro.bo;


public class TblCalDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3621215300507150585L;

	public static final String RESI_GRPID = "3402223969890";

	private String calCode;
	private String calName;
	private String calExp;
	/**
	 * @return the calCode
	 */
	public String getCalCode() {
		return calCode;
	}
	/**
	 * @param calCode the calCode to set
	 */
	public void setCalCode(String calCode) {
		this.calCode = calCode;
	}
	/**
	 * @return the calName
	 */
	public String getCalName() {
		return calName;
	}
	/**
	 * @param calName the calName to set
	 */
	public void setCalName(String calName) {
		this.calName = calName;
	}
	/**
	 * @return the calExp
	 */
	public String getCalExp() {
		return calExp;
	}
	/**
	 * @param calExp the calExp to set
	 */
	public void setCalExp(String calExp) {
		this.calExp = calExp;
	}
}
