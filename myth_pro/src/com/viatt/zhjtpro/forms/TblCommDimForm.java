package com.viatt.zhjtpro.forms;

import org.apache.struts.upload.FormFile;


public class TblCommDimForm extends BaseForm {
	private String commCode;
	private String commName;
	private String commAdds;
	private String commArea;
	private String commUrl;
	private String commPro;
	private FormFile file;
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
	 * @return the commCode
	 */
	public String getCommCode() {
		return commCode;
	}
	/**
	 * @param commCode the commCode to set
	 */
	public void setCommCode(String commCode) {
		this.commCode = commCode;
	}
	/**
	 * @return the commName
	 */
	public String getCommName() {
		return commName;
	}
	/**
	 * @param commName the commName to set
	 */
	public void setCommName(String commName) {
		this.commName = commName;
	}
	/**
	 * @return the commAdds
	 */
	public String getCommAdds() {
		return commAdds;
	}
	/**
	 * @param commAdds the commAdds to set
	 */
	public void setCommAdds(String commAdds) {
		this.commAdds = commAdds;
	}
	/**
	 * @return the commArea
	 */
	public String getCommArea() {
		return commArea;
	}
	/**
	 * @param commArea the commArea to set
	 */
	public void setCommArea(String commArea) {
		this.commArea = commArea;
	}
	/**
	 * @return the commUrl
	 */
	public String getCommUrl() {
		return commUrl;
	}
	/**
	 * @param commUrl the commUrl to set
	 */
	public void setCommUrl(String commUrl) {
		this.commUrl = commUrl;
	}
	/**
	 * @return the commPro
	 */
	public String getCommPro() {
		return commPro;
	}
	/**
	 * @param commPro the commPro to set
	 */
	public void setCommPro(String commPro) {
		this.commPro = commPro;
	}
}