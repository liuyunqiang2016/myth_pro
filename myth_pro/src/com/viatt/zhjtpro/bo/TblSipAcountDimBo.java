package com.viatt.zhjtpro.bo;

public class TblSipAcountDimBo extends BaseBo {

	/**
	 *  序列化
	 */
	private static final long serialVersionUID = 5447205204857052054L;
	
	private String acountId;
	private String username;
	private String password;
	private String type;
	private String commPro;
	private String groupId;
	private String remark;
	public String getAcountId() {
		return acountId;
	}
	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCommPro() {
		return commPro;
	}
	public void setCommPro(String commPro) {
		this.commPro = commPro;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
