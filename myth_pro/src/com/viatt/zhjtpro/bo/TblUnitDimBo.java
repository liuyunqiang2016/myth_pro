package com.viatt.zhjtpro.bo;

public class TblUnitDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3946971612613086713L;

	public static final String RESI_GRPID = "3402223969890";
	
	private String unitCode;
	private String buildingCode;
	private String areaCode;
	private String commCode;
	private String unitName;
	private String unitUrl;
	/**
	 * @return the unitCode
	 */
	public String getUnitCode() {
		return unitCode;
	}
	/**
	 * @param unitCode the unitCode to set
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	/**
	 * @return the buildingCode
	 */
	public String getBuildingCode() {
		return buildingCode;
	}
	/**
	 * @param buildingCode the buildingCode to set
	 */
	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
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
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * @return the unitUrl
	 */
	public String getUnitUrl() {
		return unitUrl;
	}
	/**
	 * @param unitUrl the unitUrl to set
	 */
	public void setUnitUrl(String unitUrl) {
		this.unitUrl = unitUrl;
	}

}
