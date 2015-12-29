package com.viatt.zhjtpro.bo;

public class TblRolemenuDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 3723624626476822405L;

	public static final String RESI_GRPID = "3402223969890";
	
	private String roleCode;
	private String menuCode;
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
	 * @return the menuCode
	 */
	public String getMenuCode() {
		return menuCode;
	}
	/**
	 * @param menuCode the menuCode to set
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
}
