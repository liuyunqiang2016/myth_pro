package com.viatt.zhjtpro.bo;


public class TblRoleDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 446962916686210840L;

	public static final String RESI_GRPID = "3402223969890";
	
	private String roleCode;
	private String roleName;
	private String orgCode;
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
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
}
