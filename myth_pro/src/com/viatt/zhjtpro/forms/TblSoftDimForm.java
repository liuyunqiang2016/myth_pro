package com.viatt.zhjtpro.forms;

import org.apache.struts.upload.FormFile;


public class TblSoftDimForm extends BaseForm {
	private String softCode;
	private String softName;
	private String softVision;
	private String softFn;
	private String softSize;
	private String updExp;
	private String softState;
	private FormFile file;
	private String equType;
	/**
	 * @return the equType
	 */
	public String getEquType() {
		return equType;
	}
	/**
	 * @param equType the equType to set
	 */
	public void setEquType(String equType) {
		this.equType = equType;
	}
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
	 * @return the softCode
	 */
	public String getSoftCode() {
		return softCode;
	}
	/**
	 * @param softCode the softCode to set
	 */
	public void setSoftCode(String softCode) {
		this.softCode = softCode;
	}
	/**
	 * @return the softName
	 */
	public String getSoftName() {
		return softName;
	}
	/**
	 * @param softName the softName to set
	 */
	public void setSoftName(String softName) {
		this.softName = softName;
	}
	/**
	 * @return the softVision
	 */
	public String getSoftVision() {
		return softVision;
	}
	/**
	 * @param softVision the softVision to set
	 */
	public void setSoftVision(String softVision) {
		this.softVision = softVision;
	}
	/**
	 * @return the softFn
	 */
	public String getSoftFn() {
		return softFn;
	}
	/**
	 * @param softFn the softFn to set
	 */
	public void setSoftFn(String softFn) {
		this.softFn = softFn;
	}
	/**
	 * @return the softSize
	 */
	public String getSoftSize() {
		return softSize;
	}
	/**
	 * @param softSize the softSize to set
	 */
	public void setSoftSize(String softSize) {
		this.softSize = softSize;
	}
	/**
	 * @return the updExp
	 */
	public String getUpdExp() {
		return updExp;
	}
	/**
	 * @param updExp the updExp to set
	 */
	public void setUpdExp(String updExp) {
		this.updExp = updExp;
	}
	/**
	 * @return the softState
	 */
	public String getSoftState() {
		return softState;
	}
	/**
	 * @param softState the softState to set
	 */
	public void setSoftState(String softState) {
		this.softState = softState;
	}
}