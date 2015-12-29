package com.viatt.zhjtpro.forms;

import org.apache.struts.upload.FormFile;

public class OrgDimForm extends BaseForm {
	
	private String orgCode;
	private String orgName;
	private String orgState;
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
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return the orgState
	 */
	public String getOrgState() {
		return orgState;
	}
	/**
	 * @param orgState the orgState to set
	 */
	public void setOrgState(String orgState) {
		this.orgState = orgState;
	}
}
