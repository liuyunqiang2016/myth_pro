package com.viatt.zhjtpro.bo;

public class TblUserDimBo extends BaseBo {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1494424124754762152L;

	public static final String USER_INFO_REFERENCE = "user_info";
	
	private String userCode;
	private String logName;
	private String logPwd;
	private String userName;
	private String lastLogtime;
	private String userState;
	private String roleCode;
	private String propertyId;
	private String deptName;
	private String orgCode;
	private String emailAddress;
	private String telNum;
	
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}
	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	/**
	 * @return the logName
	 */
	public String getLogName() {
		return logName;
	}
	/**
	 * @param logName the logName to set
	 */
	public void setLogName(String logName) {
		this.logName = logName;
	}
	/**
	 * @return the logPwd
	 */
	public String getLogPwd() {
		return logPwd;
	}
	/**
	 * @param logPwd the logPwd to set
	 */
	public void setLogPwd(String logPwd) {
		this.logPwd = logPwd;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the lastLogtime
	 */
	public String getLastLogtime() {
		return lastLogtime;
	}
	/**
	 * @param lastLogtime the lastLogtime to set
	 */
	public void setLastLogtime(String lastLogtime) {
		this.lastLogtime = lastLogtime;
	}
	/**
	 * @return the userState
	 */
	public String getUserState() {
		return userState;
	}
	/**
	 * @param userState the userState to set
	 */
	public void setUserState(String userState) {
		this.userState = userState;
	}
	/**
	 * @return the roleCode
	 */
	public String getRoleCode() {
		return roleCode;
	}
	/**
	 * @param roleCode the roleCode to set
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	/**
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}
	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the telNum
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the telNum to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
}
