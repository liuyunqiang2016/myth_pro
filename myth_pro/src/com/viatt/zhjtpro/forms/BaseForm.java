package com.viatt.zhjtpro.forms;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm {
 
	protected String advancedUserName;

	protected String advancedPassword; 

	protected String method;

	protected int pageSize = 15;
	
	private String menuPare;
	private String menuChild;
	

	/**
	 * @return the menuPare
	 */
	public String getMenuPare() {
		return menuPare;
	}

	/**
	 * @param menuPare the menuPare to set
	 */
	public void setMenuPare(String menuPare) {
		this.menuPare = menuPare;
	}

	/**
	 * @return the menuChild
	 */
	public String getMenuChild() {
		return menuChild;
	}

	/**
	 * @param menuChild the menuChild to set
	 */
	public void setMenuChild(String menuChild) {
		this.menuChild = menuChild;
	}

	public String getAdvancedUserName() {
		return advancedUserName;
	}

	public String getAdvancedPassword() {
		return advancedPassword;
	}

	public void setAdvancedUserName(String advancedUserName) {
		this.advancedUserName = advancedUserName;
	}

	public void setAdvancedPassword(String advancedPassword) {
		this.advancedPassword = advancedPassword;
	}

	public String getMethod() {
		return method;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
