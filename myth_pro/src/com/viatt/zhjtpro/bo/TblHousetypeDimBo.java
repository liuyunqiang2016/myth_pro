package com.viatt.zhjtpro.bo;

public class TblHousetypeDimBo extends BaseBo {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 4725177819796072423L;

	public static final String RESI_GRPID = "3402223969890";
	
	private String htCode;
	private String htName;
	private String roCount;
	private String toCount;
	private String htUrl;
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
}
