package com.viatt.zhjtpro.bo;

public class TblPropertyDimBo extends BaseBo
{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 7784132334501398649L;

	private String propertyId;
	private String propertyName;
	
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
