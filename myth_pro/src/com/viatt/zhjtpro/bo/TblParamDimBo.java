package com.viatt.zhjtpro.bo;

public class TblParamDimBo extends BaseBo {
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -1424293505374153192L;
	private String paramCode;
	private String paramName;
	private String paramValue;
	private String paramExp;
	/**
	 * @return the paramCode
	 */
	public String getParamCode() {
		return paramCode;
	}
	/**
	 * @param paramCode the paramCode to set
	 */
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * @return the paramValue
	 */
	public String getParamValue() {
		return paramValue;
	}
	/**
	 * @param paramValue the paramValue to set
	 */
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	/**
	 * @return the paramExp
	 */
	public String getParamExp() {
		return paramExp;
	}
	/**
	 * @param paramExp the paramExp to set
	 */
	public void setParamExp(String paramExp) {
		this.paramExp = paramExp;
	}
}
