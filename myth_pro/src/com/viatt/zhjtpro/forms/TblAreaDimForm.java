package com.viatt.zhjtpro.forms;


public class TblAreaDimForm extends BaseForm {
	private String areaCode;
	private String commCode;
	private String areaName;
	private String commName;
	/**
	 * @return the commName
	 */
	public String getCommName() {
		return commName;
	}
	/**
	 * @param commName the commName to set
	 */
	public void setCommName(String commName) {
		this.commName = commName;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the commCode
	 */
	public String getCommCode() {
		return commCode;
	}
	/**
	 * @param commCode the commCode to set
	 */
	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}
	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}
	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}