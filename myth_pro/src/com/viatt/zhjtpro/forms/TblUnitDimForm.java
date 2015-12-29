package com.viatt.zhjtpro.forms;

import org.apache.struts.upload.FormFile;


public class TblUnitDimForm extends BaseForm {
	private String unitCode;
	private String buildingCode;
	private String areaCode;
	private String commCode;
	private String unitName;
	private String unitUrl;
	private String areaName;
	private String buildingName;
	private String commName;
	private FormFile file;
	/**
	 * @return the file
	 */
	public FormFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(FormFile file) {
		this.file = file;
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
	/**
	 * @return the buildingName
	 */
	public String getBuildingName() {
		return buildingName;
	}
	/**
	 * @param buildingName the buildingName to set
	 */
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
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