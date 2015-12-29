package com.viatt.zhjtpro.bo;

public class TblAreaDimBo extends BaseBo 
{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1796322235669856070L;

	public static final String RESI_GRPID = "3402223969890";
	
	private String areaCode;
	private String commCode;
	private String commName;
	private String areaName;
	
	
	public String getCommName() {
		return commName;
	}
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
