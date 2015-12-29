package com.viatt.zhjtpro.forms;

import org.apache.struts.upload.FormFile;

public class TblHousetypeDimForm extends BaseForm 
{
	
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4635505590999893783L;
	
	private String htCode;
	private String htName;
	private String roCount;
	private String toCount;
	private String htUrl;
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
	 * @return the htCode
	 */
	public String getHtCode() {
		return htCode;
	}
	/**
	 * @param htCode the htCode to set
	 */
	public void setHtCode(String htCode) {
		this.htCode = htCode;
	}
	/**
	 * @return the htName
	 */
	public String getHtName() {
		return htName;
	}
	/**
	 * @param htName the htName to set
	 */
	public void setHtName(String htName) {
		this.htName = htName;
	}
	/**
	 * @return the htUrl
	 */
	public String getHtUrl() {
		return htUrl;
	}
	/**
	 * @param htUrl the htUrl to set
	 */
	public void setHtUrl(String htUrl) {
		this.htUrl = htUrl;
	}
	/**
	 * @return the roCount
	 */
	public String getRoCount() {
		return roCount;
	}
	/**
	 * @param roCount the roCount to set
	 */
	public void setRoCount(String roCount) {
		this.roCount = roCount;
	}
	/**
	 * @return the toCount
	 */
	public String getToCount() {
		return toCount;
	}
	/**
	 * @param toCount the toCount to set
	 */
	public void setToCount(String toCount) {
		this.toCount = toCount;
	}
}