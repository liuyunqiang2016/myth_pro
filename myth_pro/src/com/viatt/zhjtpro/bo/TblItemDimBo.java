package com.viatt.zhjtpro.bo;


public class TblItemDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 2026236002991258639L;

	public static final String RESI_GRPID = "3402223969890";

	private String itemCode;
	private String itemName;
	private String calCode;
	private String sfLev;
	private String sfExp;
	/**
	 * @return the itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode the itemCode to set
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
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
	 * @return the sfLev
	 */
	public String getSfLev() {
		return sfLev;
	}
	/**
	 * @param sfLev the sfLev to set
	 */
	public void setSfLev(String sfLev) {
		this.sfLev = sfLev;
	}
	/**
	 * @return the sfExp
	 */
	public String getSfExp() {
		return sfExp;
	}
	/**
	 * @param sfExp the sfExp to set
	 */
	public void setSfExp(String sfExp) {
		this.sfExp = sfExp;
	}
}
