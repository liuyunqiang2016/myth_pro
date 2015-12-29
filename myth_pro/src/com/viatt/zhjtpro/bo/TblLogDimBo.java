package com.viatt.zhjtpro.bo;

public class TblLogDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -791766730397236522L;

	public static final String RESI_GRPID = "3402223969890";

	private String logCode;
	private String logContent;
	private String operName;
	private String creTime;
	/**
	 * @return the logCode
	 */
	public String getLogCode() {
		return logCode;
	}
	/**
	 * @param logCode the logCode to set
	 */
	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}
	/**
	 * @return the logContent
	 */
	public String getLogContent() {
		return logContent;
	}
	/**
	 * @param logContent the logContent to set
	 */
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	/**
	 * @return the operName
	 */
	public String getOperName() {
		return operName;
	}
	/**
	 * @param operName the operName to set
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}
	/**
	 * @return the creTime
	 */
	public String getCreTime() {
		return creTime;
	}
	/**
	 * @param creTime the creTime to set
	 */
	public void setCreTime(String creTime) {
		this.creTime = creTime;
	}
}
