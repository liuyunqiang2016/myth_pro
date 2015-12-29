package com.viatt.zhjtpro.bo;

public class TblInfoStatusDimBo extends BaseBo {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 6628900060582390673L;
	
	private String equCode;
	private String infoCode;
	private String infoType;
	private String updType;
	private String sendZt;
	private String remark;
	private String infoTitle ;
	
	/**
	 * @return the equCode
	 */
	public String getEquCode() {
		return equCode;
	}
	/**
	 * @param equCode the equCode to set
	 */
	public void setEquCode(String equCode) {
		this.equCode = equCode;
	}
	/**
	 * @return the infoCode
	 */
	public String getInfoCode() {
		return infoCode;
	}
	/**
	 * @param infoCode the infoCode to set
	 */
	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}
	/**
	 * @return the infoType
	 */
	public String getInfoType() {
		return infoType;
	}
	/**
	 * @param infoType the infoType to set
	 */
	public void setInfoType(String infoType) {
		this.infoType = infoType;
	}
	/**
	 * @return the sendZt
	 */
	public String getSendZt() {
		return sendZt;
	}
	/**
	 * @param sendZt the sendZt to set
	 */
	public void setSendZt(String sendZt) {
		this.sendZt = sendZt;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the updType
	 */
	public String getUpdType() {
		return updType;
	}
	/**
	 * @param updType the updType to set
	 */
	public void setUpdType(String updType) {
		this.updType = updType;
	}
	
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	
	public String getInfoTitle() {
		return infoTitle;
	}
}
