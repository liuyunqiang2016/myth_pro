package com.viatt.zhjtpro.forms;

import java.util.List;


public class TblMenuDimForm extends BaseForm {
	private String menuCode;
	private String menuName;
	private String menuLev;
	private String menuPar;
	private String menuUrl;
	private String menuView;
	private String menuExp;
	private List parMenu;
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
	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}
	/**
	 * @param menuName the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	/**
	 * @return the menuLev
	 */
	public String getMenuLev() {
		return menuLev;
	}
	/**
	 * @param menuLev the menuLev to set
	 */
	public void setMenuLev(String menuLev) {
		this.menuLev = menuLev;
	}
	/**
	 * @return the menuPar
	 */
	public String getMenuPar() {
		return menuPar;
	}
	/**
	 * @param menuPar the menuPar to set
	 */
	public void setMenuPar(String menuPar) {
		this.menuPar = menuPar;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/**
	 * @return the menuView
	 */
	public String getMenuView() {
		return menuView;
	}
	/**
	 * @param menuView the menuView to set
	 */
	public void setMenuView(String menuView) {
		this.menuView = menuView;
	}
	/**
	 * @return the menuExp
	 */
	public String getMenuExp() {
		return menuExp;
	}
	/**
	 * @param menuExp the menuExp to set
	 */
	public void setMenuExp(String menuExp) {
		this.menuExp = menuExp;
	}
	/**
	 * @return the parMenu
	 */
	public List getParMenu() {
		return parMenu;
	}
	/**
	 * @param parMenu the parMenu to set
	 */
	public void setParMenu(List parMenu) {
		this.parMenu = parMenu;
	}
}