package com.ccb.net.models;

import biz.common.IModel;

public class I0010Model implements IModel{
	private String msgId ;
	
	private String equId;
	/**
	 * @return the equId
	 */
	public String getEquId() {
		return equId;
	}
	/**
	 * @param equId the equId to set
	 */
	public void setEquId(String equId) {
		this.equId = equId;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}
